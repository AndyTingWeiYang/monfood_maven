window.addEventListener('load', function(){
    const profilePic = $('#profilePic');

    // update profile pic
    $('#upload-file').on('change', function(){

        var reader = new FileReader();
        reader.readAsDataURL(this.files[0]);
        reader.addEventListener('load', function(){
            profilePic.attr('src', reader.result)
            const base64 = new String(profilePic.attr('src'));
            const picBase64 = base64.substring(23, base64.length)
            // console.log(picBase64)
            $.ajax({
                url: '/monfood_maven/UserProfileUpdatePicServlet',
                type: 'POST',
                data: JSON.stringify({
                    // userId : 1,
                    pic : picBase64
                }),
                dataType: 'json',
                success: function(msg){
                    console.log(msg)
                    
                },
                error: function(errMsg){
                    console.log(errMsg)
                }
            })
        });






    })

    // onload get user profile
    $.ajax({
        url: '/monfood_maven/UserProfileServlet',
        type: 'POST',
        data: JSON.stringify({
            // userId : 1,
        }),
        dataType: 'json',
        success: function(msg){
            // console.log(msg)
            let userProfile = msg.userProfile;
            if(msg.profilePic == null || msg.profilePic == undefined){
                return;
            }
            profilePic.attr('src', 'data:image/jpg;base64,' + msg.profilePic)
            
        },
        error: function(errMsg){
            console.log(errMsg)
        }
    })

    function byteToBase64(buffer) {
        var binary = '';
        var bytes = new Uint8Array(buffer);
        var len = bytes.byteLength;
        for (var i = 0; i < len; i++) {
            binary += String.fromCharCode(bytes[i]);
        }
        return window.btoa(binary);
    }

    function getOrder(){
        // order
        $.ajax({
            url: '/monfood_maven/OrderAllUserServlet',
            type: 'POST',
            data: JSON.stringify({
                // userId : 1
            }),
            dataType: 'json',
            success: function(msg){
                // console.log(msg)
                $.each(msg.userOrders, function(index, data){
                //    console.log(byteToBase64(this.resVO.resPic))
                    
                    let list = `
                        <div style="border-bottom:1px solid rgb(160, 156, 156); " class="orderList row d-flex justify-content-between">
                            <div class="col-6 x d-flex">
                                <div class="row d-flex">
                                    <div class="col x d-flex" style="padding-bottom: 8px">
                                        <img class="align-self-center" src="${'data:image/jpg;base64,' + byteToBase64(this.resVO.resPic)}" alt="">
                                    </div>
                                    <div class="col x" style="padding-top: 10px; padding-right: 0" class="productList">
                                        <div class="resName">${this.resVO.resName}</div>
                                        <div style="font-size: 12px;" >共 $<span class="total">${this.total}</span>- <span class="date" style="font-size: 10px">${this.orderDone}</span></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-3 x align-self-center">
                                <div class="col x">
                                    <div>
                                        <span style="font-size: 10px;">訂單編號: <span class="orderId">${this.orderId}</span></span>
                                    </div>
                                    <div>
                                        <a style="background-color: #FF7F3F; border: none; width: 100%; margin-bottom: 25px;" class="rating btn btn-primary" data-bs-toggle="modal" href="#exampleModalToggle" role="button">訂單評分</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    `
                    $('#orderList').append(list);

                })

                // magic, don't touch
                // 將產品名稱與kcal渲染至畫面
                $.each(msg.orderDetail, function(index, data){
                    var that = this;
                    $.each($('.orderId'), function(index, data){

                        if(that.orderId == this.innerText){
                            let list = `<div style="font-size: 12px;" ><span class="item">${that.productVo.productName}</span>- <span class="kcal">${that.productVo.productKcal}</span>kcal</div>`;
                            let productList = $(this).closest('.orderList').find('div');
                            $(productList[3]).append(list)
                            return;
                        }
                        
                    })
                    
                })

            },
            error: function(errMsg){
                console.log(errMsg)
            }
        })
    }

    getOrder();
    
    var res_star;
    var del_star;
    // console.log($('.rating'))
    // rating
    $('#orderList').on('click', '.rating' , function(){
        
        let orderId = parseInt($(this).closest('div').prev().find('span').find('span').text())
        console.log($('.rating'))
        $.ajax({
            url: '/monfood_maven/AdminOrderServlet',
            type: 'POST',
            data: JSON.stringify({
                orderId : orderId
            }),
            dataType: 'json',
            success: function(msg){

                let resStar =`
                    <span class="resStar star${msg.Orders.resRate >= 1 ? ' -on':''}" data-star="1"><i class="fas fa-star"></i></span>
                    <span class="resStar star${msg.Orders.resRate >= 2 ? ' -on':''}" data-star="2"><i class="fas fa-star"></i></span>
                    <span class="resStar star${msg.Orders.resRate >= 3 ? ' -on':''}" data-star="3"><i class="fas fa-star"></i></span>
                    <span class="resStar star${msg.Orders.resRate >= 4 ? ' -on':''}" data-star="4"><i class="fas fa-star"></i></span>
                    <span class="resStar star${msg.Orders.resRate >= 5 ? ' -on':''}" data-star="5"><i class="fas fa-star"></i></span>
                `
                let delStar =`
                    <span class="delStar star${msg.Orders.delRate >= 1 ? ' -on':''}" data-star="1"><i class="fas fa-star"></i></span>
                    <span class="delStar star${msg.Orders.delRate >= 2 ? ' -on':''}" data-star="2"><i class="fas fa-star"></i></span>
                    <span class="delStar star${msg.Orders.delRate >= 3 ? ' -on':''}" data-star="3"><i class="fas fa-star"></i></span>
                    <span class="delStar star${msg.Orders.delRate >= 4 ? ' -on':''}" data-star="4"><i class="fas fa-star"></i></span>
                    <span class="delStar star${msg.Orders.delRate >= 5 ? ' -on':''}" data-star="5"><i class="fas fa-star"></i></span>
                `
                
                let resComment = msg.Orders.resComment;
                let delComment = msg.Orders.delComment;

                if(resComment == null || resComment == undefined){
                    $('.resModal').val('');
                }else{
                    $('.resModal').val(resComment);
                };

                if(delComment == null || delComment == undefined){
                    $('.delModal').val('');
                }else{
                    $('.delModal').val(delComment);
                };

                $('.resBlock').html(resStar)
                $('.delBlock').html(delStar)
                return;

            },
            error: function(errMsg){

            }
            
        })

        // 重要性的星號res
        $('.col').on('click', '.resStar', function(){
            console.log(this)
            res_star = parseInt($(this).attr('data-star'));
            $(this).closest('.star_block').find('.resStar').each(function(i, item){
                if(parseInt($(this).attr('data-star')) <= res_star){
                    $(this).addClass('-on');
                }else{
                    $(this).removeClass('-on');
                }
            })
            
        });

        // 重要性的星號del
        $('.col').on('click', '.delStar', function(){
            console.log(this)
            del_star = parseInt($(this).attr('data-star'));
            $(this).closest('.star_block').find('.delStar').each(function(i, item){
                if(parseInt($(this).attr('data-star')) <= del_star){
                    $(this).addClass('-on');
                }else{
                    $(this).removeClass('-on');
                }
            })
            
        });
        
        // update rating
        $('#ratingSubmit').on('click', function(){
            $.ajax({
                url: '/monfood_maven/UpdateOrderRating',
                type: 'POST',
                data: JSON.stringify({
                    orderId : orderId,
                    resRate : res_star,
                    delRate : del_star,
                    resComment : $('.resModal').val().trim(),
                    delComment : $('.delModal').val().trim()
                }),
                dataType: 'json',
                success: function(msg){
                    console.log(msg)
                    
                },
                error: function(errMsg){

                }
                
            })
        })
        
        return;
    
    })

})