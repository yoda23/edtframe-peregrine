layui.use(['table', 'layer'], function () {
    var table = layui.table, layer = layui.layer;
    table.render({
        id: 'tableList',
        elem: '#tableList',
        method: 'post',
        url: ctx + '/user/condition',
        cellMinWidth: 150,
        limit: pageLength,
        limits: [pageLength],
        cols: [[
            {field: '', title: '序号', unresize: true, type: 'numbers'},
            {field: 'loginId', title: '登陆名', unresize: true},
            {field: 'name', title: '姓名', sort: true, unresize: true},
            {field: 'mechanismsName', title: '机构', unresize: true},
            {field: 'roleName', title: '权限', unresize: true},
            {field: '', title: '状态', toolbar: '#active', unresize: true},
            {field: 'addUser', title: '添加人', unresize: true},
            {field: 'addTime', title: '添加时间', unresize: true},
            {field: '', title: '操作', toolbar: '#toolbar', unresize: true, width: 250},
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
                    title: '修改用户',
                    content: ctx + '/user/toUpdate/' + data.id,
                    end: function () {
                        table.reload('tableList');
                    }
                });
                break;
            case 'up':
                layer.confirm("确定要启用该用户吗", {
                    btn: ['确定', '取消']
                }, function () {
                    $.ajax({
                        type: "POST",
                        url: ctx + '/user/update/state/' + data.id + '/1',
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
                layer.confirm("确定要禁用该用户吗？", {
                    btn: ['确定', '取消']
                    // 按钮
                }, function () {
                    $.ajax({
                        type: "POST",
                        url: ctx + '/user/update/state/' + data.id + '/2',
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
            case 'rest':
                layer.confirm('确定要重置该用户的密码吗？', {
                    btn: ['确定', '取消']
                    // 按钮
                }, function () {
                    $.ajax({
                        type: "POST",
                        url: ctx + '/user/rest/password/' + data.id,
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
                content: ctx + '/redirect?page=user/userAdd',
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
