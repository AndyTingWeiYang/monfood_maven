window.addEventListener('load', function(){

    // check login info
    $.ajax({
        url: '/monfood_maven/GetInfoServlet',
        type: 'POST',
        dataType: 'json',
        success: function(msg){
            if(msg == null){
                $('#logStatus').text('登入')
            }else{
                $('#logStatus').text('登出')
            }
        }
    })

    // logout
    $('#logStatus').on('click', function(){
        if ($(this).text() == '登出') {
            $.ajax({
                url: '/monfood_maven/UserLogoutServlet',
                type: 'POST',
                dataType: 'json',
                success: function(msg){
                    // location.href='/monfood_maven/del/monFoodIndex.html';
                },
                complete: function(){
                    location.href='/monfood_maven/browse.html';
                    $('#logStatus').text('登出')
                }
            })
        }   
    })

})