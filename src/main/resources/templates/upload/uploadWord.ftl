<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>新增文物信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/layui/css/layui.css" media="all">
    <style>
        #permissionId {
            margin-bottom: 100px;
            width: 200px;
        }
    </style>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
    <legend>说明：上传文物文件(文物重复不能上传,上传文件必须是word文档)</legend>
</fieldset>
<div class="layui-form" lay-filter="layuiadmin-form-admin" id="layuiadmin-form-admin" style="padding: 20px 30px 0 0;">
    <form class="layui-form" action="" lay-filter="component-form-group">
        <div class="layui-form-item">
            <label class="layui-form-label">文件路径</label>
            <div class="layui-input-inline">
                <button type="button" class="layui-btn" id="upload">
                    <i class="layui-icon">&#xe67c;</i>选择文件
                </button>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">项目名称</label>
            <div class="layui-input-inline">
                <input type="text" name="projectName" id="projectName" lay-verify="required" placeholder="请输入用户名"
                       autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">项目时间</label>
            <div class="layui-input-inline">
                <input type="text" name="projectTime" id="projectTime" lay-verify="required" placeholder="请输入用户名"
                       autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button type="button" class="layui-btn" id="dataSave">保存</button>
                <button type="reset" class="layui-btn layui-btn-normal">关闭</button>
            </div>
        </div>
    </form>
</div>
</body>
<script src="/jquery/jquery.min.js"></script>
<script src="/layui/layui.js"></script>
<script>
    layui.config({
        base: '/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'upload'], function () {
        var $ = layui.$
            , layer = layui.layer
            , upload = layui.upload;
        var loading;

        //选完文件后不自动上传
        upload.render({
            elem: '#upload'
            , url: '/uploadFile/uploadRelicWord'
            , method: 'POST'
            , accept: 'file'
            , auto: false
            , size: 0
            , bindAction: '#dataSave'
            , data: {
                projectName: function () {
                    loading = layer.msg('正在上传请等待...', {icon: 16, shade: 0.3, time:0});
                    var projectName = $('#projectName').val();
                    return projectName;
                },
                projectTime: function () {
                    var projectTime = $('#projectTime').val();
                    return projectTime;
                }
            }
            , done: function (res) {
                layer.close(loading);
                if (res.success) {

                    layer.msg('上传成功', {
                        icon: 6,
                        time: 2000
                    }, function () {
                        window.parent.location.reload();
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    });
                } else {
                    layer.msg(res.message, {icon: 5, time: 2000});
                }
            }
            , error: function () {
                layer.close(loading);
                layer.msg('网络异常，请稍后重试！');
            }
        });


        //监听关闭按钮
        $('.layui-btn-normal').on('click', function () {
            window.parent.location.reload();
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });
    })
</script>
</html>