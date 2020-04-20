//
// let vh = window.innerHeight * 0.01;
// document.documentElement.style.setProperty('--vh', `${vh}px`);

$(function(){
  //toggle navigaition
  //ie에서 안먹어서 theme-index.php 로 이동
  $('.nav_button').click(function(){
    $(this).toggleClass('off');
    $('#hd').toggleClass('ease');
    $('.artist_depth02').removeClass("on");
    $('.sub-x-btn-wr').toggleClass('ease');
    $('#navigation').toggleClass('close');
    $('#navigation').toggleClass('open');
    // $('body').toggleClass('non-scroll');
    var wHeight = $(window).height();
    if($('#navigation').hasClass('open') == true) {
      $('body').css('height',wHeight).css('overflow','hidden');
    } else {
      $('body').css('height','auto').css('overflow','auto');
    }
  });

  //toggle schedule
  $('.schedule_tabs a').click(function(){
    var target = $(this).attr('data-target');

    $('.schedule_tabs a').removeClass('on');
    $(this).addClass('on');
    $('.tab-content').hide();
    $('#'+target).fadeIn(600);
  });
});
