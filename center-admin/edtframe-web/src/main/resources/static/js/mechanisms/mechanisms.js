var treeObj;
var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "parentId",
            rootPId: 0
        },
        key: {
            name: "name"
        }
    },
    callback: {
        onClick: mechanismsTreeOnClick
    }
};
layui.use(['table', 'layer'], function () {
    var table = layui.table, layer = layui.layer, $ = layui.$;
    table.render({
        id: 'tableList',
        elem: '#tableList',
        method: 'post',
        url: ctx + '/mechanisms/condition',
        cellMinWidth: 150,
        limit: pageLength,
        limits: [pageLength],
        cols: [[
            {field: '', title: '序号', unresize: true, type: 'numbers'},
            {field: 'name', title: '机构名称', unresize: true},
            {field: 'parentName', title: '上级机构', unresize: true, toolbar: '#parentName'},
            {field: 'code', title: '编码', unresize: true},
            {field: 'linkName', title: '联系人', unresize: true},
            {field: 'linkPhone', title: '联系电话', unresize: true},
            {field: 'addUserName', title: '添加人', unresize: true},
            {
                field: 'addTime',
                title: '添加时间',
                unresize: true, width: 160
                // templet: '<div><span title="{{d.addTime}}">{{d.addTime}}</span></div>'
            },
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
                    fix: false,
                    maxmin: false,
                    shade: 0.4,
                    title: '机构修改',
                    content: ctx + '/mechanisms/toUpdate/' + data.id,
                    end: function () {
                        table.reload('tableList');
                        createTree();
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
                        url: ctx + '/mechanisms/delete/' + data.id,
                        cache: false,
                        async: true,
                        success: function (result) {
                            var index = layer.alert(result.status.message, function () {
                                layer.close(index);
                                table.reload('tableList');
                                createTree();
                            });
                        }
                    });
                });
                break;
        }
    });
    var active = {
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
                title: '添加机构',
                content: ctx + '/redirect?page=/mechanisms/mechanismsAdd',
                end: function () {
                    table.reload('tableList');
                    createTree();
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
    createTree();
    $("#expandAll").click(function () {
        treeObj.expandAll(true);
        $(this).hide();
        $("#notExpandAll").show();
        return false;
    });
    $("#notExpandAll").click(function () {
        treeObj.expandAll(false);
        $(this).hide();
        $("#expandAll").show();
        return false;
    });
    $("#refresh").click(function () {
        createTree();
    });
    var cusTopHeight = $(window).height();
    $("#leftHeight").css({height: (cusTopHeight - 20) + "px"});
    $("#downPs").click(function () {
        $('.cus-pel').toggleClass('cus-pel2');
        $('.cus-pous').toggle();
        $(this).toggleClass('layui-icon-right');
        $(this).toggleClass('layui-icon-left');
        $('.cus-prs').toggleClass('layui-col-md3');
        $('.cus-right').removeClass('layui-col-md9').addClass('layui-col-md12');
        $('.cus-down').toggleClass('cus-down2');
        $('.cus-right .layui-table-view .layui-table').css({width: "100%"});
    })
});

function createTree() {
    $.ajax({
        url: ctx + '/mechanisms/user/tree',
        type: 'POST',
        success: function (data) {
            jQuery.fn.zTree.init(jQuery("#mechanismsTree"), setting, data);
            treeObj = jQuery.fn.zTree.getZTreeObj("mechanismsTree");
            treeObj.expandAll(false);
        },
        error: function (msg) {
            layer.alert(msg);
        }
    });
    $("#expandAll").show();
    $("#notExpandAll").hide();
}

function mechanismsTreeOnClick(event, treeId, treeNode) {
    table.reload('tableList', {
        page: {
            curr: 1
        },
        where: {
            organizationId: treeNode.id
        }
    });
}

