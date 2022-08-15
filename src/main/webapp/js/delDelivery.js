let resAddress = sessionStorage.getItem('resAddress');
let cartList = JSON.parse(sessionStorage.getItem('cartList'));
let orderList = JSON.parse(sessionStorage.getItem('orderList'));


// 訂單資訊
$("#res-address").text(resAddress);
$("#lump-sum").text(orderList.total);
$("#payment-method").text(orderList.payBy);
$("#del-address").text(orderList.address);

let listgroupitem;
$.each(cartList, function( index, value ) {
    // console.log(index);
    // console.log(value);

    $("#res-name").text(value.resName);

    listgroupitem = `
    <div class="list-group-item">
      <div class="meal-title">
        <div id="meal">${value.productName}</div>
        <div>$<span id="meal-price">${value.productPrice}</span></div>                       
      </div>
      <p class="amount">數量${value.amount}</p>        
    </div>         
    `;          
  $(".order-list").before(listgroupitem);

});

// 完成訂單倒轉畫面
var btnOrderFinish = document.querySelector(".btnOrderFinish");
btnOrderFinish.addEventListener("click",function(){
  var worker = new Worker("./js/worker_countdown.js");
  worker.onmessage = function (e) {
    document.querySelector("#countsec").innerHTML = e.data;
    if (e.data == 0) {
      worker.terminate();
      window.location.replace("./delIndex.html");
    }
  };
});
      
//聯絡使用者的聊天室
function appendNewMsg(isMe, msg) {
  let messagesArea = document.querySelector(".messages");
  let li = document.createElement("li");
  li.className += isMe ? "me" : "friend";
  li.innerHTML = msg;
  messagesArea.appendChild(li);
  messagesArea.scrollTop = messagesArea.scrollHeight;
}

// WebSocket
let userId = orderList.userId;
let delId = sessionStorage.getItem('del');
let delType = "1";

// 建立連線
let myPoint = `/ResToDel/${delId}/${delType}`;
let host = window.location.host;
// let path = window.location.pathname;
// let webCtx = path.substring(0, path.indexOf("/", 1));
let endPointURL = "wss://" + window.location.host + "/monfood_maven" + myPoint;
console.log(endPointURL);

let webSocket = new WebSocket(endPointURL);

webSocket.onopen = function(event) {
  // console.log("外送員連線上囉Connect Success!");					
};

webSocket.onmessage = function(event) {
  let jsonObj = JSON.parse(event.data);
  // console.log("onmessage收到的訊息 = ", jsonObj);
    if ("userSend" === jsonObj.type) {
      // console.log("我有收到userSend");
      let message = jsonObj.message;
      let isMe = jsonObj.sender === delId;
      appendNewMsg(isMe, message);
    }else if("taken" === jsonObj.type) {
        // 地圖轉換                       
        // 用Directions Service送出request
        let directionsService = new google.maps.DirectionsService();
        // 得到res開始畫路線
        let directionsDisplay = new google.maps.DirectionsRenderer();

        const map = new google.maps.Map(document.getElementById("map"), {
          zoom: 15,
          center: resAddress,
          zoomControl: false,
          mapTypeControl: false,
          scaleControl: false,
          streetViewControl: false,
          rotateControl: false,
          fullscreenControl: false
        });
        const marker = new google.maps.Marker({
          position: resAddress,
          animation: google.maps.Animation.BOUNCE,
          map: map,
        });

        // 放置路線圖層
        directionsDisplay.setMap(map);             

        // 設置參數,繪製路線圖
        // directionsService.route(directionsRequest, callback);
        var markers = [];
        var infowindows = [];
        directionsService.route(
          {
            origin: resAddress ,
            destination: orderList.address ,  //目的地到時候要串使用者及商家位置
            travelMode: "DRIVING",
            unitSystem: google.maps.UnitSystem.METRIC,
            // drivingOptions: pessimistic,
            avoidFerries: true ,
            avoidHighways: true ,
            avoidTolls: true 
          },
          (response, status) => {
            if(status == "OK"){
              // 取得時間
              let delTime = response.routes[0].legs[0].duration.text;
              // console.log(delTime);          

              // 首頁資訊
              $("#meal-status").text(`請前往${orderList.address}送餐點。預估所需時間${delTime}`);
              
              directionsDisplay.setDirections(response);
            }else{
              console.log(status);
            }
          }
        );                                              
    }

};  

$(".btnOrderFinish").on("click",function(){
  console.log("按下btn")
  var jsonObj = {
    type: "orderFinish",
    sender: delId,
    receiver: userId+"2",
    message: "訂單已完成",
  };
  webSocket.send(JSON.stringify(jsonObj));
  console.log("發出去的訊息 = ",jsonObj);
})

$(".send_message").on("click",function(){
  var jsonObj = {
    type: "delSend",
    sender: delId,
    receiver: userId+"2",
    message: $(".message_input").val(),
  };
  webSocket.send(JSON.stringify(jsonObj)); 
  // console.log("發出去的訊息 = ",jsonObj);

  appendNewMsg(true, $(".message_input").val());
  $(".message_input").val("");
});

$(".message_input").on("keyup",function(e){
  if(e.which==13){
    $(".send_message").click();
  };
});

webSocket.onclose = function(event) {
  // console.log("商家連線斷囉Disconnected!");
};


// //google map api
// // 抓外送員即時位置
let delLng = "";
let delLat = "";

// 初始化地圖
function initMap() {       
  
  // 用Directions Service送出request
  let directionsService = new google.maps.DirectionsService();
  // 得到res開始畫路線
  let directionsDisplay = new google.maps.DirectionsRenderer();

  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(
      function (position) {
        // console.log(position);
        delLng = position.coords.longitude; // 經度
        delLat = position.coords.latitude; // 緯度

        const delLocation = { lat: delLat, lng: delLng };
        // console.log(delLng);
        // console.log(delLat);

        const map = new google.maps.Map(document.getElementById("map"), {
          zoom: 15,
          center: delLocation,
          zoomControl: false,
          mapTypeControl: false,
          scaleControl: false,
          streetViewControl: false,
          rotateControl: false,
          fullscreenControl: false
        });
        const marker = new google.maps.Marker({
          position: delLocation,
          animation: google.maps.Animation.BOUNCE,
          map: map,
        });

        // 放置路線圖層
        directionsDisplay.setMap(map);             

        // 設置參數,繪製路線圖
        // directionsService.route(directionsRequest, callback);
        var markers = [];
        var infowindows = [];
        directionsService.route(
          {
            origin: delLocation ,
            destination: resAddress ,  //目的地到時候要串使用者及商家位置
            travelMode: "DRIVING",
            unitSystem: google.maps.UnitSystem.METRIC,
            // drivingOptions: pessimistic,
            avoidFerries: true ,
            avoidHighways: true ,
            avoidTolls: true 
          },
          (response, status) => {
            if(status == "OK"){
              // console.log(response);
              
              // 取得地址
              // let delAddress = response.routes[0].legs[0].end_address;
              // console.log(delAddress);

              // 取得時間
              let delTime = response.routes[0].legs[0].duration.text;
              // console.log(delTime);          

              // 首頁資訊
              $("#meal-status").text(`請前往${resAddress}領取餐點。預估所需時間${delTime}`);
              
              directionsDisplay.setDirections(response);
            }else{
              console.log(status);
            }
          }
        );             
      },
      function (error) {
        alert(
          "使用者不同意取得位置資訊或尚未取得位置資訊：ERROR(" +
            error.code +
            "): " +
            error.message
        );
      
      }
    
    )
    
    
  };

  
};
window.initMap = initMap;