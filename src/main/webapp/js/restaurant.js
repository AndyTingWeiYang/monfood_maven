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

//TODO: 商品顯示
$(document).ready(function(){
  $.ajax({
    url: 'AdminProductAllServlet',
    type: 'POST',
    dataType: 'JSON',
    success: function(data){
      const pdtList = data.pdtList;
      const productVO = pdtList[i];

      



    }
  
});
});

//談窗內容
//詳細資訊