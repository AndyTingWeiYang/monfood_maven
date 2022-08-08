window.addEventListener('load', function(){
  const profilePic = $('#profilePic');

  // update profile pic
  $('#upload-file').on('change', function(){

      var reader = new FileReader();
      reader.readAsDataURL(this.files[0]);
      reader.addEventListener('load', function(){
          profilePic.attr('src', reader.result)
          const base64 = new String(profilePic.attr('src'));
          const picBase64 = base64.substring(23, base64.length)
          // console.log(picBase64)
          $.ajax({
              url: '/monfood_maven/UserProfileUpdatePicServlet',
              type: 'POST',
              data: JSON.stringify({
                  // userId : 6,
                  pic : picBase64
              }),
              dataType: 'json',
              success: function(msg){
                  console.log(msg)
                  
              },
              error: function(errMsg){
                  console.log(errMsg)
              }
          })
      });






  })

  // onload get user profile
  $.ajax({
      url: '/monfood_maven/UserProfileServlet',
      type: 'POST',
      data: JSON.stringify({
          // userId : 6,
      }),
      dataType: 'json',
      success: function(msg){
        // console.log(msg)
        let userProfile = msg.userProfile;
        if(msg.profilePic == null || msg.profilePic == undefined){
          return;
        }
        profilePic.attr('src', 'data:image/jpg;base64,' + msg.profilePic)

          
      },
      error: function(errMsg){
          console.log(errMsg)
      }
  })
  
  // 小怪獸折扣
  $.ajax({
    url: '/monfood_maven/MonsCheckServlet',
    type: 'POST',
    data: JSON.stringify({
      // userId : 6,
    }),
    dataType: 'json',
    success: function(msg){
      let discountVal = msg.monsterInfo.discount;
      let monsLevel = msg.monsterInfo.monsLevel;
      let orderTimes = msg.orderTimes;
      let picBase64 = msg.monsPic;
      console.log(picBase64)
      $('#discount').val(discountVal).attr('disabled', 'disabled');
      $('#monsLevel').text(monsLevel);
      $('#monsPic').attr('src', 'data:image/jpg;base64,' + picBase64)
      // 總金額
      $('#total').text(parseInt($('.itemTotal').text()) + parseInt($('.delCost').text()) - parseInt($('.discountVal').text()));
      
      // 小怪獸EXP
      let levelEXP = orderTimes % 10;
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