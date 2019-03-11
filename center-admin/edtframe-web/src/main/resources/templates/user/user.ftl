<!DOCTYPE HTML>
<html>
<head>
    <#include "../common.ftl">
    <title>用户管理</title>
</head>
<body>
<div class="cus-fluid">
    <div class="layui-col-md12">
        <div class="layui-card">
            <div class="layui-form layui-card-header layuiadmin-card-header-auto cus-mou-ts">
                <div class="test-table-reload-btn">
                    <div class="layui-inline">
                        <input class="layui-input" placeholder="用户姓名" name="name" id="name" autocomplete="off">
                    </div>
                    <button class="layui-btn" data-type="search"><i
                            class="layui-icon layui-icon-search layuiadmin-button-btn"></i>搜索
                    </button>
                </div>
            </div>

            <div class="layui-card-body">
                <@shiro.hasPermission name='101'>
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
        src="${ctx }/static/js/user/user.js"></script>
<script type="text/html" id="toolbar">
    <@shiro.hasPermission name='102'>
        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="update"><i class="layui-icon layui-icon-edit"></i>编辑</a>
    </@shiro.hasPermission>
    {{#  if(d.active == 1){ }}
    <@shiro.hasPermission name='104'>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="down"><i
                class="layui-icon layui-icon-close layui-icon-close"></i>禁用</a>
    </@shiro.hasPermission>
    {{#  } else { }}
    <@shiro.hasPermission name='104'>
        <a class="layui-btn layui-btn-xs" lay-event="up"><i
                class="layui-icon layui-icon-ok"></i>启用</a>
    </@shiro.hasPermission>
    {{#  } }}
    <@shiro.hasPermission name='105'>
        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="rest"><i
                class="layui-icon layui-icon-refresh"></i>重置密码</a>
    </@shiro.hasPermission>
</script>
<script type="text/html" id="active">
    {{#  if(d.active == 1){ }}
    <span class="layui-badge layui-bg-green">启用</span>
    {{#  } else { }}
    <span class="layui-badge layui-bg-badge">禁用</span>
    {{#  } }}
</script>
</body>
</html>
