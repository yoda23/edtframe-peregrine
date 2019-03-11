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
            <div class="layui-form layui-card-header layuiadmin-card-header-auto cus-mou-ts">
                <div class="test-table-reload-btn">
                    <div class="layui-inline">
                        <input class="layui-input" placeholder="内容" name="content" id="content" autocomplete="off">
                    </div>
                    <button class="layui-btn" data-type="search"><i
                            class="layui-icon layui-icon-search layuiadmin-button-btn"></i>搜索
                    </button>
                </div>
            </div>
            <div class="layui-card-body ">
                <div style="padding-bottom: 10px;" class="layui-btn-group">
                    <@shiro.hasPermission name='1901'>
                        <button class="layui-btn layuiadmin-button-btn" data-method="import"><i
                                class="layui-icon layui-icon-upload-drag layuiadmin-button-btn"></i>导入
                        </button>
                    </@shiro.hasPermission>
                </div>
                <div style="padding-bottom: 10px;" class="layui-btn-group">
                    <@shiro.hasPermission name='1901'>
                        <button class="layui-btn layuiadmin-button-btn" data-method="add"><i
                                class="layui-icon layui-icon-add-1 layuiadmin-button-btn"></i>添加
                        </button>
                    </@shiro.hasPermission>
                </div>
                <table class="layui-hide" id="tableList" lay-filter="tableList"></table>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript"
        src="${ctx }/static/js/sensitiveWords/sensitiveWords.js"></script>
<script type="text/html" id="toolbar">
    <@shiro.hasPermission name='1902'>
        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="update"><i class="layui-icon layui-icon-edit"></i>修改</a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name='1903'>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
    </@shiro.hasPermission>
</script>
</body>
</html>
