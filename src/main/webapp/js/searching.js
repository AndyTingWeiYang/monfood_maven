window.addEventListener('load', function(){

    let orderList = JSON.parse(sessionStorage.getItem('orderList'));
    console.log(orderList)
    $('#orderId').text(orderList.orderId);
    $('#monsPic').attr('src', orderList.monsPic);
    $('#duration').text(orderList.delTime);
    $('#address').text(orderList.address)


})