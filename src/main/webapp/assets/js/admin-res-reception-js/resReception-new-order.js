$(document).ready(function() {
    let orderList;
    let delOrder;
    let intervalId;

    function init() {
        $.ajax({
            url: 'FindByOrderServlet',
            method: 'post',
            data: {
                orderStatus: 0
            },
            dataType: 'JSON',
            success: function(data) {
                // 先清空資料
                $('#accordionBlock').html('');
                orderList = data.orderList;
                if(!orderList) {
                    return;
                }

                for(let i = 0; i < orderList.length; i++) {
                    const orderMap = orderList[i];

                    const accordionList = `
                        <div class="accordion" id="accordionExample-${i}"></div><br>
                    `;

                    const accordionItemHeaderHtml = `
                        <h2 class="accordion-header" id="headingOne-${i}">
                        <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne-${i}"
                            aria-expanded="true" aria-controls="collapseOne">
                            請點擊查看訂單內容
                        </button>
                        </h2>
                    `;

                    const accordionItemBodyHtml = `
                    <div id="collapseOne-${i}" class="accordion-collapse collapse" aria-labelledby="headingOne-${i}"
                    data-bs-parent="#accordionExample-${i}">
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
                            <div id="productList${i}"></div>
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
                                <button class="delBtn btn btn-secondary" data-index="${i}"
                                type="button" data-bs-toggle="modal" href="#exampleModalToggle" role="button">可取餐</button>
                                </div>
                                </div>
                                </div>
                                </div>
                                </div>
                                
                    `;
                                    
                                    //上方原本變數
                                    // data-resid="${orderMap.RES_ID}"
                                    // data-productname="${orderMap.PRODUCT_NAME}"
                                    // data-productprice="${orderMap.PRODUCT_PRICE}"
                                    // data-producttotal="${orderMap.TOTAL}"
                                    // data-amount="${orderMap.AMOUNT}"


                    const wrapperContent = $('<div>').addClass('accordion-item').append(accordionItemHeaderHtml).append(accordionItemBodyHtml);
                    
                    $('#accordionBlock').append($(accordionList).append(wrapperContent));

                    // TODO: 動態塞入訂單項目 => Lambda groupby 訂單編號
                    for(let j = 0; j < orderMap.productList.length; j++) {
                        const product = orderMap.productList[j];
                        const orderItemHtml = `
                            <ul class="list-group list-group-horizontal">
                                <li class="list-group-item flex-balance"><span class="productName" name="productName">${product.PRODUCT_NAME}</span></li>
                                <li class="list-group-item flex-balance">數量:<span class="amount" name="amount">${product.AMOUNT}</span></li>
                                <li class="list-group-item flex-balance">NT$<span class="productPrice" name="productPrice">${product.PRODUCT_PRICE}</span></li>
                                <li class="list-group-item flex-balance -off"></li>
                            </ul>
                        `;

                        $('#productList' + i).append(orderItemHtml);
                    }
                }

                $('.delBtn').click(function() {
                    const index = $(this).data('index');
                    console.log(orderList[index]);
                    
                    // 待外送員接單
                    delOrder = orderList[index];
                });
            }      
        });
    }

    init();

   $(document).mouseover(function() {
       clearInterval(intervalId);
   });

   $(document).mouseleave(function() {
       // 每5秒撈取資料
       clearInterval(intervalId);
       intervalId = setInterval(init, 5000);
   });
});