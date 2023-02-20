jQuery(function ($) {
    $(".menu").each(function () {
        alert($(this).attr("href"))
        alert(window.location.href);
    })
})