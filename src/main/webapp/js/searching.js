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

    let cartList = JSON.parse(sessionStorage.getItem('cartList'));
    let resId ;
    let resName;
  
    $.each(cartList["cartList"], function( index, value ) {
        console.log( value );
        resId = value.resId;
        resName = value.resName;
        $.each(value,function(key,value){
            // console.log(key ,"=",value);
        });
    });
   

    console.log("resId = ",resId);
    console.log("resName = ",resName);
    // 推播webSocket
    // 取得userId
    let userId = orderList.userId;
    let userType = 2;
    console.log("userId = ",userId);
   
    // 建立連線
    let myPoint = `/ResToDel/${userId}/${userType}`;
    let host = window.location.host;
    let path = window.location.pathname;
    let webCtx = path.substring(0, path.indexOf("/", 1));
    let endPointURL = "wss://" + window.location.host + webCtx + myPoint;
    console.log(endPointURL);

    let webSocket = new WebSocket(endPointURL);

    webSocket.onopen = function (event) {
        alert("Connect Success!");
        console.log("使用者連線上囉Connect Success!");
        console.log(event);
    };

    // onmessage 接收到資料才會執行
    webSocket.onmessage = function (event) {
    let data = JSON.parse(event.data);
    console.log("data = ", data);
    if ("open" === data.stateType) {
        console.log(data.allUser);

        var jsonObj = {
        type: "userNotification",
        sender: userId,
        receiver: resId+"0",  // resId
        message: `您有一筆訂單`,
        cartList:cartList,
        orderList:orderList
        };
        webSocket.send(JSON.stringify(jsonObj)); // 	jsonObj改成json格式
        console.log("已送出推播" ,jsonObj );
    }else if ("resReject" === data.type){
        console.log("通知使用者 餐廳拒單囉")
        alert("餐廳已拒單");
    }
    };

    webSocket.onclose = function (event) {
      console.log("使用者連線斷囉Disconnected!");
    };

   


})