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
            <input type="hidden" id="id" name="id" value="${role.id }">
            <div class="layui-form-item">
                <label class="layui-form-label"><span
                        class="c-red">*</span>角色名称：</label>
                <div class="layui-input-block">
                    <input type="text" name="name" id="name" lay-verify="required" autocomplete="off"
                           placeholder="角色名称" class="layui-input" value="${role.name }">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"><span
                        class="c-red">*</span>角色备注：</label>
                <div class="layui-input-block">
                    <input type="text" name="reMark" id="reMark" lay-verify="" autocomplete="off"
                           placeholder="角色备注" class="layui-input" value="${role.reMark }">
                </div>
            </div>
            <div class="layui-layer-btn layui-layer-btn- cus-psike">
                <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="save">确 定</button>
                <button class="layui-btn layui-btn-danger" id="closeWindow">取 消</button>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript" src="${ctx}/static/js/role/role.update.js"></script>
</body>
</html>
