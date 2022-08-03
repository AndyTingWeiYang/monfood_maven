window.addEventListener('load', function(){

    // order
    $.ajax({
        url: '/monfood_maven/OrderAllUserServlet',
        type: 'POST',
        data: JSON.stringify({
            userId : 1
        }),
        dataType: 'json',
        success: function(msg){
            console.log(msg)
            
        },
        error: function(errMsg){
            console.log(errMsg)
        }
    })
    
    // 重要性的星號
    $('.col').on('click', '.star', function(){
        console.log(this)
        let current_star = parseInt($(this).attr('data-star'));
        $(this).closest('.star_block').find('.star').each(function(i, item){
            if(parseInt($(this).attr('data-star')) <= current_star){
                $(this).addClass('-on');
            }else{
                $(this).removeClass('-on');
            }
        })
        
    });

})