layui.use(['table', 'layer'], function () {
    var table = layui.table, layer = layui.layer;
    table.render({
        id: 'tableList',
        elem: '#tableList',
        method: 'post',
        url: ctx + '/wechat/account/condition',
        cellMinWidth: 150,
        limit: pageLength,
        limits: [pageLength],
        cols: [[
            {field: '', title: '序号', unresize: true, type: 'numbers'},
            {field: 'originalId', title: '原始ID', unresize: true},
            {field: 'mechanismsName', title: '机构名称', unresize: true},
            {field: 'appId', title: 'appid', unresize: true},
            // {field: 'appSecret', title: 'appsecret', unresize: true},
            // {field: 'shopId', title: '商户ID', unresize: true},
            // {field: 'shopKey', title: '商户KEY', unresize: true},
            {field: 'token', title: 'token', unresize: true},
            {field: 'tokenFlag', title: 'token标识', unresize: true},
            {field: 'name', title: '名称', unresize: true},
            {field: '', title: '类型', toolbar: '#type', unresize: true},
            {field: 'addUserName', title: '添加人', unresize: true},
            {field: 'addTime', title: '添加时间', unresize: true},
            {field: '', title: '操作', toolbar: '#toolbar', unresize: true, width: 200},
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
                    fix: false, // 不固定
                    maxmin: false,
                    shade: 0.4,
                    title: '修改用户',
                    content: ctx + '/wechat/account/toUpdate/' + data.id,
                    end: function () {
                        table.reload('tableList');
                    }
                });
                break;
            case 'del':
                layer.confirm("确定要删除此条信息么", {
                    btn: ['确定', '取消']
                }, function () {
                    $.ajax({
                        type: "POST",
                        url: ctx + '/wechat/account/delete/' + data.id,
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
                title: '添加公众号信息',
                content: ctx + '/redirect?page=wechatAccount/wechatAccountAdd',
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
