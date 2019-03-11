<!DOCTYPE HTML>
<html>
<head>
    <#include "../common.ftl">
    <title>角色管理</title>
</head>
<body>
<div class="cus-fluid">
    <div class="layui-col-md12">
        <div class="layui-card">
            <div class="layui-card-body">
                <@shiro.hasPermission name='201'>
                    <div style="padding-bottom: 10px;">
                        <button class="layui-btn layuiadmin-button-btn" data-method="add"><i
                                class="layui-icon layui-icon-add-1 layuiadmin-button-btn"></i>添加
                        </button>
                    </div>
                </@shiro.hasPermission>
                <table class="layui-hide" id="tableList" lay-filter="tableList"></table>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript"
        src="${ctx }/static/js/role/role.js"></script>
<script type="text/html" id="toolbar">
    <@shiro.hasPermission name='202'>
        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="update"><i class="layui-icon layui-icon-edit"></i>编辑</a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name='203'>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name='204'>
        <a class="layui-btn layui-btn-xs" lay-event="menu"><i class="layui-icon layui-icon-form"></i>分配菜单</a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name='205'>
        <a class="layui-btn layui-btn-xs" lay-event="rights"><i class="layui-icon layui-icon-group"></i>分配权限</a>
    </@shiro.hasPermission>
</script>
</body>
</html>
