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
  
})