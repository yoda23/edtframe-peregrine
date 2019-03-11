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
                        class="c-red">*</span>任务名称：</label>
                <div class="layui-input-block">
                    <input type="text" name="jobName" id="jobName" lay-verify="required" autocomplete="off"
                           placeholder="任务名称" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"><span
                        class="c-red">*</span>立即启动：</label>
                <div class="layui-input-block">
                    <input type="checkbox" name="jobStatus" id="jobStatus" lay-skin="switch" lay-text="ON|OFF">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"><span
                        class="c-red">*</span>任务分组：</label>
                <div class="layui-input-block">
                    <input type="text" name="jobGroup" id="jobGroup" lay-verify="required" autocomplete="off"
                           placeholder="任务分组" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"><span
                        class="c-red">*</span>表达式：</label>
                <div class="layui-input-block">
                    <input type="text" name="cronExpression" id="cronExpression" lay-verify="required"
                           autocomplete="off"
                           placeholder="表达式" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"><span
                        class="c-red">*</span>类名称：</label>
                <div class="layui-input-block">
                    <input type="text" name="beanName" id="beanName" lay-verify="required" autocomplete="off"
                           placeholder="类名称" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"><span
                        class="c-red">*</span>方法名称：</label>
                <div class="layui-input-block">
                    <input type="text" name="methodName" id="methodName" lay-verify="required" autocomplete="off"
                           placeholder="方法名称" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"><span
                        class="c-red">*</span>任务备注：</label>
                <div class="layui-input-block">
                    <input type="text" name="description" id="description" lay-verify="required" autocomplete="off"
                           placeholder="任务备注" class="layui-input">
                </div>
            </div>
            <div class="layui-layer-btn layui-layer-btn- cus-psike">
                <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="save">确 定</button>
                <button class="layui-btn layui-btn-danger" id="closeWindow">取 消</button>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript" src="${ctx}/static/js/scheduleJob/scheduleJob.add.js"></script>
</body>
</html>
