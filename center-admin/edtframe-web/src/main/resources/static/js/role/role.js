layui.use(['table', 'layer'], function () {
    var table = layui.table, layer = layui.layer;
    table.render({
        id: 'tableList',
        elem: '#tableList',
        method: 'post',
        url: ctx + '/role/condition',
        cellMinWidth: 150,
        limit: pageLength,
        limits: [pageLength],
        cols: [[
            {field: '', title: '序号', unresize: true, type: 'numbers'},
            {field: 'name', title: '姓名', sort: true, unresize: true},
            {field: 'reMark', title: '备注', unresize: true},
            {field: 'addUserName', title: '添加人', unresize: true},
            {field: 'addTime', title: '添加时间', unresize: true},
            {field: '', title: '操作', toolbar: '#toolbar', unresize: true},
        ]],
        page: true
    });
    table.on('tool(tableList)', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        switch (layEvent) {
            case 'update':
                layer.open({
                    type: 2,
                    area: ['800px', '500px'],
                    fix: false, // 不固定
                    maxmin: false,
                    shade: 0.4,
                    title: '修改角色',
                    content: ctx + '/role/toUpdate/' + data.id,
                    end: function () {
                        table.reload('tableList');
                    }
                });
                break;
            case 'del':
                layer.confirm('确定要删除此条数据么？', {
                    btn: ['确定', '取消']
                    // 按钮
                }, function () {
                    $.ajax({
                        type: "POST",
                        url: ctx + '/role/delete/' + data.id,
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
            case 'menu':
                layer.open({
                    type: 2,
                    area: ['800px', '500px'],
                    fix: false, // 不固定
                    maxmin: false,
                    shade: 0.4,
                    title: '配置菜单',
                    content: ctx + '/role/menu/' + data.id + '/update/',
                    end: function () {
                        table.reload('tableList');
                    }
                });
                break;
            case 'rights':
                layer.open({
                    type: 2,
                    area: ['800px', '500px'],
                    fix: false, // 不固定
                    maxmin: false,
                    shade: 0.4,
                    title: '配置权限',
                    content: ctx + '/role/rights/' + data.id + '/update',
                    end: function () {
                        table.reload('tableList');
                    }
                });
                break;
        }
    });
    var $ = layui.$, active = {
        add: function () {
            layer.open({
                type: 2,
                area: ['800px', '500px'],
                fix: false, // 不固定
                maxmin: false,
                shade: 0.4,
                title: '角色添加',
                content: ctx + '/redirect?page=/role/roleAdd',
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
