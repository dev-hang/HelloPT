// main slider

    var swiper = new Swiper('.swiper-container', {
      direction: 'horizontal',
      slidesPerView: 'auto',
      mousewheel: true,
      centeredSlides: true,
      spaceBetween: 140,
      speed: 800,
      // scrollbar: {
      //   el: '.swiper-scrollbar',
      //   hide: true,
      // },
      pagination: {
        el: '.swiper-pagination',
        type: 'fraction',
      },
      navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev'
      },
      breakpoints: {
        1300: {
          spaceBetween: 20
        },
        772: {
          spaceBetween: 0
        }
      }
    });

$(function(){
  var scrollP = $('.scroll-cta').parents('.swiper-slide');
  // console.log(scrollP);
  setInterval(function(){
    if(scrollP.hasClass('swiper-slide-prev') == true) {
    $('.scroll-cta').hide();
  }
  }, 35);
});
