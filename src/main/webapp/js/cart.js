window.addEventListener('load', function(){
  
    //////////////////////// 取得Redis購物車內容//////////////////////////
    $.ajax({
        url: 'GetCartServlet',
        type: 'POST',
        dataType: 'json',
        success: function(msg){
          console.log(msg)
          $.each(msg.cart, function(index, data){
            console.log(this)
            let cartLi = 
            `
              <li class="cartItem d-flex justify-content-between" style="border-bottom: 1px solid rgb(200, 199, 199); padding-bottom: 10px">
                <div class="d-flex">
                  <div style="margin-right: 10px; margin-left: 8px">
                    <select class="form-select form-select-sm" aria-label=".form-select-sm example" style="width: 60px; margin: 5px">
                    <option class="cartOption" value="0"${this.amount == 0 ? 'selected': ''}>移除</option>
                    <option class="cartOption" value="1"${this.amount == 1 ? 'selected': ''}>1</option>
                    <option class="cartOption" value="2"${this.amount == 2 ? 'selected': ''}>2</option>
                    <option class="cartOption" value="3"${this.amount == 3 ? 'selected': ''}>3</option>
                    <option class="cartOption" value="4"${this.amount == 4 ? 'selected': ''}>4</option>
                    <option class="cartOption" value="5"${this.amount == 5 ? 'selected': ''}>5</option>
                    <option class="cartOption" value="6"${this.amount == 6 ? 'selected': ''}>6</option>
                    <option class="cartOption" value="7"${this.amount == 7 ? 'selected': ''}>7</option>
                    <option class="cartOption" value="8"${this.amount == 8 ? 'selected': ''}>8</option>
                    <option class="cartOption" value="9"${this.amount == 9 ? 'selected': ''}>9</option>
                    <option class="cartOption" value="10"${this.amount == 10 ? 'selected': ''}>10</option>
                    <option class="cartOption" value="11"${this.amount == 11 ? 'selected': ''}>11</option>
                    <option class="cartOption" value="12"${this.amount == 12 ? 'selected': ''}>12</option>
                    <option class="cartOption" value="13"${this.amount == 13 ? 'selected': ''}>13</option>
                    <option class="cartOption" value="14"${this.amount == 14 ? 'selected': ''}>14</option>
                    <option class="cartOption" value="15"${this.amount == 15 ? 'selected': ''}>15</option>
                    <option class="cartOption" value="16"${this.amount == 16 ? 'selected': ''}>16</option>
                    <option class="cartOption" value="17"${this.amount == 17 ? 'selected': ''}>17</option>
                    <option class="cartOption" value="18"${this.amount == 18 ? 'selected': ''}>18</option>
                    <option class="cartOption" value="19"${this.amount == 19 ? 'selected': ''}>19</option>
                    <option class="cartOption" value="20"${this.amount == 20 ? 'selected': ''}>20</option>
                    </select>
                  </div>
                  <a href="" class="">
                    <div>
                        <h6 style="margin-bottom: 2px; margin-top: 5px" prodId="${this.prodId}">${this.prodName}</h6>
                        <div style="font-size: 12px">$<span class="itemPrice">${this.prodPrice}</span></div>
                        <div style="font-size: 12px"><span class="kcal">${this.prodKcal}</span>Kcal</div>
                    </div>
                  </a>
                </div>
                <div style="margin-right: 10px">$<span class="totalPrice">${this.amount*this.prodPrice}</span></div>
              </li>
            `

            $('.cartList').append(cartLi)
          })
        },
        error: function(errMsg){

        }
    })

    //////////////////////// 取得Redis購物車內容//////////////////////////


    // 加入購物車
    $('#pdtPageBlock').on('click', '.monfood-addToCart', function(){
        let item = $(this).closest('.modal-content').find('.monfood-modalBody');
        let prodName = item.find('.monfood-ModalPdtName').text();
        let prodKcal = item.find('.monfood-ModalPdtKcal').text();
        let prodPrice = item.find('.monfood-ModalPdtPrice').text();
        let amount = item.find('.quantity-input').val();
        let prodId = item.find('.monfood-ModalPdtName').attr('prodId');

        let cartLi = 
        `
          <li class="cartItem d-flex justify-content-between" style="border-bottom: 1px solid rgb(200, 199, 199); padding-bottom: 10px">
            <div class="d-flex">
              <div style="margin-right: 10px; margin-left: 8px">
                <select class="form-select form-select-sm" aria-label=".form-select-sm example" style="width: 60px; margin: 5px">
                <option class="cartOption" value="0"${amount == 0 ? 'selected': ''}>移除</option>
                <option class="cartOption" value="1"${amount == 1 ? 'selected': ''}>1</option>
                <option class="cartOption" value="2"${amount == 2 ? 'selected': ''}>2</option>
                <option class="cartOption" value="3"${amount == 3 ? 'selected': ''}>3</option>
                <option class="cartOption" value="4"${amount == 4 ? 'selected': ''}>4</option>
                <option class="cartOption" value="5"${amount == 5 ? 'selected': ''}>5</option>
                <option class="cartOption" value="6"${amount == 6 ? 'selected': ''}>6</option>
                <option class="cartOption" value="7"${amount == 7 ? 'selected': ''}>7</option>
                <option class="cartOption" value="8"${amount == 8 ? 'selected': ''}>8</option>
                <option class="cartOption" value="9"${amount == 9 ? 'selected': ''}>9</option>
                <option class="cartOption" value="10"${amount == 10 ? 'selected': ''}>10</option>
                <option class="cartOption" value="11"${amount == 11 ? 'selected': ''}>11</option>
                <option class="cartOption" value="12"${amount == 12 ? 'selected': ''}>12</option>
                <option class="cartOption" value="13"${amount == 13 ? 'selected': ''}>13</option>
                <option class="cartOption" value="14"${amount == 14 ? 'selected': ''}>14</option>
                <option class="cartOption" value="15"${amount == 15 ? 'selected': ''}>15</option>
                <option class="cartOption" value="16"${amount == 16 ? 'selected': ''}>16</option>
                <option class="cartOption" value="17"${amount == 17 ? 'selected': ''}>17</option>
                <option class="cartOption" value="18"${amount == 18 ? 'selected': ''}>18</option>
                <option class="cartOption" value="19"${amount == 19 ? 'selected': ''}>19</option>
                <option class="cartOption" value="20"${amount == 20 ? 'selected': ''}>20</option>
                </select>
              </div>
              <a href="" class="">
                <div>
                    <h6 style="margin-bottom: 2px; margin-top: 5px" prodId="${prodId}">${prodName}</h6>
                    <div style="font-size: 12px">$<span class="itemPrice">${prodPrice}</span></div>
                    <div style="font-size: 12px"><span class="kcal">${prodKcal}</span>Kcal</div>
                </div>
              </a>
            </div>
            <div style="margin-right: 10px">$<span class="totalPrice">${amount*prodPrice}</span></div>
          </li>
        `

        $('.cartList').append(cartLi)
        $('.btn-close').click();

        $.ajax({
          url: 'SetCartServlet',
          type: 'POST',
          data: JSON.stringify({
            prodName: prodName,
            prodKcal: prodKcal,
            prodPrice: prodPrice,
            amount: amount,
            prodId: prodId
          }),
          dataType: 'json',
          success: function(msg){
            console.log(msg)
          },
          error: function(errMsg){
    
          }
        })
    })

    // select 標籤
    $('.cartList').on('change', 'select', function(){
        //  = $(this).find('option:selected').val() * 2
        $(this).closest('li').find('.totalPrice').text($(this).find('option:selected').val()*parseInt($(this).closest('li').find('.itemPrice').text()));
        // console.log()
        if($(this).find('option:selected').val() == 0){
            $.ajax({
              url: 'DeleteOneCartServlet',
              type: 'POST',
              data: JSON.stringify({
                index: $(this).closest('li').index()
              }),
              dataType: 'json',
              success: function(msg){
              },
              error: function(errMsg){
              }
            })
            $(this).closest('li').remove();
        }
        // let total = $(this).find('option:selected').val()*$(this).closest('.d-flex').find('.itemPrice').text();
        // $(this).closest('li').find('.totalPrice').text(total); 

    })

    // 清空購物車
    $('.clear_btn').on('click', function(){
        $('.cartList').html('')
        $.ajax({
          url: 'DeleteCartServlet',
          type: 'POST',
          dataType: 'json',
          success: function(msg){
          },
          error: function(errMsg){
          }
        })
    })

    // 送出購物車
    $('.checkout_btn').on('click', function(){
        let cartItem = ($('.cartItem'));
        var cartList = new Array;
        
        if ($('.cartList').text() == '') {
          return;
        }

        $.each(cartItem, function(){
            let cart = {
                resId : $('.monfood-resNameHeader').attr('resid'),
                resName : $('.monfood-resNameHeader').text(),
                productID : $(this).find('h6').attr('prodid'),
                productName : $(this).find('h6').text(),
                amount : $(this).find('option:selected').val(),
                productPrice : $(this).find('.itemPrice').text(),
                productKcal : $(this).find('.kcal').text()
            };
            cartList.push(cart);
        })
        var data = {'cartList':cartList};
        sessionStorage.setItem('cartList', JSON.stringify(data));

        // 清空Redis
        $.ajax({
          url: 'DeleteCartServlet',
          type: 'POST',
          dataType: 'json',
          success: function(msg){
          },
          error: function(errMsg){
          }
        })
        location.href = 'pay.html'


    })

})