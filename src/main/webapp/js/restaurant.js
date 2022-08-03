$(function () {
  $('.flip').click(function () {
    let css = $(this).find('.panel').css('display');

    if (css === 'none') return $(this).find('.panel').css('display', 'block');

    $(this).find('.panel').css('display', 'none');
  });
});

// 
(function () {
  "use strict";
  var jQueryPlugin = (window.jQueryPlugin = function (ident, func) {
    return function (arg) {
      if (this.length > 1) {
        this.each(function () {
          var $this = $(this);

          if (!$this.data(ident)) {
            $this.data(ident, func($this, arg));
          }
        });

        return this;
      } else if (this.length === 1) {
        if (!this.data(ident)) {
          this.data(ident, func(this, arg));
        }

        return this.data(ident);
      }
    };
  });
})();

(function () {
  "use strict";
  function Guantity($root) {
    const element = $root;
    const quantity = $root.first("data-quantity");
    const quantity_target = $root.find("[data-quantity-target]");
    const quantity_minus = $root.find("[data-quantity-minus]");
    const quantity_plus = $root.find("[data-quantity-plus]");
    var quantity_ = quantity_target.val();
    $(quantity_minus).click(function () {
      quantity_target.val(--quantity_);
    });
    $(quantity_plus).click(function () {
      quantity_target.val(++quantity_);
    });
  }
  $.fn.Guantity = jQueryPlugin("Guantity", Guantity);
  $("[data-quantity]").Guantity();
})();

//商品頁面Header資訊
$(document).ready(function(){
   $.ajax({
      url: 'ResGetRateServlet',
      type: 'POST',
      dataType: 'JSON',

      success: function(data){
        const resList = data.resList.filter(function(resVo){
          return resVo
        });

        
      }
   });
});
//詳細資料
//TODO: 商品顯示
$(document).ready(function(){
  $.ajax({
    url: 'AdminProductAllServlet',
    type: 'POST',
    dataType: 'JSON',
    success: function(data){
      const pdtList = data.pdtList.filter(function(productVO){
        return productVO
      });

      for(let i = 0; i < pdtList.length; i++) {
        const productVO = pdtList[i];

        let pdtPageContentHtml = `
        <div>
        <div type="button monfood-pdtModalBtn" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
          <div id="product_img monfood-pdtImgdiv">
            <img id="showPopup" class="monfood-pdtImg" src="/monfood_maven/resprofile/ProductPicServlet?productID=${productVO.productID}"/>
          </div>
          <p class="monfood-pdtName">${productVO.prodcuctName}</p>
          <p class="monfood-pdtPrice">${productVO.prodcuctPrice}</p>
        </div>
     
     {/* Modal */}

        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title monfood-pdtNameModdal" id="exampleModalLabel">>${productVO.prodcuctName}</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body monfood-modalBody">
                <div id="modal-img monfood-pdtPicModaldiv" class="mon">
                  <img class="monfood-pdtPicModal" src="/monfood_maven/resprofile/ProductPicServlet?productID=${productVO.productID}"/>
                </div>
                <div class="monfood-pdtinfoModeldiv">
                  <h4 class="monfood-ModalPdtName">>${productVO.prodcuctName}</h4>
                  <h6 class="monfood-ModalPdtKcal">>${productVO.prodcuctKcal}</h6>
                  <h6 class="monfood-ModalPdtPrice">${productVO.prodcuctPrice}</h6>
                </div>
                <!--  -->
                <div class="Qcontainer">
                  <div class="quantity-control" data-quantity="" style="margin-top: 0px">
                    <button class="quantity-btn" data-quantity-minus="">
                      <svg viewBox="0 0 409.6 409.6">
                        <g>
                          <g>
                            <path d="M392.533,187.733H17.067C7.641,187.733,0,195.374,0,204.8s7.641,17.067,17.067,17.067h375.467 c9.426,0,17.067-7.641,17.067-17.067S401.959,187.733,392.533,187.733z"/>
                          </g>
                        </g>
                      </svg>
                    </button>
                    <input type="number" class="quantity-input" data-quantity-target="" value="1" step="1" min="1" max="" name="quantity"/>
                    <button class="quantity-btn" data-quantity-plus="">
                      <svg viewBox="0 0 426.66667 426.66667">
                        <path
                          d="m405.332031 192h-170.664062v-170.667969c0-11.773437-9.558594-21.332031-21.335938-21.332031-11.773437 0-21.332031 9.558594-21.332031 21.332031v170.667969h-170.667969c-11.773437 0-21.332031 9.558594-21.332031 21.332031 0 11.777344 9.558594 21.335938 21.332031 21.335938h170.667969v170.664062c0 11.777344 9.558594 21.335938 21.332031 21.335938 11.777344 0 21.335938-9.558594 21.335938-21.335938v-170.664062h170.664062c11.777344 0 21.335938-9.558594 21.335938-21.335938 0-11.773437-9.558594-21.332031-21.335938-21.332031zm0 0"
                        />
                      </svg>
                    </button>
                  </div>
                </div>

                <!--  -->
              </div>
              <div class="modal-footer monfood-modalFooter">
                <button type="button" class="monfood-addToCart">加入購物車</button>
              </div>
            </div>
          </div>
        </div>
      </div>
        `;
      }

    }
  
});
});

//談窗內容
