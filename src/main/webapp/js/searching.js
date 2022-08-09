window.addEventListener('load', function(){

    if (sessionStorage.getItem('cartList') == null || sessionStorage.getItem('cartList') == '') {
        location.href = '/monfood_maven/browse.html';
        return;
    }

    let orderList = JSON.parse(sessionStorage.getItem('orderList'));
    console.log(orderList)
    $('#orderId').text(orderList.orderId);
    $('#monsPic').attr('src', orderList.monsPic);
    $('#duration').text(orderList.delTime);
    $('#address').text(orderList.address)


})