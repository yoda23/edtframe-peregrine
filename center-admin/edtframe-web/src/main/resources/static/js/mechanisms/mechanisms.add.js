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
layui.use(['form', 'layer'], function () {
    var layer = layui.layer, form = layui.form;
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
        $.ajax({
            type: "POST",
            url: ctx + '/mechanisms/save',
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

function mechanismsTreeOnClick(event, treeId, treeNode) {
    $("#parentName").val(treeNode.name);
    $("#parentId").val(treeNode.id);
}


