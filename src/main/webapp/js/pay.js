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

  // radio標籤
  $('#cash').on('click', function(){
      $('#cash').attr('checked', 'checked');
      $('#creditcard').removeAttr('checked');    
  })

  $('#creditcard').on('click', function(){
      $('#creditcard').attr('checked', 'checked');
      $('#cash').removeAttr('checked');
  })

  // 小怪獸折扣
  $.ajax({
    url: 'MonsCheckServlet',
    type: 'POST',
    data: JSON.stringify({
      userId : 1,
    }),
    dataType: 'json',
    success: function(msg){
      // console.log(msg)

      let discountVal = msg.monsterInfo.discount;
      let monsLevel = msg.monsterInfo.monsLevel;
      let orderTimes = msg.orderTimes;
      let picBase64 = msg.monsPic;
      $('.discountVal').text(discountVal);
      $('.discountValBottom').text(discountVal);
      $('.monsLevelBottom').text(monsLevel);
      $('#monsPic').attr('src', 'data:image/jpg;base64,' + picBase64)
      // 總金額
      $('#total').text(parseInt($('.itemTotal').text()) + parseInt($('.delCost').text()) - parseInt($('.discountVal').text()));
      
      // 小怪獸EXP
      let levelEXP = (orderTimes % 10)+1;
      if(orderTimes >= 40){
        $('#levelEXP').css('width', `100%`).text('100%');
      }else if(orderTimes >= 10 && levelEXP == 0){
        $('#levelEXP').css('width', `100%`).text('100%');
      }else{
        $('#levelEXP').css('width', `${levelEXP}0%`).text(`${levelEXP}0%`)
      }


    },
    error: function(errMsg){
      console.log(errMsg)
    }



  })





  // 購物車假資料
  var data = {"cartList" : [{
      resId : 4,
      resName : '麥當勞-台北民生店',
      productID : 1,
      productName : '雙層牛肉吉士堡',
      amount : 5,
      productPrice : 65,
      productKcal : 467,
      // img : 'images/20190813JEF004__20190813_L.jpg'
    },
    {
      resId : 4,
      resName : '麥當勞-台北民生店',
      productID : 2,
      productName : '大麥克',
      amount : 4,
      productPrice : 75,
      productKcal : 548,
      // img : 'images/111.jpg's
    }]
  };

  // 設定假資料到session
  sessionStorage.setItem('cartList', JSON.stringify(data));

  // 從session 取得購物車假資料
  let cartData = JSON.parse(sessionStorage.getItem('cartList'));
  var itemTotal = 0;
  var kcalTotal = 0;

  // 店名
  $('.resName').text(cartData.cartList[0].resName);

  // 將購物車資料渲染至頁面
  $.each(cartData, function(){
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
            <a href="" class="">
              <div>
                <h5>${this.productName}</h5>
                <div class="itemPrice">$${this.amount * this.productPrice}</div>
                <div class="kcal">${this.productKcal * this.amount}Kcal</div>
              </div>
            </a>
          </div>
          <img src="images/20190813JEF004__20190813_L.jpg" alt="" style="height: 90px; width: 90px">
          </li>
        `
        
        // 小記金額
        itemTotal += this.amount * this.productPrice;
        // 總卡路里
        kcalTotal += this.productKcal * this.amount;
        $('.cartList').append(cartLi);
        
        // 卡路里EXP

      })
    })
  // 小記
  $('.itemTotal').text(itemTotal);
  
////////////////////////////////// 優惠代碼 //////////////////////////////////////
  var promoteId;
  $('#promoteConfirm').on('click', function(){
    // 清空span標籤
    $('#promoteMsg').text('');
    // 標籤格式驗證
    if($('.promoteText').val().trim() == '' || $('.promoteText').val().trim() == null){
      let errMsg = `
            <span id="errMsg" style="color: red; font-size:12px">*請輸入正確代碼</span>
          `
      $('#promoteMsg').append(errMsg);
      return;
    }

    // 取得input值
    let promoteCode = $('.promoteText').val();
    // 優惠碼後端驗證
    $.ajax({
      url: 'PromoteCheckServlet',
      type: 'POST',
      data: JSON.stringify({
        promoteCode : promoteCode,
      }),
      dataType: 'json',
      success: function(msg){
        console.log(msg)

        var promoteMsg = msg;
        // 無此優惠, return
        if(msg.errMsg == '無此優惠代碼' || msg.errMsg == '系統錯誤'){
          let errMsg = `
            <span id="errMsg" style="color: red; font-size:12px">*無此優惠代碼</span>
          `
          $('#promoteMsg').append(errMsg);
          return;

        }else{
          // 若優惠碼有效, 確認是否已被使用者使用
          $.ajax({
            url: 'PromoteDetailOneServlet',
            type: 'POST',
            data: JSON.stringify({
              // 送出userId & promoteId 做雙pk查詢驗證使用狀況
              userId : 1,
              promoteId : msg.promoteCode.promoteId,
              
            }),
            dataType: 'json',
            success: function(msg){
              console.log(msg)
              // 返回0代表尚未使用, 1表示已使用
              if(msg.msg == 1){
                let errMsg = `
                  <span id="errMsg" style="color: red; font-size:12px">*優惠已使用</span>
                `
                $('#promoteMsg').append(errMsg);
                return;
              }else{
                // 優惠碼未使用, 加入優惠標籤與折扣金額

                // 取得promoteId供送出訂單使用
                promoteId = promoteMsg.promoteCode.promoteId;
                let successMsg = 
                  `
                    <div>優惠代碼</div>
                    <div>$-<span id="promotePrice">${promoteMsg.promoteCode.promotePrice}</span></div>
                  `
                $('#promoteMsg').append(successMsg);
                $('#total').text(parseInt($('.itemTotal').text()) + parseInt($('.delCost').text()) - parseInt($('.discountVal').text()) - parseInt($('#promotePrice').text()));
                $('.promoteText').attr('disabled', true);
                $('#promoteConfirm').attr('disabled', true);
              }
            },
            error: function(errMsg){
              console.log(errMsg)
            }
          })
        }
      },
      error: function(errMsg){
        console.log(errMsg)
      }
    })
  })

  // 記帳EXP

////////////////////////////////// 送出訂單 //////////////////////////////////////
  $('.submit').on('click', function(){
    let cartList = JSON.parse(sessionStorage.getItem('cartList'));

    // 未使用優惠券
    if($('#promotePrice').text() == ''){
      $.ajax({
        url: 'OrderServlet',
        type: 'POST',
        data: JSON.stringify({
          
          userId : 1,
          resId : cartList.cartList[0].resId,
          note : $('.note').val().trim(),
          userLocation : $('#finalAddress').text(),
          productKcalTotal : kcalTotal,
          total : $('#total').text(),
          delCost : $('.delCost').text(),
          useCash : ($('input[name=pay]:checked').val() == 'cash'? true : false),
          creditId : ($('input[name=pay]:checked').val() == 'cash'? '' : '1234567812345678'),
          discount : ($('#promotePrice').text() == '' ? 0 : parseInt($('#promotePrice').text())),
          
        }),
        dataType: 'json',
        success: function(msg){
          // 訂單主檔建立成功, 新增訂單明細
          $.each(cartList, function(){
            $.each(this, function(index, detail){
              $.ajax({
                url: 'OrderDetailServlet',
                type: 'POST',
                data: JSON.stringify({
                  orderId : msg.OrderId,
                  productId : detail.productID,
                  amount : detail.amount,
                  orderedPrice : detail.productPrice
                }),
                dataType: 'json',
                success: function(msg){
   
                },
                error: function(errMsg){

                }
              });
            })
          })

          // 將部分訂單資訊存入session供後頁面使用
          let orderList = {
            orderId : msg.OrderId,
            delTime: $('#duration').text(),
            address: $('#finalAddress').text(),
            total: $('#total').text(),
            payBy: ($('input[name=pay]:checked').val() == 'cash'? '現金' : '信用卡'),
            monsPic: $('#monsPic').attr('src')
          };
          sessionStorage.setItem('orderList', JSON.stringify(orderList));
          location.href="searching.html"
        },
        error: function(errMsg){
          // console.log(errMsg)
        }
      });
    }else{

      // 使用優惠券
      $.ajax({
        url: 'OrderServlet',
        type: 'POST',
        data: JSON.stringify({
          
          userId : 1,
          resId : cartList.cartList[0].resId,
          note : $('.note').val().trim(),
          userLocation : $('#finalAddress').text(),
          productKcalTotal : kcalTotal,
          total : $('#total').text(),
          delCost : $('.delCost').text(),
          useCash : ($('input[name=pay]:checked').val() == 'cash'? true : false),
          creditId : ($('input[name=pay]:checked').val() == 'cash'? '' : '1234567812345678'),
          discount : ($('#promotePrice').text() == '' ? 0 : parseInt($('#promotePrice').text())),
          promoteId : promoteId
          
        }),
        dataType: 'json',
        success: function(msg){
          // 訂單主檔建立成功, 新增訂單明細
          $.each(cartList, function(){
            $.each(this, function(index, detail){
              $.ajax({
                url: 'OrderDetailServlet',
                type: 'POST',
                data: JSON.stringify({
                  orderId : msg.OrderId,
                  productId : detail.productID,
                  amount : detail.amount,
                  orderedPrice : detail.productPrice
                }),
                dataType: 'json',
                success: function(msg){

                },
                error: function(errMsg){

                }
              });
            })
          })
        
          // 將部分訂單資訊存入session供後頁面使用
          let orderList = {
            orderId : msg.OrderId,
            delTime: $('#duration').text(),
            address: $('#finalAddress').text(),
            total: $('#total').text(),
            payBy: ($('input[name=pay]:checked').val() == 'cash'? '現金' : '信用卡'),
            monsPic: $('#monsPic').attr('src')
          };
          sessionStorage.setItem('orderList', JSON.stringify(orderList));
          location.href="searching.html"
        
        
        },
        error: function(errMsg){
          // console.log(errMsg)
        }
      });

      // 記錄優惠券使用
      $.ajax({
        url: 'PromoteDetailServlet',
        type: 'POST',
        data: JSON.stringify({
          userId : 1,
          promoteId : promoteId
        }),
        dataType: 'json',
        success: function(msg){
          console.log(msg)
        },
        error: function(errMsg){
          console.log(errMsg)
        }
      })






    }

  })

  // select 標籤
  $('select').on('change', function(){
    if($(this).find('option:selected').val() == 0){
      $(this).closest('li').remove();
    }

  })

})