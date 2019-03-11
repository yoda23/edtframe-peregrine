<!DOCTYPE HTML>
<html>
<head>
    <#include "../common.ftl">
    <title>任务管理</title>
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
                <@shiro.hasPermission name='901'>
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
        src="${ctx }/static/js/scheduleJob/scheduleJob.js"></script>
<script type="text/html" id="toolbar">
    {{#  if(d.jobStatus == "running"){ }}
    <@shiro.hasPermission name='904'>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="down"><i
                class="layui-icon layui-icon-close layui-icon-close"></i>停止</a>
    </@shiro.hasPermission>
    {{#  } else { }}
    <@shiro.hasPermission name='904'>
        <a class="layui-btn layui-btn-xs" lay-event="up"><i
                class="layui-icon layui-icon-ok"></i>启动</a>
    </@shiro.hasPermission>
    {{#  } }}
    <@shiro.hasPermission name='902'>
        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="update"><i class="layui-icon layui-icon-edit"></i>编辑</a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name='903'>
        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="delete"><i class="layui-icon layui-icon-edit"></i>删除</a>
    </@shiro.hasPermission>
</script>
<script type="text/html" id="jobStatus">
    {{#  if(d.jobStatus == "running"){ }}
    <span class="layui-badge layui-bg-green">已启动</span>
    {{#  } else { }}
    <span class="layui-badge layui-bg-badge">已停止</span>
    {{#  } }}
</script>
</body>
</html>
