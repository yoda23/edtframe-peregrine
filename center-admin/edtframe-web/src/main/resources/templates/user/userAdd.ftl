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
            <input type="hidden" id="userMechanismsRights" name="userMechanismsRights">
            <div class="cus-disflex">
                <div style="width: 260px;position: relative;">
                    <div>&nbsp;</div>
                    <div class="layui-card cus_tree_scroll cus-pous">
                    <#--<button class="layui-btn layui-btn-xs" id="selectAll">全选</button>-->
                    <#--<button class="layui-btn layui-btn-xs" id="notSelectAll">取消</button>-->
                    <#--<button class="layui-btn layui-btn-xs" id="expandAll">展开</button>-->
                    <#--<button class="layui-btn layui-btn-xs" id="notExpandAll">收回</button>-->
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
                        <ul id="userTree" class="ztree"></ul>
                    </div>
                </div>
                <div class="cus-right">
                    <div class="layui-form-item">
                        <label class="layui-form-label"><span
                                class="c-red">*</span>登录账号：</label>
                        <div class="layui-input-block">
                            <input type="text" name="loginId" id="loginId" lay-verify="required" autocomplete="off"
                                   placeholder="登录账号" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label"><span
                                class="c-red">*</span>初始密码：</label>
                        <div class="layui-input-block">
                            <input type="password" name="loginPassword" id="userPassword" lay-verify="pass"
                                   autocomplete="off"
                                   placeholder="初始密码" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label"><span
                                class="c-red">*</span>确认密码：</label>
                        <div class="layui-input-block">
                            <input type="password" name="userAddPassword2" id="userPassword2" lay-verify="repass"
                                   autocomplete="off"
                                   placeholder="确认密码" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label"><span
                                class="c-red">*</span>姓名：</label>
                        <div class="layui-input-block">
                            <input type="text" name="name" id="name" lay-verify="required" autocomplete="off"
                                   placeholder="姓名" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label"><span
                                class="c-red">*</span>角色：</label>
                        <div class="layui-input-block">
                            <label>
                                <select xm-select="roleId" xm-select-radio="" lay-verify="required" name="roleId"
                                        id="roleId">
                                </select>
                            </label>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label"><span
                                class="c-red">*</span>所属地市：</label>
                        <div class="layui-input-block">
                            <input type="text" name="parentName" id="parentName" lay-verify="required"
                                   autocomplete="off" placeholder="所属地市" class="layui-input" readonly="readonly">
                            <input type="hidden" name="mechanismsId" id="mechanismsId" lay-verify="required"
                                   autocomplete="off" placeholder="" class="layui-input" readonly="readonly">
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-layer-btn layui-layer-btn- cus-psike">
                <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="save">确 定</button>
                <button class="layui-btn layui-btn-danger " id="closeWindow">取 消</button>
            </div>
        </form>
    </div>

</div>
<script type="text/javascript" src="${ctx}/static/js/user/user.add.js"></script>
</body>
</html>
