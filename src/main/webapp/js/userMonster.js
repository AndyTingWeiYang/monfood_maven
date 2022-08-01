window.addEventListener('load', function(){





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
})