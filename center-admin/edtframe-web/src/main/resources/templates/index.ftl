<!DOCTYPE HTML>
<html>
<head>
    <#include "common.ftl">
    <title>EDT-FRAME基础框架</title>
</head>
<body class="layui-layout-body">
<div id="LAY_app">
    <div class="layui-layout layui-layout-admin">
        <div class="layui-header">
            <!-- 头部区域 -->
            <ul class="layui-nav layui-layout-left">
                <li class="layui-nav-item layadmin-flexible" lay-unselect>
                    <a layadmin-event="flexible" title="侧边伸缩">
                        <i class="layui-icon layui-icon-shrink-right kit-side-fold"></i>
                    </a>
                </li>
                <li class="layui-nav-item" lay-unselect>
                    <a href="javascript:;" class="tabReload" layadmin-event="refresh" title="刷新">
                        <i class="layui-icon layui-icon-refresh-3"></i>
                    </a>
                </li>
            </ul>
            <ul class="layui-nav layui-layout-right" lay-filter="layadmin-layout-right">
                <li class="layui-nav-item">
                    <a href="javascript:;">
                        <span><font class="c-999"></font>${user.name! }（${user.mechanisms.name!}）<font
                                class="layui-badge layui-bg-orange cus-gunli">${user.role.name! }</font></span>
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a id="userInfo" lay-href="">基本资料</a></dd>
                        <dd><a id="userInfoPassword" lay-href="">修改密码</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="${ctx }/logout" layadmin-event="about">退出</a>
                </li>
            </ul>
        </div>

        <!-- 侧边菜单 -->
        <div class="layui-side layui-side-menu layui-bg-black">
            <div class="layui-side-scroll">
                <div class="layui-logo" lay-href="home/console.html">
                    <span class="logohis">EDT-FRAME基础框架</span>
                <#--<span class="logohow"><img src="images/logo.png"></span>-->
                </div>
                <ul class="layui-nav layui-nav-tree" lay-shrink="all" id="LAY-system-side-menu"
                    lay-filter="layadmin-system-side-menu">
                    <#list listIndexRoleMenu as listMenufirst>
                        <li data-name="home" class="layui-nav-item cl-1">
                            <a href="javascript:;" lay-tips="${listMenufirst.name}" lay-direction="2">
                                <i class="layui-icon ${listMenufirst.icon!}"></i>
                                <cite>${listMenufirst.name}</cite>
                            </a>
                            <#list listMenufirst.listMenuChild as listMenuSecond>
                                <dl class="layui-nav-child">
                                    <dd data-name="console">
                                        <a href="${listMenuSecond.openUrl! }" class="menuItem"
                                           target="main_self_frame"><span>${listMenuSecond.name !}</span></a>
                                    </dd>
                                </dl>
                            </#list>
                        </li>
                    </#list>
                </ul>


            </div>
        </div>

        <!-- 页面标签 -->
        <div class="layadmin-pagetabs content-tabs" id="LAY_app_tabs">
            <div class="layui-icon layadmin-tabs-control layui-icon-prev tabLeft"></div>
            <div class="layui-icon layadmin-tabs-control layui-icon-next tabRight"></div>
            <div class="layui-icon layadmin-tabs-control layui-icon-down">
                <ul class="layui-nav layadmin-tabs-select" lay-filter="layadmin-pagetabs-nav">
                    <li class="layui-nav-item" lay-unselect>
                        <a href="javascript:;"></a>
                        <dl class="layui-nav-child layui-anim-fadein">
                            <dd layadmin-event="closeThisTabs"><a class="tabCloseCurrent" href="javascript:void(0);">关闭当前标签页</a>
                            </dd>
                            <dd layadmin-event="closeOtherTabs"><a class="tabCloseOther" href="javascript:void(0);">关闭其它标签页</a>
                            </dd>
                            <dd layadmin-event="closeAllTabs"><a class="tabCloseAll"
                                                                 href="javascript:void(0);">关闭全部标签页</a></dd>
                        </dl>
                    </li>
                </ul>
            </div>
            <nav class="layui-tab page-tabs menuTabs" lay-allowClose="true" lay-filter="layadmin-layout-tabs">
                <ul class="layui-tab-title page-tabs-content">
                <#--<li href="${ctx}/html/welcome.html" class="layui-this menuTab" id="home"><span-->
                <#--class="layui-icon layui-icon-home"></span></li>-->
                </ul>
            </nav>
        </div>

        <!-- 主体内容 -->
        <div class="layui-body cus-body-bg" id="LAY_app_body">
            <div class="layadmin-tabsbody-item layui-show mainContent" id="content-main">
                <!-- <iframe src="home/console.html" frameborder="0" class="layadmin-iframe"></iframe> -->
                <iframe src="" name="iframeMain"
                        frameborder="0" class="layadmin-iframe RuoYi_iframe cus-frame"
                        seamless></iframe>
            </div>
        </div>
        <!-- 辅助元素，一般用于移动设备下遮罩 -->
        <div class="layadmin-body-shade" layadmin-event="shade"></div>
    </div>
</div>


<script type="text/javascript" src="${ctx}/static/layer-ui/lib/index.js"></script>
<script type="text/javascript">

</script>
</body>
</html>
