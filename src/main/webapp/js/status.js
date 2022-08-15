window.addEventListener('load', function(){
  
    if (sessionStorage.getItem('cartList') == null || sessionStorage.getItem('cartList') == '') {
      location.href = '/monfood_maven/browse.html';
      return;
    }

    let cartList = JSON.parse(sessionStorage.getItem('cartList'))
    let orderList = JSON.parse(sessionStorage.getItem('orderList'));
    
    var res;

    // get res location
    let resId = cartList.cartList[0].resId
    $.ajax({
      url: 'ResGetToResPage',
      type: 'POST',
      dataType: 'JSON',
      data: {
        resId: resId
      },
      success: function(data){
        res = data.resPage.bzLocation;
      },
      async: false
    })

    var directionsService = new google.maps.DirectionsService();
    var directionsDisplay = new google.maps.DirectionsRenderer();

    $('.resName').text(cartList.cartList[0].resName);
    $('#finalAddress').text(orderList.address);
    $('#total').text(orderList.total)
    $('#payBy').text(orderList.payBy)


    // 初始化地圖
    map = new google.maps.Map(document.getElementById('map'), {
      zoom: 16,
      center: res
    });
    
    // 放置路線圖層
    directionsDisplay.setMap(map);
    
    // 路線相關設定
    var request = {
      origin: res,
      destination: document.getElementById('finalAddress').innerText,
      travelMode: 'DRIVING'
    };
  
      
    // 繪製路線
    directionsService.route(request, function (result, status) {
      if (status == 'OK') {
        // 回傳路線上每個步驟的細節
        directionsDisplay.setDirections(result);
        // 路程時間
        var duration = parseInt(result.routes[0].legs[0].duration.value);
        // 目前時間
        var today = new Date();
        // 轉為毫秒數
        var t_s = today.getTime();
        
        var time = new Date();
        time.setTime(t_s + duration*1000); // 將目前時間與路程時間加總 (加上預設餐點時間15分鐘)
        var arrival = time.getHours() + ":" + time.getMinutes(); // 格式化
        document.getElementById("duration").innerText = arrival;
        
      } else {
        // console.log(status);
      }
    });
  
    // 將購物車資料渲染至頁面
  $.each(cartList, function(){
    $.each(this, function(index, data){
      let cartLi = 
        `
          <li class="d-flex justify-content-between" style="border-bottom: 1px solid rgb(200, 199, 199); padding-bottom: 10px">
          <div class="d-flex">
            <div style="margin-right: 10px;">
              <select disabled class="form-select form-select-sm" aria-label=".form-select-sm example">
                <option class="cartOption" value="0"${this.amount == 0 ? 'selected': ''}>移除</option>
                <option class="cartOption" value="1"${this.amount == 1 ? 'selected': ''}>1</option>
                <option class="cartOption" value="2"${this.amount == 2 ? 'selected': ''}>2</option>
                <option class="cartOption" value="3"${this.amount == 3 ? 'selected': ''}>3</option>
                <option class="cartOption" value="4"${this.amount == 4 ? 'selected': ''}>4</option>
                <option class="cartOption" value="5"${this.amount == 5 ? 'selected': ''}>5</option>
                <option class="cartOption" value="6"${this.amount == 6 ? 'selected': ''}>6</option>
                <option class="cartOption" value="7"${this.amount == 7 ? 'selected': ''}>7</option>
                <option class="cartOption" value="8"${this.amount == 8 ? 'selected': ''}>8</option>
                <option class="cartOption" value="9"${this.amount == 9 ? 'selected': ''}>9</option>
                <option class="cartOption" value="10"${this.amount == 10 ? 'selected': ''}>10</option>
                <option class="cartOption" value="11"${this.amount == 11 ? 'selected': ''}>11</option>
                <option class="cartOption" value="12"${this.amount == 12 ? 'selected': ''}>12</option>
                <option class="cartOption" value="13"${this.amount == 13 ? 'selected': ''}>13</option>
                <option class="cartOption" value="14"${this.amount == 14 ? 'selected': ''}>14</option>
                <option class="cartOption" value="15"${this.amount == 15 ? 'selected': ''}>15</option>
                <option class="cartOption" value="16"${this.amount == 16 ? 'selected': ''}>16</option>
                <option class="cartOption" value="17"${this.amount == 17 ? 'selected': ''}>17</option>
                <option class="cartOption" value="18"${this.amount == 18 ? 'selected': ''}>18</option>
                <option class="cartOption" value="19"${this.amount == 19 ? 'selected': ''}>19</option>
                <option class="cartOption" value="20"${this.amount == 20 ? 'selected': ''}>20</option>
                
              </select>
            </div>
            <div>
              <h5>${this.productName}</h5>
              <div class="itemPrice" style="font-size: 12px">$${this.amount * this.productPrice}</div>
              <div class="kcal" style="font-size: 12px">${this.productKcal * this.amount}Kcal</div>
            </div>
          </div>
          <img src="/monfood_maven/resprofile/ProductPicServlet?productID=${this.productID}" alt="" style="height: 90px; width: 90px">
          </li>
        `
        
        $('.cartList').append(cartLi);

      })
    })
    //chatroom
    function appendNewMsg(isMe, msg) {
      let messagesArea = document.querySelector(".messages");
      let li = document.createElement("li");
      li.className += isMe ? "me" : "friend";
      li.innerHTML = msg;
      messagesArea.appendChild(li);
      messagesArea.scrollTop = messagesArea.scrollHeight;
    }

    // WebScoket
    let delName = (sessionStorage.getItem('delName')+'"').slice(1,-1);
    let delId = sessionStorage.getItem('delId');
    let userId = orderList.userId;
    let userType = "2";

    $(".title").text(delName);

    // 建立連線
    let myPoint = `/ResToDel/${userId}/${userType}`;
    let host = window.location.host;
    // let path = window.location.pathname;
    // let webCtx = path.substring(0, path.indexOf("/", 1));
    let endPointURL = "wss://" + window.location.host + "/monfood_maven" + myPoint;
    console.log(endPointURL);

    let webSocket = new WebSocket(endPointURL);

    webSocket.onopen = function(event) {
      // console.log("使用者連線上囉Connect Success!");					
    };
    
    webSocket.onmessage = function(event) {
      let jsonObj = JSON.parse(event.data);
      // console.log("onmessage收到的訊息 = ", jsonObj);
      if ("delSend" === jsonObj.type){      
        let message = jsonObj.message;
        let isMe = jsonObj.sender === userId;
        appendNewMsg(isMe, message);
      }else if("orderFinish" === jsonObj.type){
        Swal.fire("訂單完成");
        sessionStorage.removeItem('orderList');
        sessionStorage.removeItem('cartList');
        setTimeout("location.href='/monfood_maven/browse.html'",5000);
        console.log("收到的訊息 = ",jsonObj);
      }
    };  

    $(".send_message").on("click",function(){
      var jsonObj = {
        type: "userSend",
        sender: userId,
        receiver: delId+"1",
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


});