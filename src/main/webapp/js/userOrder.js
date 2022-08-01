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