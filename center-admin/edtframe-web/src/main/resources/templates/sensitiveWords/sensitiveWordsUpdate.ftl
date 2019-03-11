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
            <input type="hidden" id="id" name="id" value="${sensitiveWords.id }">
            <div class="layui-form-item">
                <label class="layui-form-label"><span
                        class="c-red">*</span>敏感内容：</label>
                <div class="layui-input-block">
                    <input type="text" name="content" id="content" lay-verify="required" autocomplete="off"
                           placeholder="敏感内容" class="layui-input" value="${sensitiveWords.content }">
                </div>
            </div>
            <div class="layui-layer-btn layui-layer-btn- cus-psike">
                <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="save">确 定</button>
                <button class="layui-btn layui-btn-danger" id="closeWindow">取 消</button>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript" src="${ctx}/static/js/sensitiveWords/sensitiveWords.update.js"></script>
</body>
</html>
