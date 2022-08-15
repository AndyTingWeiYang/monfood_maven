window.addEventListener('load', function(){

    if (sessionStorage.getItem('cartList') == null || sessionStorage.getItem('cartList') == '') {
        location.href = '/monfood_maven/browse.html';
        return;
    }

    let orderList = JSON.parse(sessionStorage.getItem('orderList'));
    // console.log(orderList)
    $('#orderId').text(orderList.orderId);
    $('#monsPic').attr('src', orderList.monsPic);
    $('#duration').text(orderList.delTime);
    $('#address').text(orderList.address)

    let cartList = JSON.parse(sessionStorage.getItem('cartList'));
    let resId ;
    let resName;
  
    $.each(cartList["cartList"], function( index, value ) {
        // console.log( value );
        resId = value.resId;
        resName = value.resName;
        // $.each(value,function(key,value){
        //    console.log(key ,"=",value);
        // });
    });
   
    // 推播webSocket
    // 取得userId
    let userId = orderList.userId;
    let userType = 2;
   
    // 建立連線
    let myPoint = `/ResToDel/${userId}/${userType}`;
    let host = window.location.host;
    // let path = window.location.pathname;
    // let webCtx = path.substring(0, path.indexOf("/", 1));
    let endPointURL = "wss://" + window.location.host + "/monfood_maven" + myPoint;
    console.log(endPointURL);

    let webSocket = new WebSocket(endPointURL);

    webSocket.onopen = function (event) {
        console.log(event);
    };

    // onmessage 接收到資料才會執行
    webSocket.onmessage = function (event) {
        console.log(event);
        let data = JSON.parse(event.data);
        console.log("data = ", data);
        if ("userOpen" === data.stateType) {    
            delete orderList.monsPic; // 圖片太大導致連線一直斷
            var jsonObj = {
            type: "userNotification",
            sender: userId,
            receiver: resId+"0",
            message: `您有一筆訂單`,
            cartList:cartList,
            orderList:orderList
            };
            webSocket.send(JSON.stringify(jsonObj));
            console.log("已送出推播" ,jsonObj );
        }else if ("resReject" === data.type){
            console.log("通知使用者 餐廳拒單囉");
            Swal.fire("餐廳已拒單 請重新下單");
            sessionStorage.removeItem('orderList');
            sessionStorage.removeItem('cartList');
            setTimeout("location.href='/monfood_maven/browse.html'",5000);
        }else if ("delAccept" === data.type){
            Swal.fire("您的外送員"+data.delName+'"已接單');
            sessionStorage.setItem("delName", data.delName);
            sessionStorage.setItem("delId", data.delId);
            $.ajax({
                url: '/monfood_maven/UpdateDelId',
                type: 'POST',
                data: JSON.stringify({
                    delId: data.delId,
                    orderId: orderList.orderId
                }),
                dataType: 'json',
                success: function(msg){
                    console.log(msg)
                },
                error: function(errMsg){
                    console.log(errMsg)
                }
            })
            setTimeout("location.href='/monfood_maven/status.html'",5000);
        }
    };

    webSocket.onclose = function (event) {
      console.log("使用者連線斷囉Disconnected!");
    };

   


})