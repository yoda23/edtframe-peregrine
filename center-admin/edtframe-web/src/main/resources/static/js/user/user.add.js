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
layui.use(['form', 'layer', 'formSelects'], function () {
    var layer = layui.layer, formSelects = layui.formSelects, form = layui.form;
    var treeObj;
    $.ajax({
        url: ctx + '/mechanisms/user/tree',
        type: 'POST',
        success: function (data) {
            jQuery.fn.zTree.init(jQuery("#userTree"), setting, data);
            treeObj = jQuery.fn.zTree.getZTreeObj("userTree");
            treeObj.expandAll(false);
        },
        error: function (msg) {
            layer.alert(msg);
        }
    });
    // $("#selectAll").click(function () {
    //     treeObj.checkAllNodes(true);
    //     return false;
    // });
    // $("#notSelectAll").click(function () {
    //     treeObj.checkAllNodes(false);
    //     return false;
    // });
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
    formSelects.data('roleId', 'server', {
        type: 'post',
        url: ctx + '/role/info'
    });
    form.on('submit(save)', function (data) {
        $.ajax({
            type: "POST",
            url: ctx + '/user/save',
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
    form.verify({
        pass: [/^[\S]{6,12}$/, '密码必须6到12位，且不能出现空格'],
        repass: function (value) {
            var pass = $("#userPassword").val();
            if (!new RegExp(pass).test(value)) {
                return '两次输入的密码不一致';
            }

        }
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


