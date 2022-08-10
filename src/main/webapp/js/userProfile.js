window.addEventListener('load', function(){


    const userId = $('#userId');
    const userName = $('#userName');
    const userTel = $('#userTel');
    const userEmail = $('#userEmail');
    const budget = $('#budget');
    const kcal = $('#kcal');
    const introduction = $('#introduction');
    const budgeterrMsg = $('#budgeterrMsg');
    const kcalerrMsg = $('#kcalerrMsg');
    const introductionerrMsg = $('#introductionerrMsg');
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
            
            userId.val(userProfile.userId).attr('disabled', 'disabled');
            userName.val(userProfile.userName).attr('disabled', 'disabled');
            userTel.val(userProfile.userTel).attr('disabled', 'disabled');
            userEmail.val(userProfile.userAccount).attr('disabled', 'disabled');
            budget.val(userProfile.budget);
            kcal.val(userProfile.calories);
            profilePic.attr('src', 'data:image/jpg;base64,' + msg.profilePic)
            if(userProfile.userProfile == undefined){
                introduction.val('');
                return;
            }else{
                introduction.val(userProfile.userProfile);
            }
            
            if(msg.profilePic == null || msg.profilePic == undefined){
                return;
            }
            
        },
        error: function(errMsg){
            console.log(errMsg)
        }
    })

    // input框驗證
    budget.keypress(function(e){
        if(e.which == 13){
            $('#budgetsubmit').click();
        }
    })
    budget.on('blur', function(){
        budgeterrMsg.text('');
    })
    kcal.keypress(function(e){

        if(e.which == 13){
            $('#kcalsubmit').click();
        }
    })
    kcal.on('blur', function(){
        kcalerrMsg.text('');
    })
    introduction.keypress(function(e){

        if(e.which == 13){
            $('#introductionsubmit').click();
        }
    })
    introduction.on('blur', function(){
        introductionerrMsg.text('');
    })


    // 更新消費金額
    $('#budgetsubmit').on('click', function(){
        let errMsg;
        let numCheck = /^[0-9]*$/;

        if(budget.val().trim() == null || budget.val().trim() == ''){
            errMsg = '*請輸入正確資料'
            budgeterrMsg.text(errMsg);
            return;
        }

        if(!numCheck.test(budget.val().trim())){
            errMsg = '*請輸入數字'
            budgeterrMsg.text(errMsg);
            return;
        }

        $.ajax({
            url: '/monfood_maven/UserProfileUpdateServlet',
            type: 'POST',
            data: JSON.stringify({
                // userId : 6,
                data : parseInt(budget.val().trim()),
                msg : 'budget'
            }),
            dataType: 'json',
            success: function(msg){
                // console.log(msg)
                budgeterrMsg.text(msg.msg);
                
            },
            error: function(errMsg){
                budgeterrMsg.text(errMsg.msg);
            }

        })
    })

    // 更新熱量限制
    $('#kcalsubmit').on('click', function(){
        let errMsg;
        let numCheck = /^[0-9]*$/;

        if(kcal.val().trim() == null || kcal.val().trim() == ''){
            errMsg = '*請輸入正確資料'
            kcalerrMsg.text(errMsg);
            return;
        }

        if(!numCheck.test(kcal.val().trim())){
            errMsg = '*請輸入數字'
            kcalerrMsg.text(errMsg);
            return;
        }

        $.ajax({
            url: '/monfood_maven/UserProfileUpdateServlet',
            type: 'POST',
            data: JSON.stringify({
                // userId : 6,
                data : parseInt(kcal.val().trim()),
                msg : 'kcal'
            }),
            dataType: 'json',
            success: function(msg){
                // console.log(msg)
                kcalerrMsg.text(msg.msg);
                
            },
            error: function(errMsg){
                kcalerrMsg.text(errMsg.msg);
            }

        })
    })

    // 更新自我介紹
    $('#introductionsubmit').on('click', function(){
        let errMsg;

        if(introduction.val().trim() == null || introduction.val().trim() == ''){
            errMsg = '*請輸入正確資料'
            introductionerrMsg.text(errMsg);
            return;
        }

        $.ajax({
            url: '/monfood_maven/UserProfileUpdateServlet',
            type: 'POST',
            data: JSON.stringify({
                // userId : 6,
                data : introduction.val().trim(),
                msg : 'introduction'
            }),
            dataType: 'json',
            success: function(msg){
                // console.log(msg)
                introductionerrMsg.text(msg.msg);
                
            },
            error: function(errMsg){
                introductionerrMsg.text(errMsg.msg);
            }

        })
    })


})