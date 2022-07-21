$(function(){

    
    const orderId = $('#orderId');
    
    $('#submit').on('click', function(){
         console.log(orderId.val());
        $.ajax({
            url: 'OrderServlet',
            type: 'POST',
            data: {'orderId' : orderId.val()},
            dataType: 'json',
            success: function(data){
                console.log(data);
            }






        })
    })
    
})
