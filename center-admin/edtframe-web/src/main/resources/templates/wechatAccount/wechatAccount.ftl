<!DOCTYPE HTML>
<html>
<head>
    <#include "../common.ftl">
    <title>微信账号管理</title>
</head>
<body>
<div class="cus-fluid">
    <div class="layui-col-md12">
        <div class="layui-card">
            <div class="layui-form layui-card-header layuiadmin-card-header-auto cus-mou-ts">
                <div class="test-table-reload-btn">
                    <div class="layui-inline">
                        <input class="layui-input" placeholder="微信名称" name="name" id="name" autocomplete="off">
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
        src="${ctx }/static/js/wechatAccount/wechatAccount.js"></script>
<script type="text/html" id="toolbar">
    <@shiro.hasPermission name='102'>
        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="update"><i class="layui-icon layui-icon-edit"></i>编辑</a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name='102'>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-edit"></i>删除</a>
    </@shiro.hasPermission>
</script>
<script type="text/html" id="type">
    {{#  if(d.type == 1){ }}
    <span class="layui-badge layui-bg-green">公众号</span>
    {{#  } else { }}
    <span class="layui-badge layui-bg-cyan">小程序</span>
    {{#  } }}
</script>
</body>
</html>
