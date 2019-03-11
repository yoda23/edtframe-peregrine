layui.use(['form', 'layer'], function () {
    var layer = layui.layer, form = layui.form;
    form.on('submit(save)', function (data) {
        layer.confirm('确定要修改用户信息吗？', {
            btn: ['确定', '取消']
        }, function () {
            $.ajax({
                type: "POST",
                url: ctx + '/user/info/update',
                data: $("#form").serialize(),
                cache: false,
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
        });
        return false;
    });
});


