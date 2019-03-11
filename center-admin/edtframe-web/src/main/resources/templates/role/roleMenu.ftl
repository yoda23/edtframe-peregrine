<!DOCTYPE HTML>
<html>
<head>
    <#include "../common.ftl">
    <title></title>
</head>
<body>
<div class="cus-fluid">
    <div class="layui-col-md12">
        <form class="layui-form" action="" id="form">
            <input type="hidden" id="id" name="id" value="${role.id!}">
            <input type="hidden" id="menuId" name="menuId" value="${role.menuId!}">
            <div class="layui-form-item">
                <label class="layui-form-label"><span
                        class="c-red">*</span>角色名称：</label>
                <div class="layui-input-block">
                    <input type="text" name="name" id="name" lay-verify="required" autocomplete="off"
                           placeholder="角色名称" value="${role.name!}" class="layui-input" readonly="readonly">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"></label>
                <div class="layui-input-block">
                    <ul id="menuTree" class="ztree"></ul>
                </div>
            </div>
            <div class="layui-layer-btn layui-layer-btn- cus-psike">
                <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="save">确 定</button>
                <button class="layui-btn layui-btn-danger " id="closeWindow">取 消</button>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript" src="${ctx}/static/js/role/role.menu.js"></script>
<script type="text/javascript">
    layui.use(['form', 'layer'], function () {
        var layer = layui.layer, form = layui.form;
        $.ajax({
            url: '${ctx}/menu/list',
            type: 'POST',
            success: function (data) {
                jQuery.fn.zTree.init(jQuery("#menuTree"), setting, data);
                var treeObj = jQuery.fn.zTree.getZTreeObj("menuTree");
                treeObj.expandAll(false);
                var menuId = "${role.menuId}";
                var arrys = menuId.split(",");
                if (menuId !== "") {
                    for (var i = 0; i < arrys.length; i++) {
                        var node = treeObj.getNodeByParam("id", arrys[i],
                                null);
                        treeObj.checkNode(node, true, false, true);
                    }
                }
            },
            error: function (msg) {
                layer.alert(msg);
            }
        });
    });
</script>
</body>
</html>
