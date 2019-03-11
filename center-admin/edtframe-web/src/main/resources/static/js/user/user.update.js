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
    }, check: {
        enable: true,
        chkStyle: "checkbox",
        chkboxType: {
            "Y": "s",
            "N": "s"
        }
    },
    callback: {
        onClick: userTreeOnClick,
        onCheck: userTreeOnCheck
    }
};
layui.use(['form', 'layer'], function () {
    var layer = layui.layer, form = layui.form;
    form.on('submit(save)', function (data) {
        layer.confirm('确定要修改此条数据么？', {
            btn: ['确定', '取消']
        }, function () {
            $.ajax({
                type: "POST",
                url: ctx + '/user/update',
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


/*
单击树形节点
 */
function userTreeOnClick(event, treeId, treeNode) {
    //设置所属地市信息
    $("#parentName").val(treeNode.name);
    $("#mechanismsId").val(treeNode.id);
}


/*
单击树形check
 */
function userTreeOnCheck(event, treeId, treeNode) {
    var treeObj = jQuery.fn.zTree.getZTreeObj("userTree");
    var nodes = treeObj.getCheckedNodes(true);
    var msg = "";
    for (var i = 0; i < nodes.length; i++) {
        if ((i + 1) === nodes.length) {
            msg += nodes[i].id;
        } else {
            msg += nodes[i].id + ",";
        }
    }
    jQuery('#userMechanismsRights').val(msg);
}