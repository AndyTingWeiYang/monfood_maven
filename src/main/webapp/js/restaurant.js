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


// IIFE 立即執行函式
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
      if (quantity_ > 0) {
        quantity_target.val(--quantity_);
      }
    });


    $(quantity_plus).click(function () {
      quantity_target.val(++quantity_);
    });
  }
  $.fn.Guantity = jQueryPlugin("Guantity", Guantity);
  $("[data-quantity]").Guantity();
})();


//-----------------上面不動----------------------
//商品頁面Header資訊
$(document).ready(function(){

  const urlParams = new URLSearchParams(window.location.search);
      const resId = urlParams.get('resID');
         if(resId) {
          $.ajax({
              url: 'ResGetToResPage',
              type: 'POST',
              dataType: 'JSON',
              data: {
                resId: resId
              },

              success: function(data){
                console.log(data);
                console.log('hihi');
                    const resPage = data.resPage;
                    var resCat;

                    if(resPage.resCategory==1){
                      resCat = "台式料理";
                      }else if(resPage.resCategory==2){
                      resCat = "日式料理";
                      }else if(resPage.resCategory==3){
                      resCat = "泰式料理";
                      }else if(resPage.resCategory==4){
                      resCat = "美式料理";
                      }else if(resPage.resCategory==5){
                      resCat = "韓式料理";
                      }

                    let resHeaderHtml = `
                    <div class="col-lg-6">
                        <div class="col-xl-10 monfood-resNameHeader" style="float: left; font-size :35px;" resId="${resPage.resId}">${resPage.resName}</div>  
                        <div class="col-lg-3 monfood-resType">${resCat}</div>
                          <div class="row">
                              <div class="col-xl-2 monfood-openedTimediv" style="text-align: center;">營業時間</div>
                              <div class="col-xl-3 monfood-openedTime" style="margin: 0; padding:0;">開始: ${resPage.bzOpenHours}</div>  
                              <div class="col-xl-3 monfood-closeTime" style="margin: 0; padding:0;">結束: ${resPage.bzCloseHours}</div>    
                          </div> 
                    </div>  
                    <div class="col-lg-6">
                      <button id="commentBtn" type="button" class="col-xl-2 btn btn-primary monfood-resDetail" data-bs-toggle="modal" data-bs-target="#exampleModalDetail">評分</button>
                    `;

                    //
                    let resCommentHtml = `
                    <div class="modal fade detail-modal" id="exampleModalDetail" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                  <div class="modal-header">
                                      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" style="width: 10px; height: 10px; margin-right: 2px"></button>
                                      <br />
                                  </div>
                                <div style="margin: 10px">
                                    <div class="modal-body">
                                        <div class="ratings" style="font-size: 15px">
                                            <div class="card card-body">
                                                <ul id="commentBlock" style="list-style: none; font-size: 15px"></ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                      

                    </div>  
                  
                    `;

                const contentHtml = $(resHeaderHtml);
                $('#resHeaderBlock').append(contentHtml).append(resCommentHtml);
                $('#cart-res-name').text(resPage.resName);
                $('#commentBtn').click(commentFunc);
              }
              
          });

    //評論彈窗
    function commentFunc() {
      $.ajax({
        url: 'AdminGetResComment',
        type: 'POST',
        data: {
          resId: resId
        },
        dataType: 'JSON',
        success: function(data) {
            const commentList = data.commentList;

            if(!commentList) {
              return;
            }

            for(let i = 0; i < commentList.length; i++) {
              const resVO = commentList[i];
              const resComment = resVO.resComment;
              // false / undefined / '' / null / 0 => false
              if(!resComment) {
                continue;
              }

              let resCommentHtml = `
                <li>
                    <p>${resComment}</p>
                </li>
                <hr />
              `;
  
              $('#commentBlock').append(resCommentHtml);
            }
        }
      });    
    }
     
  $.ajax({
    url: 'GetAllPdtServlet',
    type: 'POST',
    data: {
      resId: resId
    },
    dataType: 'JSON',
    success: function(data){
        console.log(data)
        const pdtList = data.pdtList;
  
          for(let i = 0; i < pdtList.length; i++) {
            const productVo = pdtList[i];
    
              let pdtPageHtml = `
                  <div type="button monfood-pdtModalBtn" class="btn btn-primary col-xl-3 col-6 col d-flex justify-content-center" data-bs-toggle="modal" data-bs-target="#exampleModal-${productVo.productID}">
                      <div id="product_img monfood-pdtImgdiv">
                        <img id="showPopup" class="monfood-pdtImg" src="/monfood_maven/resprofile/ProductPicServlet?productID=${productVo.productID}"/>
                        <p class="monfood-pdtName">${productVo.productName}</p>
                        <p class="monfood-pdtPrice">$${productVo.productPrice}</p>
                    </div>
                  </div>
              `;
              let pdtPageContentHtml = `
              <div class="modal fade" id="exampleModal-${productVo.productID}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                  <div class="modal-content">
                    <div class="modal-header">
                      
                      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body monfood-modalBody">
                      <div id="modal-img monfood-pdtPicModaldiv" class="mon">
                        <img class="monfood-pdtPicModal" src="/monfood_maven/resprofile/ProductPicServlet?productID=${productVo.productID}"/>
                      </div>
                      <div class="monfood-pdtinfoModeldiv">
                        <h4 class="monfood-ModalPdtName" prodId="${productVo.productID}">${productVo.productName}</h4>
                        <h6><span class="monfood-ModalPdtKcal">${productVo.productKcal}</span> Kcal</h6>
                        <h6>$<span class="monfood-ModalPdtPrice">${productVo.productPrice}</span></h6>
                      </div>
                      
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
  
                    </div>
                    <div class="modal-footer monfood-modalFooter">
                      <button type="button" class="monfood-addToCart">加入購物車</button>
                    </div>
                  </div>
                </div>
              </div>
              `;
            
              // resPageHtml<a> 新增內容
              $('#pdtPageBlock').append(pdtPageHtml).append(pdtPageContentHtml);

              $("[data-quantity]").Guantity();
            }
              
      
            }
        });		

    } 
});


