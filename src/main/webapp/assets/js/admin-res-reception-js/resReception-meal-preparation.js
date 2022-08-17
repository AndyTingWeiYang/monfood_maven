$(document).ready(function () {
	let resProtocol = window.location.protocol;
    let resHost = window.location.host;
    let resPath = window.location.pathname;
    let resWebCtx = resPath.substring(0, resPath.indexOf("/", 1));
    
    let orderList;
    let delOrder;
    // ==========yuyu========
    let orderMap;
    let resId;

    function initTemplate(data) {
        orderMap = data;

        const accordionList = `
            <div class="accordion" id="accordionExample-${data.ORDER_ID}"></div><br>
        `;

        const accordionItemHeaderHtml = `
            <h2 class="accordion-header" id="headingOne-${data.ORDER_ID}">
            <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne-${data.ORDER_ID}"
                aria-expanded="true" aria-controls="collapseOne">
                請點擊查看訂單內容
            </button>
            </h2>
        `;

        const accordionItemBodyHtml = `
        <div id="collapseOne-${data.ORDER_ID}" class="accordion-collapse collapse" aria-labelledby="headingOne-${data.ORDER_ID}"
        data-bs-parent="#accordionExample-${data.ORDER_ID}">
            <div class="accordion-body">
            <div class="mf-order-order-test">
                <div>
                <div>訂購會員:<span>${orderMap.USER_ID}</span></div>
                <div>訂單編號:<span>${orderMap.ORDER_ID}</span></div>
                </div>
                <div>
                <div><span>${orderMap.RES_NAME}</span>(外送)</div>
                <div><span>${orderMap.BZ_LOCATION}</span></div>
                </div>
                <div>
                <div>訂購日期:<span>${orderMap.ORDER_CREATE}</span></div>
                </div>
            </div>
            <hr>
            <div class="mf-order-test1">
                <div class="list-group">
                <div id="productList${data.ORDER_ID}"></div>
                <div>
                    <ul class="mf-order-notes">
                    <li>訂單備註:</li>
                    <div class="mf-order-notes-1">
                        <li><span>${orderMap.NOTE}</span></li>
                    </div>
                    </ul>
                </div>
                </div>
                <div>
                <div class="mf-order-test1-2">
                    <ul class="mf-order-test1-1-1">
                    <li>統一編號:<span>${orderMap.RES_ACCOUNT}</span></li>
                    <li>總額:NT$ <span>${orderMap.TOTAL}</span></li>
                    </ul>
                </div>
                <div class="mf-order-test2-1">
                    <p>訂單狀態:</p>
                    <div class="mf-order-model-1"><span>${orderMap.ORDER_STATUS}</span></div>
                </div>
                <div>
                    <button class="delBtn btn btn-secondary" data-orderid="${data.ORDER_ID}"
                    type="button" data-bs-toggle="modal" href="#exampleModalToggle" role="button">可取餐</button>
                    </div>
                    </div>
                    </div>
                    </div>
                    </div>
                    
        `;

        const wrapperContent = $('<div>').addClass('accordion-item').append(accordionItemHeaderHtml).append(accordionItemBodyHtml);

        $('#accordionBlock').append($(accordionList).append(wrapperContent));

        // TODO: 動態塞入訂單項目 => Lambda groupby 訂單編號
        for (let j = 0; j < orderMap.productList.length; j++) {
            const product = orderMap.productList[j];
            const orderItemHtml = `
                <ul class="list-group list-group-horizontal">
                    <li class="list-group-item flex-balance"><span class="productName" name="productName">${product.PRODUCT_NAME}</span></li>
                    <li class="list-group-item flex-balance">數量:<span class="amount" name="amount">${product.AMOUNT}</span></li>
                    <li class="list-group-item flex-balance">NT$<span class="productPrice" name="productPrice">${product.PRODUCT_PRICE}</span></li>
                    <li class="list-group-item flex-balance -off"></li>
                </ul>
            `;

            $('#productList' + data.ORDER_ID).append(orderItemHtml);
        }
    }

    function init() {
        $.ajax({
            url: 'FindByOrderServlet',
            method: 'post',
            data: {
                orderStatus: 1
            },
            dataType: 'JSON',
            success: function (data) {
                // 先清空資料
                $('#accordionBlock').html('');
                orderList = data.orderList;
                if (!orderList) {
                    return;
                }

                for (let i = 0; i < orderList.length; i++) {
                    initTemplate(orderList[i]);
                }

                $('.delBtn').click(function () {
                    const orderId = $(this).data('orderid');

                    // 待外送員接單
                    delOrder = orderList.find(order => parseInt(order.ORDER_ID) === orderId);
                    console.log(delOrder);
                });

                if (resId) {
                    return;
                }
                // ===============websocket yuyu======================
                // webSocket
                resId = sessionStorage.getItem("resId");  
                let resType = "0";

                // 建立連線
                let myPoint = `/ResToDel/${resId}/${resType}`;
                let host = window.location.host;
                // let path = window.location.pathname;
                // let webCtx = path.substring(0, path.indexOf("/", 1));
                let endPointURL = "wss://" + window.location.host + "/monfood_maven" + myPoint;
                console.log(endPointURL);

                let webSocket = new WebSocket(endPointURL);

                webSocket.onopen = function (event) {
                    console.log("商家連線上囉Connect Success!");
                };

                // onmessage 接收到資料才會執行
                let delIdArr = new Array();
                let cartList;
                let resAddress;
/*這個QQ跟商家組的討論後要改掉*/let orderListQQ;
                function onmessage() {
                    webSocket.onmessage = function (event) {
                        let jsonObj = JSON.parse(event.data);
                        console.log("jsonObj = ", jsonObj);
                        if ("resOpen" === jsonObj.stateType) {
                            console.log(jsonObj.allUser);
                            // alert("onmessage 我收到後端資料囉");
                            console.log("onmessage 我收到後端資料囉");

                            // 取得delId 存進陣列
                            jsonObj.allUser.forEach(function (item) {
                                if (item.slice(-1) == "1") {
                                    delIdArr.push(item);
                                }
                            });
                        } else if ("userNotification" === jsonObj.type) {
                            console.log("收到使用者訂單jsonObj = ", jsonObj);
                            console.log(jsonObj.message);
                            Swal.fire({
                                title: jsonObj.message + "請問是否接受?",
                                showDenyButton: true,
                                allowOutsideClick: false,
                                confirmButtonText: '接單',
                                denyButtonText: '拒單',
                            }).then((result) => {
                                /* Read more about isConfirmed, isDenied below */
                                if (result.isConfirmed) {
                                    cartList = jsonObj.cartList;
                                    orderListQQ = jsonObj.orderList;
                                    Swal.fire("您已接單");

                                    // 更新商品訂單狀態
                                    $.ajax({
                                        url: 'UpdateOrderStatusServlet',
                                        method: 'post',
                                        data: {
                                            orderId: orderListQQ.orderId,
                                            orderStatus: 1
                                        },
                                        dataType: 'json',
                                        success: function (data) {
                                            // append initTemplate 訂單資料
                                            if (!data.orderList) {
                                                return;
                                            }

                                            const orderMap = data.orderList[0];
                                            orderList.push(orderMap);
                                            initTemplate(orderMap);
                                            
                                            $('.delBtn').unbind('click').click(function () {
							                    const orderId = $(this).data('orderid');
							
							                    // 待外送員接單
							                    delOrder = orderList.find(order => parseInt(order.ORDER_ID) === orderId);
							                    console.log(delOrder);
							                });
                                        }
                                    });

                                } else if (result.isDenied) {
                                    Swal.fire('您已拒絕接受此單')
                                    var resToUser = {
                                        type: "resReject",
                                        sender: resId,
                                        receiver: jsonObj.orderList.userId + "2",
                                        message: `${orderMap.RES_NAME}已拒絕此訂單`,
                                    };
                                    webSocket.send(JSON.stringify(resToUser)); // 	jsonObj改成json格式
                                    console.log(resToUser);
                                    
                                    // 更新商品訂單狀態
                                    $.ajax({
                                        url: 'UpdateOrderStatusServlet',
                                        method: 'post',
                                        data: {
                                            orderId: jsonObj.orderList.orderId,
                                            orderStatus: 4
                                        },
                                        success: function() {}
                                    });
                                }
                            })
                        } else if ("delReject" === jsonObj.type) {
                            console.log("通知餐廳 外送員拒單囉")
                            Swal.fire("外送員已拒單");
                        }
                    };
                }
                onmessage();


                let delId;
                $('#match').click(function () {
                    console.log("按下btn");
                    // 隨機取resId
                    delId = delIdArr[getRandomInt(delIdArr.length)];
                   
                    addListener();

                    $.ajax({
                        url: 'UpdateOrderStatusServlet',
                        method: 'post',
                        data: {
                            orderId: delOrder.ORDER_ID,
                            orderStatus: 2
                        },
                        dataType: 'json',
                        success: function () {
                            // // 觸發click事件
                            // $("match").trigger("click");
                            window.location.href = resProtocol + '//' + resHost + resWebCtx + '/admin-res-reception/resReception-Take-away-meal.jsp';
                        }
                    });
              });


                // sendMessage 將資料送至後端所以後端@onMessage事件就發生了
                function addListener() {
                    var jsonObj = {
                        type: "resNotification",
                        sender: resId,
                        receiver: delId,
                        message: `有一筆外送單來自：${orderMap.RES_NAME}`,
                        cartList: cartList,
                        orderList: orderListQQ,
                        resAddress: orderMap.BZ_LOCATION
                    };
                    webSocket.send(JSON.stringify(jsonObj)); // 	jsonObj改成json格式
                    console.log(jsonObj);
                }


                webSocket.onclose = function (event) {
                    console.log("商家連線斷囉Disconnected!");
                };

                // 取亂數
                function getRandomInt(max) {
                    return Math.floor(Math.random() * max);
                }
                // ===============^^websocket yuyu^^======================
            }
        });
    }

    init();
});