layui.use(['form', 'layer'], function () {
    var layer = layui.layer, form = layui.form;
    form.on('submit(save)', function (data) {
        layer.confirm('确定要修改此条数据么？', {
            btn: ['确定', '取消']
            // 按钮
        }, function () {
            $.ajax({
                type: "POST",
                url: ctx + '/role/update',
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
        }, function () {

        });
        return false;
    });
});


