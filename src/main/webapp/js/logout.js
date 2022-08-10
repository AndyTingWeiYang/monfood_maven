window.addEventListener('load', function(){
    
    // history.go(0)
    // check login info
    $.ajax({
        url: '/monfood_maven/GetInfoServlet',
        type: 'POST',
        dataType: 'json',
        success: function(msg){
            // console.log(msg)
            if(msg == null){
                $('#logStatus').text('登入')
            }else{
                $('#logStatus').text('登出')
                // logout
                $('#logStatus').on('click', function(){
                    $.ajax({
                        url: '/monfood_maven/UserLogoutServlet',
                        type: 'POST',
                        dataType: 'json',
                        success: function(msg){
                        },
                        complete: function(){
                            window.location.replace('userLogin.html')
                            // location.href='/monfood_maven/browse.html';
                            $('#logStatus').text('登入')
                        }
                    })
                })
            }
        }
    })

})