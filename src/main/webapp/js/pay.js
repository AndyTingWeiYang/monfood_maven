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

  var data = {"cartList" : [{
    "resId" : 4,
    "resName" : '麥當勞-台北民生店',
    "productID" : 1,
    "productName" : '雙層牛肉吉士堡',
    "amount" : 5,
    "productPrice" : 65,
    "productKcal" : 467
  },
  {
    "resId" : 4,
    "resName" : '麥當勞-台北民生店',
    "amount" : 5,
    "productID" : 2,
    "productName" : '大麥克',
    "productPrice" : 75,
    "productKcal" : 548
  }]
  };

  sessionStorage.setItem('cartList', JSON.stringify(data));
  
  $('.submit').on('click', function(){

    let cartList = JSON.parse(sessionStorage.getItem('cartList'));
    console.log(cartList.cartList[0])

  })
  // RES_ID, NOTE, USER_LOCATION, PRODUCT_KCAL_TOTAL, TOTAL, DEL_COST, USE_CASH, CREDIT_ID, DISCOUNT, PROMOTE_ID






})