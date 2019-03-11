<!DOCTYPE HTML>
<html>
<head>
    <#include "../common.ftl">
    <title></title>
</head>
<body>
<div class="cus-fluid">
    <div class="layui-col-md12">
        <form class="layui-form" action="" id="form" lay-filter="form">
            <input type="hidden" id="id" name="id" value="${user.id!}">
            <input type="hidden" id="userMechanismsRights" name="userMechanismsRights"
                   value="${userMechanismsRights!}">
            <div class="cus-disflex">
                <div style="width: 260px;position: relative;">
                    <div>&nbsp;</div>
                    <div class="layui-card cus_tree_scroll cus-pous">
                    <#--<button class="layui-btn layui-btn-xs" id="expandAll">展开</button>-->
                    <#--<button class="layui-btn layui-btn-xs" id="notExpandAll">收回</button>-->
                        <div class="layui-card-header cus-ulis-left">组织机构
                            <div class="cus-ruslid">
                                <div class="layui-btn-container">
                                    <div class="layui-btn-group">
                                        <button id="refresh" class="layui-btn layui-btn-primary layui-btn-sm"><i
                                                class="layui-icon ilu layui-icon-refresh-3"></i></button>
                                        <button id="expandAll" class="layui-btn layui-btn-primary layui-btn-sm"><i
                                                class="layui-icon ilu layui-icon-down"></i></button>
                                        <button style="display: none" id="notExpandAll"
                                                class="layui-btn layui-btn-primary layui-btn-sm"><i
                                                class="layui-icon ilu layui-icon-up"></i></button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <ul id="userTree" class="ztree"></ul>
                    </div>
                </div>
                <div class="cus-right">
                    <div class="layui-form-item">
                        <label class="layui-form-label"><span
                                class="c-red">*</span>登录账号：</label>
                        <div class="layui-input-block">
                            <input type="text" name="loginId" id="loginId" lay-verify="required" autocomplete="off"
                                   placeholder="登录账号" class="layui-input" value="${user.loginId }" readonly="readonly">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label"><span
                                class="c-red">*</span>姓名：</label>
                        <div class="layui-input-block">
                            <input type="text" name="name" id="name" lay-verify="required" autocomplete="off"
                                   placeholder="姓名" class="layui-input" value="${user.name}">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label"><span
                                class="c-red">*</span>角色：</label>
                        <div class="layui-input-block">
                            <label>
                                <select xm-select="roleId" lay-filter="roleId" xm-select-create="roleId"
                                        xm-select-radio="" lay-verify="required"
                                        name="roleId" id="roleId">
                                </select>
                            </label>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label"><span
                                class="c-red">*</span>所属地市：</label>
                        <div class="layui-input-block">
                            <input type="text" name="parentName" id="parentName" lay-verify="required"
                                   autocomplete="off" placeholder="所属地市" class="layui-input" readonly="readonly"
                                   value="${user.mechanisms.name}">
                            <input type="hidden" name="mechanismsId" id="mechanismsId" lay-verify="required"
                                   autocomplete="off" placeholder="" class="layui-input" readonly="readonly"
                                   value="${user.mechanismsId}">
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-layer-btn layui-layer-btn-"
                 style="position: fixed;bottom:0;left: 0;width: 100%;box-sizing: border-box">
                <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="save">确 定</button>
                <button class="layui-btn layui-btn-danger" id="closeWindow">取 消</button>
            </div>
        </form>
    </div>

</div>
<script type="text/javascript" src="${ctx}/static/js/user/user.update.js"></script>
<script type="text/javascript">
    layui.use(['layer', 'formSelects', 'form'], function () {
        var layer = layui.layer, formSelects = layui.formSelects, form = layui.form;
        var treeObj;
        var treeData;
        var userTree = jQuery("#userTree");
        formSelects.data('roleId', 'server', {
            type: 'post',
            url: ctx + '/role/info',
            success: function (id, url, searchVal, result) {
                formSelects.value('roleId', ['${user.roleId }']);
            }
        });
        $.ajax({
            url: ctx + '/mechanisms/user/tree',
            type: 'POST',
            async: false,
            success: function (data) {
                treeDate = data;
                jQuery.fn.zTree.init(userTree, setting, data);
                treeObj = jQuery.fn.zTree.getZTreeObj("userTree");
                treeObj.expandAll(false);
                var userMechanismsRights = "${userMechanismsRights}";
                var arrys = userMechanismsRights.split(",");
                if (userMechanismsRights !== "") {
                    for (var i = 0; i < arrys.length; i++) {
                        var node = treeObj.getNodeByParam("id", arrys[i],
                                null);
                        treeObj.checkNode(node, true, true);
                    }
                }
            },
            error: function (msg) {
                layer.alert(msg);
            }
        });
        // console.info(treeObj.setting.check.checkbox = {"Y": "s", "N": "s"});
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
    });
</script>
</body>
</html>
