function iFrameHeight() {
    var ifm = document.getElementById("iframepage");
    if (ifm != null) {
        var subWeb = document.frames ? document.frames["iframepage"].document : ifm.contentDocument;
        if (subWeb != null) {
            ifm.height = subWeb.body.scrollHeight;
        }
    }
}
function jump(url, target) {
    if (target) {
        $(".nav-second-level li").removeClass("active");
        $(target).parents(".nav-second-level").find("li").removeClass("active");
        $(target).parent("li").attr("class", "active");
    }
    $("#iframepage").attr("src", url);
    $("#iframepage").load(function () {
        document.getElementById("iframepage").height = $(this).contents().find(".content").height();
    });
}
$(function () {
    $(".sidebar-nav a:eq(0)").removeClass("collapsed");
    $(".sidebar-nav ul:eq(0)").addClass("in");
    $(".sidebar-nav").children("a").click(function () {
        $(this).removeClass("collapsed");
        $(this).siblings("a").addClass("collapsed");
        $(this).siblings("ul").removeClass("in");
        $(this).siblings("ul").removeAttr("style");
        $(this).next("ul").find("a").eq(0).click();
    });
    $("#iframepage").load(function () {
        var currentUrl = getCookie("currentUrl");
        $(".sidebar-nav li").each(function () {
            if ($(this).children("a").attr("onclick").indexOf(currentUrl) > -1) {
                $(".sidebar-nav li").removeClass("active");
                $(this).addClass("active");
                return false;
            }
        });
    });
});
//将form转为AJAX提交
function ajaxSubmit(frm, fn) {
    var option = {
        url: frm.attr("action"),
        type: frm.attr("method"),
        success: fn
    };
    frm.ajaxSubmit(option);
}
function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}