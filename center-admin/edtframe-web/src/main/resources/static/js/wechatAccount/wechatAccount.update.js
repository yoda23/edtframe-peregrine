var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "parentId",
            rootPId: 0
        },
        key: {
            name: "name"
        }
    },
    callback: {
        onClick: mechanismsTreeOnClick
    }
};
layui.use(['form', 'layer', 'formSelects', 'upload'], function () {
    var layer = layui.layer, formSelects = layui.formSelects, form = layui.form, upload = layui.upload;
    var treeObj;
    $.ajax({
        url: ctx + '/mechanisms/user/tree',
        type: 'POST',
        success: function (data) {
            jQuery.fn.zTree.init(jQuery("#mechanismsTree"), setting, data);
            treeObj = jQuery.fn.zTree.getZTreeObj("mechanismsTree");
            treeObj.expandAll(false);
        },
        error: function (msg) {
            layer.alert(msg);
        }
    });
    $("#expandAll").click(function () {
        treeObj.expandAll(true);
        $(this).hide();
        $("#notExpandAll").show();
        return false;
    });
    $("#notExpandAll").click(function () {
        treeObj.expandAll(false);
        $(this).hide();
        $("#expandAll").show();
        return false;
    });

    form.on('submit(save)', function (data) {
        layer.confirm("确定要修改此条信息么", {
            btn: ['确定', '取消']
        }, function () {
            $.ajax({
                type: "POST",
                url: ctx + '/wechat/account/update',
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
        });

        return false;
    });
    var picPathUpload = upload.render({
        elem: '#picPathUploadBtn',
        url: ctx + '/upload/uploadFileAction',
        done: function (res, index, upload) {
            if (res.status.success) {
                var filePath = res.result.datas;
                $("#picPath").val(filePath);
            } else {
                layer.alert("文件上传失败");
            }
        },
        error: function () {
            layer.alert("文件上传失败");
            return false;
        },
        multiple: 'false',
        accept: 'file',
        exts: 'jpg|jpeg|png'
    });
    var certificatePathUpload = upload.render({
        elem: '#certificatePathBtn',
        url: ctx + '/upload/uploadFileAction',
        done: function (res, index, upload) {
            if (res.status.success) {
                var filePath = res.result.datas;
                $("#certificatePath").val(filePath);
            } else {
                layer.alert("文件上传失败");
            }
        },
        error: function () {
            layer.alert("文件上传失败");
            return false;
        },
        multiple: 'false',
        accept: 'file',
        exts: 'p12'
    });
});

function mechanismsTreeOnClick(event, treeId, treeNode) {
    $("#mechanismsName").val(treeNode.name);
    $("#mechanismsId").val(treeNode.id);
}


