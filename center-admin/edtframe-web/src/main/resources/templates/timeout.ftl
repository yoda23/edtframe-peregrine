<#include "common.ftl">
<!DOCTYPE HTML>
<html>
<head>
    <title>登陆超时</title>
</head>
<body>
<script type="text/javascript">
    layui.use('layer', function () {
        var layer = layui.layer;
        layer.alert('用户登录超时，请重新登录', {
            icon: 0,
            skin: 'layer-ext-moon'
        }, function () {
            top.location.href = '${ctx}/login';
        });
    });
</script>
</body>
</html>
