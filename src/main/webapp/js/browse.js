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

// navigator.geolocation.getCurrentPosition(function(position) {
//   do_something(position.coords.latitude, position.coords.longitude);
// });


// google
window.addEventListener('load', function(){

  // get saved location
  $.ajax({
    url: 'GetAllLocation',
    type: 'POST',
    dataType: 'json',
    success: function(msg){
      console.log(msg)
      $.each(msg.locations, function(index, data){
        let loc_list = `
          <li class="d-flex justify-content-between align-items-center">
            <span class="addressBtn">${this.location}</span>
            <button class="btn addressConfirm" data-bs-dismiss="offcanvas" aria-label="Close">確認</button>
          </li>
        `
        $('#userLocation').append(loc_list);
      })
    },
    error: function(errMsg){
    }
  })

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
    destination: mylatlng,
    travelMode: 'DRIVING'
  };

    
  // 繪製路線
  // directionsService.route(request, function (result, status) {
  //   if (status == 'OK') {
  //     // 回傳路線上每個步驟的細節
  //     directionsDisplay.setDirections(result);
  //     // 路程時間
  //     var duration = parseInt(result.routes[0].legs[0].duration.value);
  //     // 目前時間
  //     var today = new Date();
  //     // 轉為毫秒數
  //     var t_s = today.getTime();
      
  //     var time = new Date();
  //     time.setTime(t_s + duration*1000 + 15*60*1000); // 將目前時間與路程時間加總 (加上預設餐點時間15分鐘)
  //     var arrival = time.getHours() + ":" + time.getMinutes(); // 格式化
  //     document.getElementById("duration").innerText = arrival;
      
  //   } else {
  //     // console.log(status);
  //   }
  // });
  
    
  // 輸入地址按下確認更改位置
  var options = {
    types: ['geocode']
  }
  var input = document.getElementById('address');
  var autocomplete = new google.maps.places.Autocomplete(input, options);
  
  google.maps.event.addListener(autocomplete, 'place_changed', function (){

    document.querySelector('button.btn.textConfirm').addEventListener('click', function(){
      document.getElementById('finalAddress').innerText = document.getElementById('address').value
        // insert location for user
        if($('#address').val().trim() != null || $('#address').val().trim() != ''){

          $.ajax({
            url: 'InsertLocation',
            type: 'POST',
            data: JSON.stringify({
              location: $('#address').val()
            }),
            dataType: 'json',
            success: function(msg){
            },
            error: function(errMsg){
            }
          })
        }
      // 路線相關設定
      var request = {
        origin: res,
        destination: document.getElementById('finalAddress').innerText,
        travelMode: 'DRIVING'
      };
      
      
      // 繪製路線
    // directionsService.route(request, function (result, status) {
    //   if (status == 'OK') {
    //     // 回傳路線上每個步驟的細節
    //     directionsDisplay.setDirections(result);
    //     // 路程時間
    //     var duration = parseInt(result.routes[0].legs[0].duration.value);
    //     // 目前時間
    //     var today = new Date();
    //     // 轉為毫秒數
    //     var t_s = today.getTime();
        
    //     var time = new Date();
    //     time.setTime(t_s + duration*1000 + 15*60*1000); // 將目前時間與路程時間加總 (加上預設餐點時間15分鐘)
    //     var arrival = time.getHours() + ":" + time.getMinutes(); // 格式化
    //     document.getElementById("duration").innerText = arrival;
        
    //   } else {
    //     // console.log(status);
    //   }
    // });
    
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
  sessionStorage.setItem('location','browse');
})

//以上勿動//

//搜尋
$('#searchPdt').keypress(function(e){
  const resId = $("#searchPdt").val();
    if (e.which == 13) {
      console.log('hi');
      $.ajax({
          url: 'ResSearchProductServlet',
          type: 'POST',
          dataType: 'JSON',
          data: {
            searchPdt: resId
          },

          success: function(data){
            $('#resPageBlock').html('');
            const resList = data.resList;

            for(let i = 0; i < resList.length; i++) {
              const resVo = resList[i];

              if(resVo.resCategory==1){
                  resVo.resCategory = "台式";
                }else if(resVo.resCategory==2){
                  resVo.resCategory = "日式";
                }else if(resVo.resCategory==3){
                  resVo.resCategory = "泰式";
                }else if(resVo.resCategory==4){
                  resVo.resCategory = "美式";
                }else if(resVo.resCategory==5){
                  resVo.resCategory = "韓式";
                }else if(resVo.resCategory==6){
                  resVo.resCategory = "飲料咖啡";
                }else if(resVo.resCategory==7){
                  resVo.resCategory = "越式";
                }

              let resPageHtml = `
                <a id="resPage" 
                  class="col-xl-4 col-lg-6 col-sm-12 col d-flex justify-content-center mb-5" 
                  href="restaurant.html?resID=${resVo.resId}">
                </a>
              `;

              let resPageContentHtml = `
              <div class="btn btn-primary monfood-resBlock">
                <div class="monfood-resPicdiv" >
                 <img id="resPic" src="/monfood_maven/resprofile/ResPhotoPreviewServlet?resID=${resVo.resId}" class="mt-1 monfood-respic"/>
              </div>
              <div class="row justify-content-between monfood-resNameandRatingdiv" >
                  <div id="monfood-resName" class="col-md-9 d-flex monfood-resName">${resVo.resName}</div>
                  <div id="rating" class="col-md-3 monfood-rating">${resVo.rate}</div>
              </div>
              <div class="row justify-content-between monfood-resCategorydiv">
                  <div class="col-md-6 d-flex"></div>
                  <div id="resCategory" class="col-md-6 monfood-resCategory">${resVo.resCategory}</div>
              </div>
              </div>
            `;
               // resPageHtml<a> 新增內容
          const contentHtml = $(resPageHtml).append($('<div>').append(resPageContentHtml));
    
          $('#resPageBlock').append(contentHtml);
        }
      }
    });
  }
});
// 以上為測試
//餐廳篩選
$('.restriction').click(function(){
  const resId = $(this).data('rescategory');
  $.ajax({
    url: 'ResFindByCategoryServlet',
    type: 'POST',
    dataType: 'json',
    data: {
      resCat: resId
    },
    success: function(data){
      $('#resPageBlock').html('');
        const resList = data.resList;
        debugger
        console.log('aaaaaaaaaa');
        console.log(resList.rescategory);
        console.log('aaaaaaaaaaaa');

        for(let i = 0; i < resList.length; i++) {
		    const resVo = resList[i];

        if(resVo.resCategory==1){
          resVo.resCategory = "台式";
          }else if(resVo.resCategory==2){
            resVo.resCategory = "日式";
          }else if(resVo.resCategory==3){
            resVo.resCategory = "泰式";
          }else if(resVo.resCategory==4){
            resVo.resCategory = "美式";
          }else if(resVo.resCategory==5){
            resVo.resCategory = "韓式";
          }else if(resVo.resCategory==6){
            resVo.resCategory = "飲料咖啡";
          }else if(resVo.resCategory==7){
            resVo.resCategory = "越式";
          }

            let resPageHtml = `
                <a id="resPage" 
                  class="col-xl-4 col-lg-6 col-sm-12 col d-flex justify-content-center mb-5" 
                  href="restaurant.html?resID=${resVo.resId}">
                  <span id=${resVo.resId}></span>
                </a>

              `;
  
            let resPageContentHtml = `
                <div class="btn btn-primary monfood-resBlock">
                  <div class="monfood-resPicdiv" >
                   <img id="resPic" src="/monfood_maven/resprofile/ResPhotoPreviewServlet?resID=${resVo.resId}" class="mt-1 monfood-respic"/>
                </div>
                <div class="row justify-content-between monfood-resNameandRatingdiv" >
                    <div id="monfood-resName" class="col-md-9 d-flex monfood-resName">${resVo.resName}</div>
                    <div id="rating" class="col-md-3 monfood-rating">${resVo.rate}</div>
                </div>
                <div class="row justify-content-between monfood-resCategorydiv">
                    <div class="col-md-6 d-flex"></div>
                    <div id="resCategory" class="col-md-6 monfood-resCategory">${resVo.resCategory}</div>
                </div>
                </div>
              `;
          
          // resPageHtml<a> 新增內容
          const contentHtml = $(resPageHtml).append($('<div>').append(resPageContentHtml));
    
          $('#resPageBlock').append(contentHtml);
        }
      }
    });
  });
  // TODO: 餐廳顯示
  $(document).ready(function() {

    sessionStorage.setItem('location','browse');


    $.ajax({
      url: 'ResGetRateServlet',
      type: 'POST',
      dataType: 'json',
      
      success: function(data) {
        const resList = data.resList;
        let zipCodeSession = sessionStorage.getItem("zipcode");
        let length = resList.length;
        if(zipCodeSession != null){
          for(let i=0; i < length; i++){
            if(resList[i].zipCode == zipCodeSession){
                const resVo = resList[i];
                var resCat;
      
              if(resVo.resCategory==1){
                resCat = "台式";
                }else if(resVo.resCategory==2){
                resCat = "日式";
                }else if(resVo.resCategory==3){
                resCat = "泰式";
                }else if(resVo.resCategory==4){
                resCat = "美式";
                }else if(resVo.resCategory==5){
                resCat = "韓式";
                }else if(resVo.resCategory==6){
                resCat = "飲料咖啡";
                }else if(resVo.resCategory==7){
                resCat = "越式";
                }

                let resPageHtml = `
                  <a id="resPage" 
                    class="col-xl-4 col-lg-6 col-sm-12 col d-flex justify-content-center mb-5" 
                    href="restaurant.html?resID=${resVo.resId}">
                  </a>
                `;
        
                let resPageContentHtml = `
                  <div class="btn btn-primary monfood-resBlock">
                      <div class="monfood-resPicdiv" >
                          <img id="resPic" src="/monfood_maven/resprofile/ResPhotoPreviewServlet?resID=${resVo.resId}" class="mt-1 monfood-respic"/>
                      </div>
                      <div class="row justify-content-between monfood-resNameandRatingdiv" >
                          <div id="monfood-resName" class="col-md-9 d-flex monfood-resName">${resVo.resName}</div>
                          <div id="rating" class="col-md-3 monfood-rating">${resVo.rate}分</div>
                      </div>
                      <div class="row justify-content-between monfood-resCategorydiv">
                          <div class="col-md-6 d-flex"></div>
                          <div id="resCategory" class="col-md-6 monfood-resCategory">${resCat}</div>
                      </div>
                  </div>
                `;
              
                // resPageHtml<a> 新增內容
                const contentHtml = $(resPageHtml).append($('<div>').append(resPageContentHtml));
                $('#resPageBlock').append(contentHtml);
            }
          }
        }  
        else{

       
            for(let i=0; i < length; i++){
          
            // console.log(resList[i]);
            // for(let i = 0; i < resList.length; i++) {
            const resVo = resList[i];
    
            var resCat;
    
            if(resVo.resCategory==1){
              resCat = "台式";
              }else if(resVo.resCategory==2){
              resCat = "日式";
              }else if(resVo.resCategory==3){
              resCat = "泰式";
              }else if(resVo.resCategory==4){
              resCat = "美式";
              }else if(resVo.resCategory==5){
              resCat = "韓式";
              }else if(resVo.resCategory==6){
              resCat = "飲料咖啡";
              }else if(resVo.resCategory==7){
              resCat = "越式";
              }
              // console.log(resCat);
              // console.log(resVo.resCategory);
    
              let resPageHtml = `
                <a id="resPage" 
                  class="col-xl-4 col-lg-6 col-sm-12 col d-flex justify-content-center mb-5" 
                  href="restaurant.html?resID=${resVo.resId}">
                </a>
              `;
      
              let resPageContentHtml = `
                <div class="btn btn-primary monfood-resBlock">
                    <div class="monfood-resPicdiv" >
                        <img id="resPic" src="/monfood_maven/resprofile/ResPhotoPreviewServlet?resID=${resVo.resId}" class="mt-1 monfood-respic"/>
                    </div>
                    <div class="row justify-content-between monfood-resNameandRatingdiv" >
                        <div id="monfood-resName" class="col-md-9 d-flex monfood-resName">${resVo.resName}</div>
                        <div id="rating" class="col-md-3 monfood-rating">${resVo.rate}分</div>
                    </div>
                    <div class="row justify-content-between monfood-resCategorydiv">
                        <div class="col-md-6 d-flex"></div>
                        <div id="resCategory" class="col-md-6 monfood-resCategory">${resCat}</div>
                    </div>
                </div>
              `;
            
              // resPageHtml<a> 新增內容
              const contentHtml = $(resPageHtml).append($('<div>').append(resPageContentHtml));
              $('#resPageBlock').append(contentHtml);
        
            }    
        }     
      }      
    });
            if(sessionStorage.getItem("wholeAddress")!=null){
              $("span#finalAddress").text(sessionStorage.getItem("wholeAddress"));
            }
  });

//點擊導向選擇店家
$(document).ready(function(){
  
      // 取得 queryString
      const urlParams = new URLSearchParams(window.location.search);
      const resId = urlParams.get('resID');
         if(resId) {  
      }
});

//優惠通知
$(document).ready(function(){
    $.ajax({
        url: 'ShowPromote',
        type: 'POST',
        dataType: 'JSON',
        data: {},
        
        success: function(data){

            const promoteMap = data.promoteMap;
            let notiHtml = `
                <div id="promoteBlock">輸入【 ${promoteMap.promoteCode} 】，折抵 $ ${promoteMap.promotePrice} 元 !<br />日常補貨每日三省，省錢、省事、又省力！！！</div>
            `;

            const contentHtml = $(notiHtml);
            $('#notiBlock').append(contentHtml);
        }
    });

    // 
location
$.ajax({
  url: 'GetAllLocation',
  type: 'POST',
  dataType: 'json',
  success: function(msg){
    $.each(msg.locations, function(index, data){
      let loc_list = `
        <li class="d-flex justify-content-between align-items-center">
          <span class="addressBtn">${this.location}</span>
          <button class="btn addressConfirm" data-bs-dismiss="offcanvas" aria-label="Close">確認</button>
        </li>
      `
      $('#userLocation').append(loc_list);
    })
  }
});

$('button.btn.textConfirm').on('click', function(){
  document.getElementById('finalAddress').innerText = document.getElementById('address').value
  
  // insert location for user
  if($('#address').val().trim() != null || $('#address').val().trim() != ''){

    $.ajax({
      url: 'InsertLocation',
      type: 'POST',
      data: JSON.stringify({
        location: $('#address').val()
      }),
      dataType: 'json',
      success: function(msg){
      },
      error: function(errMsg){
      }
    })
 }
});
});

