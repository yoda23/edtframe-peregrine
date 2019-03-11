var ctx = getRootPath();
var imgPath = "http://localhost:8080/res/";
var pageLength = 20;

//加载框下标，用于关闭时识别
var layerIndex;
layui.config({
    base: ctx + '/layer-ui/lay/extend/'
}).extend({
    formSelects: 'formSelects/formSelects-v4'
});

layui.use(['layer'], function () {
    var layer = layui.layer;
    layer.config({
        path: '../layer-ui/lib/layer/3.1.1/layer.mini.js',
        resize: false
    });
    $("#closeWindow").click(function () {
        parent.layer.closeAll();
    });
    $(document).keydown(function (event) {
        if (event.keyCode == 13 || event.keyCode == 32) {
            $("*").blur();//去掉焦点
            if ($(".layui-layer-btn0").length > 0)
                parent.layer.closeAll();
        }
    });
    $.ajaxSetup({
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        type: "POST",
        complete: function (XMLHttpRequest, textStatus) {
            layer.close(layerIndex);
            var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus");
            switch (sessionstatus) {
                case "timeout":
                    top.location.href = ctx + '/login';
                    layer.alert("用户登陆超时,请重新登录");
                    break;
            }
        },
        error: function (xhr, status, error) {
            layer.close(layerIndex);
            layer.alert("数据请求失败");
        },
        beforeSend: function () {
            layerIndex = layer.load(0, {
                icon: 16,
                shade: 0.01,
                time: 0
            });
        }
    });
});


/*
 * 获取项目根目录
 */
function getRootPath() {
    // 获取当前网址，如： http://localhost:8080/iceframe/view/center/jsps/user/user.jsp
    var curWwwPath = window.document.location.href;
    // 获取主机地址之后的目录，如：iceframe/view/center/jsps/user/user.jsp
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    // 获取主机地址，如： http://localhost:8080
    var localhostPaht = curWwwPath.substring(0, pos);
    // 获取带"/"的项目名，如：/iceframe
    var projectName = pathName
        .substring(0, pathName.substr(1).indexOf('/') + 1);
    return (localhostPaht + projectName);
}

//-------------------------------
/*
 * 获取当前时间，格式YYYY-MM-DD
 */
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    return year + seperator1 + month + seperator1 + strDate;
}

Date.prototype.format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1,                 //月份
        "d+": this.getDate(),                    //日
        "h+": this.getHours(),                   //小时
        "m+": this.getMinutes(),                 //分
        "s+": this.getSeconds(),                 //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
};
//-------------------------------


