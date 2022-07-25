$(function () {
  $('.flip').click(function () {
    let css = $(this).find('.panel').css('display');

    if (css === 'none') return $(this).find('.panel').css('display', 'block');

    $(this).find('.panel').css('display', 'none');
  });
});


