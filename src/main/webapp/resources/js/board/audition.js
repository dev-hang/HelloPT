$(document).ready(function () {
    $('#tab01').hover(function(){
        $(this).css({"font-weight":"bold", "color":"#dc3545", "border-bottom":"2px solid #dc3545","box-sizing": "border-box"});
    }, function() {
        $(this).css({"font-weight":"normal", "color":"#000","border-bottom":"none"});
    });
    $('#tab02').hover(function(){
    	$(this).css({"font-weight":"bold", "color":"#dc3545", "border-bottom":"2px solid #dc3545","box-sizing": "border-box"});
    }, function() {
        $(this).css({"font-weight":"normal", "color":"#000","border-bottom":"none"});
    });
    $('#tab03').hover(function(){
    	$(this).css({"font-weight":"bold", "color":"#dc3545", "border-bottom":"2px solid #dc3545","box-sizing": "border-box"});
    }, function() {
        $(this).css({"font-weight":"normal", "color":"#000","border-bottom":"none"});
    });
    $('#tab04').hover(function(){
    	$(this).css({"font-weight":"bold", "color":"#dc3545", "border-bottom":"2px solid #dc3545","box-sizing": "border-box"});
    }, function() {
        $(this).css({"font-weight":"normal", "color":"#000","border-bottom":"none"});
    });
    $('#tab05').hover(function(){
    	$(this).css({"font-weight":"bold", "color":"#dc3545", "border-bottom":"2px solid #dc3545","box-sizing": "border-box"});
    }, function() {
        $(this).css({"font-weight":"normal", "color":"#000","border-bottom":"none"});
    });


    $("#tab01").click(function(){
       let position=$("#view01").offset();
        $("html , body").animate({scrollTop:position.top},500);
    });
    $("#tab02").click(function(){
       let position=$("#view02").offset();
        $("html , body").animate({scrollTop:position.top},500);
    });
    $("#tab03").click(function(){
       let position=$("#view03").offset();
        $("html , body").animate({scrollTop:position.top},500);
    });
    $("#tab04").click(function(){
       let position=$("#view04").offset();
        $("html , body").animate({scrollTop:position.top},500);
    });
    $("#tab05").click(function(){
       let position=$("#view05").offset();
        $("html , body").animate({scrollTop:position.top},500);
    });
});