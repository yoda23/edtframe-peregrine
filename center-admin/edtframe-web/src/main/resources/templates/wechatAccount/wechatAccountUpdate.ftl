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
            <input type="hidden" name="id" id="id" lay-verify="required"
                   autocomplete="off" placeholder="" class="layui-input" readonly="readonly"
                   value="${weChatAccount.id}"/>
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
                            <input type="text" name="mechanismsName" id="mechanismsName" lay-verify="required"
                                   autocomplete="off" placeholder="所属机构" class="layui-input" readonly="readonly"
                                   value="${weChatAccount.mechanismsName}">
                            <input type="hidden" id="mechanismsId" name="mechanismsId"
                                   value="${weChatAccount.mechanismsId}"/>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label"><span
                                class="c-red">*</span>原始ID：</label>
                        <div class="layui-input-block">
                            <input type="text" name="originalId" id="originalId" lay-verify="required"
                                   autocomplete="off" placeholder="原始ID" class="layui-input"
                                   value="${weChatAccount.originalId}">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label"><span
                                class="c-red">*</span>类型：</label>
                        <div class="layui-input-block">
                            <label>
                                <select xm-select="type" xm-select-radio="" lay-verify="required" name="type"
                                        id="type">
                                </select>
                            </label>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label"><span
                                class="c-red">*</span>appId：</label>
                        <div class="layui-input-block">
                            <input type="text" name="appId" id="appId" lay-verify="required"
                                   autocomplete="off" placeholder="appId" class="layui-input"
                                   value="${weChatAccount.appId}">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label"><span
                                class="c-red">*</span>appSecret：</label>
                        <div class="layui-input-block">
                            <input type="text" name="appSecret" id="appSecret" lay-verify="required" autocomplete="off"
                                   placeholder="appSecret" class="layui-input" value="${weChatAccount.appSecret}">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label"><span
                                class="c-red">*</span>商户ID：</label>
                        <div class="layui-input-block">
                            <input type="text" name="shopId" id="shopId" lay-verify="" autocomplete="off"
                                   placeholder="商户ID" class="layui-input" value="${weChatAccount.shopId}">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label"><span
                                class="c-red">*</span>商户KEY：</label>
                        <div class="layui-input-block">
                            <input type="text" name="shopKey" id="shopKey" lay-verify="" autocomplete="off"
                                   placeholder="商户KEY" class="layui-input" value="${weChatAccount.shopKey}">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label"><span
                                class="c-red">*</span>token：</label>
                        <div class="layui-input-block">
                            <input type="text" name="token" id="token" lay-verify="required" autocomplete="off"
                                   placeholder="token" class="layui-input" value="${weChatAccount.token}">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label"><span
                                class="c-red">*</span>token标识：</label>
                        <div class="layui-input-block">
                            <input type="text" name="tokenFlag" id="tokenFlag" lay-verify="required" autocomplete="off"
                                   placeholder="token标识" class="layui-input" value="${weChatAccount.tokenFlag}">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label"><span
                                class="c-red">*</span>名称：</label>
                        <div class="layui-input-block">
                            <input type="text" name="name" id="name" lay-verify="required" autocomplete="off"
                                   placeholder="名称" class="layui-input" value="${weChatAccount.name}">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label"><span
                                class="c-red">*</span>logo：</label>
                        <div class="layui-input-block" style="position: relative">
                            <input type="text" name="picPath" id="picPath" lay-verify="" autocomplete="off"
                                   placeholder="logo" class="layui-input" readonly="readonly"
                                   value="${weChatAccount.picPath}">
                            <button type="button" class="layui-btn cus-btn-file" id="picPathUploadBtn">
                                <i class="layui-icon">&#xe67c;</i>上传
                            </button>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label"><span
                                class="c-red">*</span>证书：</label>
                        <div class="layui-input-block" style="position: relative">
                            <input type="text" name="certificatePath" id="certificatePath" lay-verify=""
                                   autocomplete="off" placeholder="证书" class="layui-input" readonly="readonly"
                                   value="${weChatAccount.certificatePath}">
                            <button type="button" class="layui-btn cus-btn-file" id="certificatePathBtn">
                                <i class="layui-icon">&#xe67c;</i>上传
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div style="height: 60px"></div>
            <div class="layui-layer-btn layui-layer-btn- cus-psike">
                <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="save" id="save">确 定</button>
                <button class="layui-btn layui-btn-danger" id="closeWindow">取 消</button>
            </div>
        </form>
    </div>

</div>
<script type="text/javascript" src="${ctx}/static/js/wechatAccount/wechatAccount.update.js"></script>
<script type="text/javascript">
    layui.use(['form', 'layer', 'formSelects', 'upload'], function () {
        var layer = layui.layer, formSelects = layui.formSelects, form = layui.form, upload = layui.upload;
        formSelects.data('type', 'local', {
            arr: [
                {"name": "公众号", "value": 1},
                {"name": "小程序", "value": 2},
            ]
        });
        formSelects.value('type', [${weChatAccount.type}]);
    });
</script>
</body>
</html>
