<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <#include "common.ftl">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/layer-ui/css/login.css" media="all"/>
    <title>EDT-FRAME通基础框架</title>
</head>
<body layadmin-themealias="default" class="bag-bg">
<div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login">
    <div class="layadmin-user-login-main cus-login-top">
        <div class="login-tou"></div>
        <div class="layadmin-user-login-box layadmin-user-login-header cus-pidk">
            <p>EDT-FRAME基础框架</p>
        </div>
        <form id="loginForm" class="layui-form">
            <div class="layadmin-user-login-box layadmin-user-login-body layui-form">
                <div class="layui-form-item">
                    <label class="layadmin-user-login-icon layui-icon layui-icon-username"
                           for="LAY-user-login-username"></label>
                    <input type="text" name="loginId" id="loginId" lay-verify="required"
                           placeholder="用户名" class="layui-input">
                </div>
                <div class="layui-form-item">
                    <label class="layadmin-user-login-icon layui-icon layui-icon-password"
                           for="LAY-user-login-password"></label>
                    <input type="password" name="loginPassword" id="loginPassword" placeholder="密码"
                           class="layui-input">
                </div>
                <div class="layui-form-item">
                    <div class="layui-row">
                        <div class="layui-col-xs7">
                            <label class="layadmin-user-login-icon layui-icon layui-icon-vercode"
                                   for="LAY-user-login-vercode"></label>
                            <input type="text" name="validateCode" id="validateCode" lay-verify="required"
                                   placeholder="图形验证码" class="layui-input">
                        </div>
                        <div class="layui-col-xs5">
                            <div style="margin-left: 10px;">
                                <img src="${ctx}/getValidateCode"
                                     class="layadmin-user-login-codeimg" id="validateCodeImg">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <button id="" class="layui-btn layui-btn-fluid" lay-submit="" lay-filter="login">登&nbsp;&nbsp;录
                    </button>
                </div>
                <!-- 重要请误删除 -->
                <!-- <div class="layui-trans layui-form-item layadmin-user-login-other">
                  <label>社交账号登入</label>
                  <a href="javascript:;"><i class="layui-icon layui-icon-login-qq"></i></a>
                  <a href="javascript:;"><i class="layui-icon layui-icon-login-wechat"></i></a>
                  <a href="javascript:;"><i class="layui-icon layui-icon-login-weibo"></i></a>
                  <a href="reg.html" class="layadmin-user-jump-change layadmin-link">注册帐号</a>
                </div> -->
            </div>
        </form>
    </div>
    <div class="layui-trans layadmin-user-login-footer">
        <p>© 2018 <a href="http://www.edt.cc/" target="_blank">www.edt.cc</a></p>
        <p>
            <span><a href="http://www.edt.cc" target="_blank">黑龙江一点通科技开发有限公司版权所有</a></span>
        </p>
    </div>
    <script type="text/javascript"
            src="${ctx }/static/layer-ui/lib/login.js"></script>
</div>
</html>
