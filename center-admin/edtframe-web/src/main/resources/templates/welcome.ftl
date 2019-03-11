<!DOCTYPE HTML>
<html>
<head>
    <#include "common.ftl">
    <title>欢迎页</title>
</head>
<body>
<div class="cus-fluid">
    <div class="cus-content">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body">
                    <div class="layui-carousel" style="background: #fff">
                        <img src="${ctx}/static/layer-ui/images/edtlogo.png"
                             style="height:300px; margin: 100px auto; display: block;"/>
                    </div>
                </div>
            </div>
            <div class="layui-col-md12">
                <div class="layui-card">
                    <div class="layui-card-header">版本信息123</div>
                    <div class="layui-card-body layui-text">
                        <table class="layui-table">
                            <tbody>
                            <tr>
                                <td>当前版本</td>
                                <td> v1.0.14</td>
                            </tr>
                            <tr>
                                <td>版权所有</td>
                                <td>黑龙江一点通科技开发有限公司 Copyright © 2014</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        var hig_t = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight;
        document.getElementById("hig_t").style.height = hig_t + "px";
        console.info("welcome");
    </script>
</body>
</html>