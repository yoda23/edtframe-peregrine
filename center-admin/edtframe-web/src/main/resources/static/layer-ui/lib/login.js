layui.use(['form'], function () {
    var form = layui.form;
    form.on('submit(login)', function (data) {
        $.ajax({
            type: "POST",
            url: ctx + '/login',
            data: $("#loginForm").serialize(),
            cache: false,
            async: true,
            success: function (result) {
                if (result.status.success) {
                    window.location.href = ctx + "/index";
                } else {
                    layer.alert(result.status.message);
                }
            }
        });
        return false;
    });
});
$("#validateCodeImg").click(
    function () {
        $("#validateCodeImg").attr(
            "src",
            ctx + "/getValidateCode?timestamp="
            + new Date().getTime());
    });