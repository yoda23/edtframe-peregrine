layui.use(['table', 'layer'], function () {
    var table = layui.table, layer = layui.layer;
    table.render({
        id: 'tableList',
        elem: '#tableList',
        method: 'post',
        url: ctx + '/sensitive-words/condition',
        cellMinWidth: 150,
        limit: pageLength,
        limits: [pageLength],
        cols: [[
            {field: '', title: '序号', unresize: true, type: 'numbers'},
            {field: 'content', title: '内容', sort: true, unresize: true},
            {field: 'addTime', title: '添加时间', unresize: true},
            {field: 'addUserName', title: '添加人', unresize: true},
            {field: '', title: '操作', toolbar: '#toolbar', unresize: true}
        ]],
        page: true
    });
    table.on('tool(tableList)', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        var index;
        switch (layEvent) {
            case 'update':
                index = layer.open({
                    type: 2,
                    area: ['800px', '200px'],
                    fix: false, // 不固定
                    maxmin: false,
                    shade: 0.4,
                    title: "敏感词修改",
                    content: ctx + '/sensitive-words/toUpdate/' + data.id,
                    end: function () {
                        table.reload('tableList');
                    }
                });
                break;
            case 'del':
                layer.confirm('确定要删除此条数据么？', {
                    btn: ['确定', '取消']
                }, function () {
                    $.ajax({
                        type: "POST",
                        url: ctx + '/sensitive-words/delete/' + data.id,
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
        // if (layEvent === 'update') {
        //
        //     layer.full(index);
        // } else if (layEvent === 'del') {
        //
        // }
        // layer.full(index);
    });
    var $ = layui.$, active = {
        search: function () {
            table.reload('tableList', {
                page: {
                    curr: 1
                },
                where: {
                    content: $("#content").val()
                }
            });
        },
        add: function () {
            var index = layer.open({
                type: 2,
                area: ['800px', '200px'],
                fix: false, // 不固定
                maxmin: false,
                shade: 0.4,
                title: '敏感词添加',
                content: ctx + '/redirect?page=sensitiveWords/sensitiveWordsAdd',
                end: function () {
                    table.reload('tableList');
                }
            });
            // layer.full(index);
        },
        import: function () {
            var index = layer.open({
                type: 2,
                area: ['800px', '200px'],
                fix: false,
                maxmin: false,
                shade: 0.4,
                title: '导入敏感词',
                content: ctx + '/redirect?page=sensitiveWords/importSensitiveWords',
                end: function () {
                    table.reload('tableList');
                }
            });
            // layer.full(index);
        }
    };
    $('.layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
        var othis = $(this), method = othis.data('method');
        active[method] ? active[method].call(this, othis) : '';
    });
});