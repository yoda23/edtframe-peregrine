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
		</span>系统管理<span class="c-gray en">&gt;</span>用户管理<a
        class="btn btn-success radius r"
        style="line-height:1.6em;
	margin-top:3px"
        href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i> </a>
</nav>
<div class="page-container">
    <div class="cus_hei_30">
			<span class="f-r"> <input type="text"
                                      name="name" id="name" placeholder="用户姓名"
                                      class="input-text cus_wid_250 radius">
				<button name="" id="userSearch" class="btn btn-success radius"
                        type="button">
					<i class="Hui-iconfont">&#xe665;</i>&nbsp;查询
				</button>
			</span>
    </div>
    <div class="mt-20">
        <table id="tableList"
               class="table table-border table-bordered table-bg table-hover table-sort">
            <thead>
            <tr class="text-c">
                <th width="30">序号</th>
                <th width="">id</th>
                <th width="40">账号</th>
                <th width="40">姓名</th>
                <th width="80">地市名称</th>
                <th width="80">角色名称</th>
                <th width="50">用户状态</th>
                <th width="40">添加人</th>
                <th width="100">添加时间</th>
                <th width="80">操作</th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<script type="text/javascript" src="${ctx}/js/user/user.dustbin.js"></script>
<script type="text/javascript">
    function initUserTable() {
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
                                "url": ctx + "/user/getUserByConditon",
                                "data": {
                                    deleteFlag: 1
                                }

                            },
                            "columns": [{
                                "data": null,
                                "targets": 0
                            }, {
                                "data": "id",
                                "visible": false
                            }, {
                                "data": "loginId"
                            }, {
                                "data": "name"
                            }, {
                                "data": "mechanismsName"
                            }, {
                                "data": "roleName"
                            }, {
                                "data": "activeName"
                            }, {
                                "data": "addUser"
                            }, {
                                "data": "addTime"
                            }],
                            "createdRow": function (row, data, index) {
                                if (data.activeName == "启用") {
                                    $('td', row).eq(5).html('<span class=\"label label-primary radius\">' + data.activeName + '</span>');
                                } else {
                                    $('td', row).eq(5).html('<span class=\"label label-danger radius\">' + data.activeName + '</span>');
                                }

                            },
                            "columnDefs": [{
                                "targets": [9],
                                "data": "id",
                                "render": function (data, type, full) {
                                    return "<shiro:hasPermission name='106'><a href='javascript:void(0)'onClick=findUser('"
                                            + data
                                            + "')>找回用户</a></shiro:hasPermission>";

                                }
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