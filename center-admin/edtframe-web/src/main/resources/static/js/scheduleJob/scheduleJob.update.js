layui.use(['form', 'layer'], function () {
    var layer = layui.layer, form = layui.form;
    form.on('submit(save)', function (data) {
        $.ajax({
            type: "POST",
            url: ctx + '/scheduleJob/update',
            data: $("#form").serialize(),
            cache: false,
            async: true,
            success: function (result) {
                if (result.status.success) {
                    layer.alert(result.status.message, function () {
                        parent.layer.closeAll();
                    });
                } else {
                    layer.alert(result.status.message);
                }
            }
        });
        return false;
    });
});