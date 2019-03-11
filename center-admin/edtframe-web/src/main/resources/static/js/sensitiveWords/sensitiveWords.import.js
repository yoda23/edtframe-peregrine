layui.use(['form', 'layer'], function () {
    var layer = layui.layer, form = layui.form;
    form.on('submit(save)', function (data) {
        $.ajax({
            type: "POST",
            url: ctx + '/sensitive-words/importSensitiveFromExcel',
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
//----------------------文件上传---start----------------------
layui.use('upload', function () {
    var upload = layui.upload;
    //执行实例
    var uploadInst = upload.render({
        elem: '#uploadBtn' //绑定元素
        , url: ctx + '/upload/uploadFileAction' //上传接口
        , done: function (res, index, upload) { //上传后的回调
            var filePath = res.result.datas;
            $("#filePath").val(filePath);
        }
        , error: function () {
            //请求异常回调
        }
        , multiple: 'false'//是否允许多文件上传
        , accept: 'file' //允许上传的文件类型
        , exts: 'xls|xlsx'//允许上传的文件类型
    });
});
//----------------------文件上传---end----------------------