$(function () {
  $(".flip").click(function () {
    $(".panel").slideToggle("slow");
    $(".xs1").toggle();
    $(".xs2").toggle();
  });
});

//   

function showPopup() {
  var overlay = document.getElementById("overlay");
  overlay.style.display = "block";
}
function hidePopup() {
  var overlay = document.getElementById("overlay");
  overlay.style.display = "none";
}

$(document).ready(function() {
  $('#change_add_1').click(function() {
      $('#curr_add').html('台北市中正區濟南路一段321號');
      
  })
});

$(document).ready(function() {
  $('#change_add_2').click(function() {
      $('#curr_add').html('新北市新莊區新泰路100號');
  })
});

navigator.geolocation.getCurrentPosition(function(position) {
  do_something(position.coords.latitude, position.coords.longitude);
});


// google
window.addEventListener('load', function(){

  var mylatlng = { lat: 25.0393131, lng: 121.3871121 };
  var res = {lat: 25.0444475, lng:121.5212073};

  var directionsService = new google.maps.DirectionsService();
  var directionsDisplay = new google.maps.DirectionsRenderer();

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
      time.setTime(t_s + duration*1000 + 15*60*1000); // 將目前時間與路程時間加總 (加上預設餐點時間15分鐘)
      var arrival = time.getHours() + ":" + time.getMinutes(); // 格式化
      document.getElementById("duration").innerText = arrival;
      
    } else {
      // console.log(status);
    }
  });
  
    
  // 輸入地址按下確認更改位置
  var options = {
    types: ['geocode']
  }
  var input = document.getElementById('address');
  var autocomplete = new google.maps.places.Autocomplete(input, options);
  
  google.maps.event.addListener(autocomplete, 'place_changed', function (){

    // var place = autocomplete.getPlace();
    // var lat = place.geometry.location.lat();
    // var lng = place.geometry.location.lng();
    // mylatlng = { lat: lat, lng: lng }

    document.querySelector('button.btn.textConfirm').addEventListener('click', function(){
      document.getElementById('finalAddress').innerText = document.getElementById('address').value
      
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
        time.setTime(t_s + duration*1000 + 15*60*1000); // 將目前時間與路程時間加總 (加上預設餐點時間15分鐘)
        var arrival = time.getHours() + ":" + time.getMinutes(); // 格式化
        document.getElementById("duration").innerText = arrival;
        
      } else {
        // console.log(status);
      }
    });
    
  })
})
  
  // 按下按鈕地圖路線變更為已儲存地址
  var button = document.querySelectorAll('button.addressConfirm');
  // console.log(button)
  for(i of button){
    i.addEventListener('click',  function(){
      document.getElementById('finalAddress').innerText = this.parentNode.children[0].innerText;
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
        time.setTime(t_s + duration*1000 + 15*60*1000); // 將目前時間與路程時間加總 (加上預設餐點時間15分鐘)
        var arrival = time.getHours() + ":" + time.getMinutes(); // 格式化
        document.getElementById("duration").innerText = arrival;
        
      } else {
        // console.log(status);
      }
    });
    })
  }
// select 標籤
$('select').on('change', function(){
  if($(this).find('option:selected').val() == 0){
    $(this).closest('li').remove();
  }
})

})

//餐廳
$(document).ready(function() {
  $("#btn").click(function() { //ID 為 submitExample 的按鈕被點擊時
      $.ajax({
          type: "POST", //傳送方式
          url: "AdminResAllServlet", //傳送目的地
          dataType: "json", //資料格式
          data: { //傳送資料
              ID: $("#ID").val(),
              Codename: $("#Codename").val(),
              Name: $("#Name").val()
          },
          success: function(data) {
              if (data.ID != null) { //如果後端回傳 json 資料有 ID
                  $("#demo")[0].reset(); 
                  $("#result").html(data.ID +data.Codename + data.Name);
              } else { //否則讀取後端回傳 json 資料 errorMsg 顯示錯誤訊息
                  $("#demo")[0].reset(); 
                  $("#result").html('<font color="#ff0000">' + data.errorMsg + '</font>'+ '錯誤!!');
              }
          },
          error: function(jqXHR) {
              $("#demo")[0].reset();
              $("#result").html('<font color="#ff0000">發生錯誤：' + jqXHR.status + '</font>'+ '爆了直接error');
          }
          
      })
  })        
});

