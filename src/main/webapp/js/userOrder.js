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

            $.each(msg.userOrders, function(index, data){
                // console.log(this)
                
                    let list = `
                            <div style="border-bottom:1px solid rgb(160, 156, 156); " class="orderList col-12 d-flex justify-content-between">
                                <div class="col-6 d-flex">
                                    <div class="col d-flex">
                                        <img class="align-self-center" style="width: 180px; height: 130px" src="" alt="">
                                    </div>
                                    <div class="col" style="padding-top: 10px;" class="productList">
                                        <div class="resName">${this.resVO.resName}</div>
                                        <div style="font-size: 14px;" >共 $<span class="total">${this.total}</span>- <span class="date">${this.orderDone}</span></div>
                                    </div>
                                </div>
                                <div class="col-3 align-self-center">
                                    <div class="col">
                                        <div>
                                            <span style="font-size: 10px;">訂單編號: <span class="orderId">${this.orderId}</span></span>
                                        </div>
                                        <div>
                                            <a style="background-color: #FF7F3F; border: none; width: 100%; margin-bottom: 25px;" class="btn btn-primary" data-bs-toggle="modal" href="#exampleModalToggle" role="button">訂單評分</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        `
                    $('#orderList').append(list);
                    // <div style="font-size: 12px;" ><span class="item">1</span>- <span class="kcal">2</span>kcal</div>


            })
            console.log(msg.orderDetail)

            $.each(msg.orderDetail, function(index, data){
                // console.log(this.orderId)
                var that = this;
                $.each($('.orderId'), function(index, data){
                    // console.log(this.innerText)

                    if(that.orderId == this.innerText){
                        let list = `<div style="font-size: 12px;" ><span class="item">${that.productVo.productName}</span>- <span class="kcal">${that.productVo.productKcal}</span>kcal</div>`;
                        let productList = $(this).closest('.orderList').find('div');
                        $(productList[2]).append(list)
                        return;
                    }
                    
                })
                
            })
            
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