<#include "../common.ftl">
<!DOCTYPE HTML>
<html>
<head>
    <#include "../common.ftl">
    <title></title>
</head>
<body>
<nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;
		</span>系统管理<span class="c-gray en">&gt;</span>日志管理<a
        class="btn btn-success radius r"
        style="line-height:1.6em;
	margin-top:3px"
        href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i> </a>
</nav>
<div class="page-container">
    <div class="mt-20">
        <table id="tableList"
               class="table table-border table-striped table-bordered table-bg table-hover table-sort">
            <thead>
            <tr class="text-c">
                <th width="20">序号</th>
                <th width="80">操作名称</th>
                <th width="150">操作类型</th>
                <th width="100">操作内容</th>
                <th width="40">操作人</th>
                <th width="120">操作时间</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        initTable();
    });

    function initTable() {
        table = $('#tableList')
                .dataTable(
                        {
                            "searching": false,
                            "lengthChange": false,
                            "processing": true,
                            "ordering": false,
                            "serverSide": true,
                            "pageLength": ${pageLength},
                            "pagingType": "full_numbers",
                            "ajax": {
                                "type": "post",
                                "url": ctx + "/systemLog/listSystemLogByCondition"
                            },
                            "columns": [{
                                "data": null,
                                "targets": 0
                            }, {
                                "data": "operationName"
                            }, {
                                "data": "operationType"
                            }, {
                                "data": "content"
                            }, {
                                "data": "userId"
                            }, {
                                "data": "addTime"
                            }],
                            "fnDrawCallback": function () {
                                var api = this.api();
                                var startIndex = api.context[0]._iDisplayStart;// 获取到本页开始的条数
                                api.column(0).nodes().each(
                                        function (cell, i) {
                                            cell.innerHTML = startIndex + i
                                                    + 1;
                                        });
                            }
                        });
    }
</script>
</body>
</html>
