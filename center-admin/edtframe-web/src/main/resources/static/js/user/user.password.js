layui.use(['form', 'layer'], function () {
    var layer = layui.layer, form = layui.form;
    form.on('submit(save)', function (data) {
        layer.confirm('确定要修改用户信息吗？', {
            btn: ['确定', '取消']
        }, function () {
            $.ajax({
                type: "POST",
                url: ctx + '/user/info/update/password',
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
    form.verify({
        pass: [/^[\S]{6,12}$/, '密码必须6到12位，且不能出现空格'],
        repass: function (value) {
            console.info(value);
            var pass = $("#userPassword").val();
            if (!new RegExp(pass).test(value)) {
                return '两次输入的密码不一致';
            }
        }
    });
});


