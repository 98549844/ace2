//$(document).ready(function () {
jQuery(function ($) {
    docReady();
    setTitle();
});

function setTitle() {
    const pName = window.location.pathname;
    //  alert("pName: "+pName);
    if (pName.indexOf("/ace/") !== -1) {
        // "/ace/" exist
        const t = pName.split("/ace/")[1].split(".html")[0];
        let m = t.toLowerCase().replace(/( |^)[a-z]/g, (L) => L.toUpperCase());
        if ("Index" === m) {
            m = "Dashboard";
        }
        //首字母大写
        $("title").html("Ace " + m);
        $("#breadcrumb2").text(m);
    } else {
        $("title").html("Ace Application");
    }

}


function docReady() {
    const aHref = "a[href*='.html']";
    const currentForm = "#current_form";
    //提交表单验证
    $(currentForm).off("submit");
    $(currentForm).on("submit", function () {
        showLoading();
        return true;
    });

    //链接点击绑定弹出加载框
    $(aHref).off();
    $(aHref).on("click", function () {
        const link = $(this).attr("href");
        $.cookie('currentURL', link);

        //左菜单 loading bar
        /*  if ($(this).attr("target") !== "_blank") {
            setTimeout(() => {
            }, 1000);
            showLoading();
        }*/
    });

    const currentURL = $.cookie('currentURL');
    $.each($(aHref), function () {
        const link = $(this).attr("href");
        if (link === currentURL) {
            $(this).parent().parent().parent().addClass("active").addClass("open");
            $(this).parent().addClass("active");
        }
    });
}

$("a.logout").on("click", function () {
    //mobile logout and hidden menu
  //  $(".navbar-toggle.menu-toggler.pull-left.display").removeClass(".display");
  //  $("#sidebar").removeClass(".display");
    logout();
});

function isMobile() {
    return (/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent));
}

function logout() {
    //clear cookies
    $.cookie('currentURL', '');
    //  /ace/logout.html
    d = dialog({
        title: 'Logout',
        content: 'Are you confirm log out !!!',
        okValue: 'Confirm',
        ok: function () {
            this.title('Logging out …');
            $.ajax({
                type: "get", // 以get方式发起请求
                url: "logout.html",
                success(data) {
                    window.location.href = '/ace/login.html';
                }
            })

            /*  setTimeout(() => {
              }, 3000);*/
            //   location.href = '/ace/login.html';
            return false;
        },
        cancelValue: 'Cancel',
        cancel: function () {
        }
    });
    d.showModal();
}


/**
 * 弹出一个加载弹出框
 */
let d;

function showLoading() {
    d = dialog({
        content: $("#loading_img").html(),
    });
    d.showModal();
    setTimeout(function () {
        d.close().remove();
    }, 120000);
}

/**
 * 关闭加载弹出框
 */
function closeLoading() {
    d.close().remove();
}

//-------------------------------------------------------------------------------------------------------------
//获取页面顶部被卷起来的高度
function scrollTop() {
    return Math.max(
        //chrome
        document.body.scrollTop,
        //firefox/IE
        document.documentElement.scrollTop);
}

//获取页面文档的总高度
function documentHeight() {
    //现代浏览器（IE9+和其他浏览器）和IE8的document.body.scrollHeight和document.documentElement.scrollHeight都可以
    return Math.max(document.body.scrollHeight, document.documentElement.scrollHeight);
}

//获取页面浏览器视口的高度
function windowHeight() {
    //document.compatMode有两个取值。BackCompat：标准兼容模式关闭。CSS1Compat：标准兼容模式开启。
    return (document.compatMode === "CSS1Compat") ?
        document.documentElement.clientHeight :
        document.body.clientHeight;
}
//-------------------------------------------------------------------------------------------------------------


//check scrolling up or down
let position = $(window).scrollTop();
function scrollDown() {
    let down;
    const scroll = $(window).scrollTop();
    if (scroll > position) {
        console.log("向下滚动");
        down = true;
    } else {
        console.log("向上滚动");
        down = false;
    }
    position = scroll;
    return down;
}











