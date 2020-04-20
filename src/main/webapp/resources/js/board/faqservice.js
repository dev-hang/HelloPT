$(document).ready(function () {
    $('.accordion .subject').on('click', function () {
        var content = $(this).siblings('.content');
        if (content.css('display') == 'none') {
            $('.accordion .content').slideUp();
            content.slideDown();
        } else {
            content.slideUp();
        }
    });
});
