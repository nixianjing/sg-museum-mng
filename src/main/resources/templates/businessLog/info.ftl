<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>详情</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/style/admin.css" media="all">
    <link rel="stylesheet" href="/style/template.css" media="all">
</head>
<body>
<div lay-filter="layuiadmin-form-admin" id="layuiadmin-form-admin" style="padding: 20px 30px 0 0;">
    <form class="layui-form" action="" lay-filter="component-form-group">
        <div class="layui-form-item">
            <label class="layui-form-label">业务名称</label>
            <div class="layui-input-block" style="width: 84.5%;">
                <input type="text" name="title" autocomplete="off" value="${businessDTO.businessTypeName!''}"
                       disabled="disabled" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">变更类型</label>
            <div class="layui-input-block" style="width: 84.5%;">
                <input type="text" name="title" autocomplete="off" value="${businessDTO.updateTypeName!''}"
                       disabled="disabled" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">变更描述</label>
            <div class="layui-input-block" style="width: 84.5%;">
                <input type="text" name="title" autocomplete="off" value="${businessDTO.updateTypeMessage!''}"
                       disabled="disabled" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">变更前内容</label>
            <div class="layui-input-block" style="width: 85%;">
                <textarea class="layui-textarea">${businessDTO.oldContentJson!''}</textarea>
            </div>
        </div>

        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">变更后内容</label>
            <div class="layui-input-block" style="width: 85%;">
                <textarea class="layui-textarea">${businessDTO.newContentJson!''}</textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">变更操作人</label>
            <div class="layui-input-block" style="width: 84.5%;">
                <input type="text" name="title" autocomplete="off" value="${businessDTO.updateUserName!''}"
                       disabled="disabled" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">变更时间</label>
            <div class="layui-input-block" style="width: 84.5%;">
                <input type="text" name="title" autocomplete="off" value="${businessDTO.updateTimeStr!''}"
                       disabled="disabled" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button type="reset" class="layui-btn layui-btn-normal">关闭</button>
            </div>
        </div>
    </form>
</div>
<script src="/layui/layui.js"></script>
<script>
    layui.config({
        base: '/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'tree', 'form', 'layedit'], function () {
        var $ = layui.$
            , layer = layui.layer
            , layedit = layui.layedit
            , form = layui.form;

        //监听关闭按钮
        $('.layui-btn-normal').on('click', function () {
            window.parent.location.reload();
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });
    })
</script>
</body>
</html>