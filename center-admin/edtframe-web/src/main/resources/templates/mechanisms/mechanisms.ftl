<!DOCTYPE HTML>
<html>
<head>
    <#include "../common.ftl">
    <title>用户管理</title>
</head>
<body>
<div class="cus-fluid">
    <div class="layui-col-md12 cus-disflex">
        <div class="cus-prs layui-col-md3" id="leftHeight">
            <div class="cus-pel">&nbsp;</div>
            <div style="background: #fff;">
                <div class="layui-card cus_tree_scroll cus-pous">
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
                <#--<button class="layui-btn layui-btn-xs" >展开</button>-->
                <#--<button class="layui-btn layui-btn-xs" >收回</button>-->
                    <ul id="mechanismsTree" class="ztree"></ul>
                </div>
            </div>
            <div class="cus-down layui-icon layui-icon-left" id="downPs"></div>
        </div>
        <div class="cus-right layui-col-md9">
            <div class="layui-card">
                <div class="layui-form layui-card-header layuiadmin-card-header-auto cus-mou-ts">
                    <div class="test-table-reload-btn">
                        <div class="layui-inline">
                            <input class="layui-input" placeholder="机构名称" name="name" id="name" autocomplete="off">
                        </div>
                        <button class="layui-btn" data-type="search"><i
                                class="layui-icon layui-icon-search layuiadmin-button-btn"></i>搜索
                        </button>
                    </div>
                </div>
                <div class="layui-card-body">
                    <@shiro.hasPermission name='301'>
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
</div>
<script type="text/javascript"
        src="${ctx }/static/js/mechanisms/mechanisms.js"></script>
<script type="text/html" id="toolbar">
    <@shiro.hasPermission name='302'>
        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="update"><i class="layui-icon layui-icon-edit"></i>编辑</a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name='303'>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del"><i
                class="layui-icon layui-icon-close layui-icon-delete"></i>删除</a>
    </@shiro.hasPermission>
</script>
<script type="text/html" id="parentName">
    {{#  if(d.id == 0){ }}
    <span class="layui-badge layui-bg-badge">无上级</span>
    {{#  } else { }}
    <span class="">{{d.parentName}}</span>
    {{#  } }}
</script>
</body>
</html>
