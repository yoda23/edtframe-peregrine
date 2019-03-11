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
            <input type="hidden" class="layui-input" value="${mechanisms.id!}"
                   placeholder="" id="id" name="id">
            <div class="cus-disflex">
                <div style="width: 260px;position: relative;">
                    <div>&nbsp;</div>
                    <div class="layui-card cus_tree_scroll cus-pous">
                        <div class="layui-card-header cus-ulis-left">组织机构
                            <div class="cus-ruslid">
                                <div class="layui-btn-container">
                                    <div class="layui-btn-group">
                                        <button id="refresh" class="layui-btn layui-btn-primary layui-btn-sm"><i
                                                class="layui-icon ilu layui-icon-refresh-3"></i></button>
                                        <button id="expandAll" class="layui-btn layui-btn-primary layui-btn-sm"><i
                                                class="layui-icon ilu layui-icon-down"></i></button>
                                        <button style="display: none" id="notExpandAll"
                                                class="layui-btn layui-btn-primary layui-btn-sm"><i
                                                class="layui-icon ilu layui-icon-up"></i></button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <ul id="mechanismsTree" class="ztree"></ul>
                    </div>
                </div>
                <div class="cus-right">
                    <div class="layui-form-item">
                        <label class="layui-form-label"><span
                                class="c-red">*</span>所属机构：</label>
                        <div class="layui-input-block">
                            <input type="text" name="parentName" id="parentName" lay-verify="required"
                                   autocomplete="off" placeholder="所属机构" class="layui-input" readonly="readonly"
                                   value="${mechanisms.parentName!}">
                            <input type="hidden" id="parentId" name="parentId" value="${mechanisms.parentId!}"/>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label"><span
                                class="c-red">*</span>机构名称：</label>
                        <div class="layui-input-block">
                            <input type="text" name="name" id="name" lay-verify="required"
                                   autocomplete="off" placeholder="机构名称" class="layui-input"
                                   value="${mechanisms.name!}">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label"><span
                                class="c-red">*</span>机构编码：</label>
                        <div class="layui-input-block">
                            <input type="text" name="code" id="code" lay-verify="required"
                                   autocomplete="off" placeholder="机构编码" class="layui-input"
                                   value="${mechanisms.code!}">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label"><span
                                class="c-red">*</span>联系人：</label>
                        <div class="layui-input-block">
                            <input type="text" name="linkName" id="linkName" lay-verify="" autocomplete="off"
                                   placeholder="联系人" class="layui-input" value="${mechanisms.linkName!}">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label"><span
                                class="c-red">*</span>联系电话：</label>
                        <div class="layui-input-block">
                            <input type="text" name="linkPhone" id="linkPhone" lay-verify="" autocomplete="off"
                                   placeholder="联系电话" class="layui-input" value="${mechanisms.linkPhone!}">
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-layer-btn layui-layer-btn- cus-psike">
                <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="save">确 定</button>
                <button class="layui-btn layui-btn-danger" id="closeWindow">取 消</button>
            </div>
        </form>
    </div>

</div>
<script type="text/javascript" src="${ctx}/static/js/mechanisms/mechanisms.update.js"></script>
</body>
</html>
