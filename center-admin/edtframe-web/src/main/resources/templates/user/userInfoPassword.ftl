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
                        class="c-red">*</span>原始密码：</label>
                <div class="layui-input-block">
                    <input type="password" name="oldPassword" id="oldPassword" lay-verify="pass"
                           autocomplete="off"
                           placeholder="原始密码" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"><span
                        class="c-red">*</span>初始密码：</label>
                <div class="layui-input-block">
                    <input type="password" name="loginPassword" id="userPassword" lay-verify="pass"
                           autocomplete="off"
                           placeholder="初始密码" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"><span
                        class="c-red">*</span>确认密码：</label>
                <div class="layui-input-block">
                    <input type="password" name="userAddPassword2" id="userPassword2" lay-verify="repass"
                           autocomplete="off"
                           placeholder="确认密码" class="layui-input">
                </div>
            </div>
            <div class="layui-layer-btn layui-layer-btn-"
                 style="position: fixed;bottom:0;left: 0;width: 100%;box-sizing: border-box">
                <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="save">确 定</button>
                <button class="layui-btn layui-btn-danger " id="closeWindow">取 消</button>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript" src="${ctx}/static/js/user/user.password.js"></script>
</body>
</html>
