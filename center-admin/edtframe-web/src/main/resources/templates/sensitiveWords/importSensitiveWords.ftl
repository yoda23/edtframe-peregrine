<!DOCTYPE HTML>
<html>
<head>
    <#include "../common.ftl">
    <title></title>
</head>
<body>
<div class="cus-fluid" style="min-height: initial">
    <div class="layui-col-md12">
        <form class="layui-form" action="" id="form">
            <div class="layui-card">
                <div class="layui-card-body">
                    <button type="button" class="layui-btn" id="uploadBtn">
                        <i class="layui-icon">&#xe67c;</i>上传文件
                    </button>
                    <input type="text" name="filePath" id="filePath" lay-verify="required" autocomplete="off"
                           placeholder="文件地址" class="layui-inline layui-word-aux cus-input-file" readonly="readonly">
                </div>
            </div>
            <div class="layui-layer-btn layui-layer-btn- cus-psike">
                <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="save">确 定</button>
                <button class="layui-btn layui-btn-danger" id="closeWindow">取 消</button>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript" src="${ctx}/static/js/sensitiveWords/sensitiveWords.import.js"></script>
</body>
</html>
