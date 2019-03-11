<!DOCTYPE HTML>
<#include "common.ftl">
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <script type="text/javascript" src="${ctx}/static/js/weixin/1.3.2/jweixin-1.3.2.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/jquery/2.1.4/jquery.js"></script>
    <script type="text/javascript">
        $(function () {
            wx.miniProgram.switchTab({url: '/pages/vip/vip'});
        });
    </script>
</head>
<!--<script type="text/javascript" src="../../js/jweixin.js"></script>-->
<body>
<div class="hui-s-wart">
    <span class="title-icon iconfont icon-dengdai"></span>
    <p class="title-le">程序跳转中，请稍后...</p>
</div>
</body>
</html>