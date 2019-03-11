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
            <div class="layui-form-item">
                <label class="layui-form-label"><span
                        class="c-red">*</span>登录账号：</label>
                <div class="layui-input-block">
                    <input type="text" name="loginId" id="loginId" lay-verify="required" autocomplete="off"
                           placeholder="登录账号" class="layui-input" value="${user.loginId!}" readonly="readonly">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"><span
                        class="c-red">*</span>姓名：</label>
                <div class="layui-input-block">
                    <input type="text" name="name" id="name" lay-verify="required" autocomplete="off"
                           placeholder="姓名" class="layui-input" value="${user.name!}">
                </div>
            </div>
            <div class="layui-layer-btn layui-layer-btn- cus-psike">
                <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="save">确 定</button>
                <button class="layui-btn layui-btn-danger " id="closeWindow">取 消</button>
            </div>
        </form>
    </div>

</div>
<script type="text/javascript" src="${ctx}/static/js/user/user.info.js"></script>
</body>
</html>
