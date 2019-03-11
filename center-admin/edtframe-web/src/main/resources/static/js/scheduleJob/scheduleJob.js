layui.use(['table', 'layer'], function () {
    var table = layui.table, layer = layui.layer;
    table.render({
        id: 'tableList',
        elem: '#tableList',
        method: 'post',
        url: ctx + '/scheduleJob/condition',
        cellMinWidth: 150,
        limit: pageLength,
        limits: [pageLength],
        cols: [[
            {field: '', title: '序号', unresize: true, type: 'numbers'},
            {field: 'jobName', title: '任务名称', unresize: true},
            {field: '', title: '任务状态', toolbar: '#jobStatus', unresize: true},
            {field: 'jobGroup', title: '任务分组', unresize: true},
            {field: 'cronExpression', title: '任务表达式', unresize: true},
            {field: 'description', title: '任务备注', unresize: true},
            {field: 'beanName', title: '类名称', unresize: true},
            {field: 'methodName', title: '方法名称', unresize: true},
            {field: 'addTime', title: '添加时间', unresize: true},
            {field: '', title: '操作', toolbar: '#toolbar', unresize: true, width: 250}
        ]],
        page: true,
    });
    table.on('tool(tableList)', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        switch (layEvent) {
            case 'update':
                layer.open({
                    type: 2,
                    area: ['800px', '500px'],
                    fix: false,
                    maxmin: false,
                    shade: 0.4,
                    title: '修改任务',
                    content: ctx + '/scheduleJob/toUpdate/' + data.id,
                    end: function () {
                        table.reload('tableList');
                    }
                });
                break;
            case 'up':
                layer.confirm("确定要启动该任务吗", {
                    btn: ['确定', '取消']
                }, function () {
                    $.ajax({
                        type: "POST",
                        url: ctx + '/scheduleJob/resumeJob/' + data.id,
                        cache: false,
                        async: true,
                        success: function (result) {
                            var index = layer.alert(result.status.message, function () {
                                layer.close(index);
                                table.reload('tableList');
                            });
                        }
                    });
                });
                break;
            case 'down':
                layer.confirm("确定要停止该任务吗？", {
                    btn: ['确定', '取消']
                    // 按钮
                }, function () {
                    $.ajax({
                        type: "POST",
                        url: ctx + '/scheduleJob/pauseJob/' + data.id,
                        cache: false,
                        async: true,
                        success: function (result) {
                            var index = layer.alert(result.status.message, function () {
                                layer.close(index);
                                table.reload('tableList');
                            });
                        }
                    });
                });
                break;
            case 'delete':
                layer.confirm('确定删除该任务吗？', {
                    btn: ['确定', '取消']
                    // 按钮
                }, function () {
                    $.ajax({
                        type: "POST",
                        url: ctx + '/scheduleJob/delete/' + data.id,
                        cache: false,
                        async: true,
                        success: function (result) {
                            var index = layer.alert(result.status.message, function () {
                                layer.close(index);
                                table.reload('tableList');
                            });
                        }
                    });
                });
                break;
        }
    });
    var $ = layui.$, active = {
        search: function () {
            table.reload('tableList', {
                page: {
                    curr: 1
                },
                where: {
                    name: $('#name').val()
                }
            });
        },
        add: function () {
            layer.open({
                type: 2,
                area: ['800px', '500px'],
                fix: false, // 不固定
                maxmin: false,
                shade: 0.4,
                title: '添加用户',
                content: ctx + '/redirect?page=scheduleJob/scheduleJobAdd',
                end: function () {
                    table.reload('tableList');
                }
            });
        }
    };
    $('.layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
        var othis = $(this), method = othis.data('method');
        active[method] ? active[method].call(this, othis) : '';
    });
});
