$(document).ready(function() {
    function init(){
        // 先清空資料
        $('.accordion-body').html('');
        $.ajax({
            url: 'FindByOrderServlet',
            method: 'post',
            data: {
                orderStatus: 1
            },
            dataType: 'JSON',
            success: function(data) {
                const orderList = data.orderList;
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
                    const orderMapStr=JSON.stringify(orderMap);
                    debugger
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
                            <ul class="list-group list-group-horizontal ">
                                <li class="list-group-item flex-balance"><span class="productName" name="productName">${orderMap.PRODUCT_NAME}</span></li>
                                <li class="list-group-item flex-balance">數量:<span class="amount" name="amount">${orderMap.AMOUNT}</span></li>
                                <li class="list-group-item flex-balance">NT$<span class="productPrice" name="productPrice">${orderMap.PRODUCT_PRICE}</span></li>
                                <li class="list-group-item flex-balance -off"><input type="checkbox" name="" id="">已售完</li>
                            </ul>
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
                                <button class="delBtn btn btn-secondary"
                                    data-ordermap="{${orderMapStr}}"
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

                    // TODO: 動態塞入訂單項目 => Lambda groupby 訂單編號
                    // const orderItemHtml = `
                    //   <ul class="list-group list-group-horizontal ">
                    //     <li class="list-group-item flex-balance"><span class="productName" name="productName"></span></li>
                    //     <li class="list-group-item flex-balance">數量:<span class="amount" name="amount"></span></li>
                    //     <li class="list-group-item flex-balance">NT$<span class="productPrice" name="productPrice"></span></li>
                    //     <li class="list-group-item flex-balance -off"><input type="checkbox" name="" id="">已售完</li>
                    //   </ul>
                    // `;

                    const wrapperContent = $('<div>').addClass('accordion-item').append(accordionItemHeaderHtml).append(accordionItemBodyHtml);
                    
                    $('#accordionBlock').append($(accordionList).append(wrapperContent));
                }

                $('.delBtn').click(function() {
                    // data-resid="${orderMap.resID}"
                    // data-productname="${orderMap.PRODUCT_NAME}"
                    // data-productprice="${orderMap.PRODUCT_PRICE}"
                    // data-producttotal="${orderMap.TOTAL}"
                    // data-amount="${orderMap.AMOUNT}"
                    const delObj = $(this);
                    debugger
                    const delMap = JSON.parse($(this).data('ordermap'));
                    debugger
                    // {
                    //     resID: delObj.data('resid'),
                    //     productName: delObj.data('productname'),
                    //     productPrice: delObj.data('productprice'),
                    //     productTotal: delObj.data('producttotal'),
                    //     amount: delObj.data('amount')
                    // };
                    console.log(delMap);
                    debugger

                    // 給外送員接單
                        });
                    }      
                });
            }
            init();
        // 每5秒撈取資料
    //    setInterval(function(){init; },5000);
});