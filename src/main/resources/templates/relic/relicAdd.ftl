<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>新增管理员</title>
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

<div lay-filter="layuiadmin-form-admin" id="layuiadmin-form-admin" style="padding: 20px 30px 0 0;">
    <form class="layui-form" action="" lay-filter="component-form-group">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="username" lay-verify="required" lay-reqtext="用户名是必填项，岂能为空？" disabled="disabled" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">文物编号</label>
                <div class="layui-input-inline">
                    <input type="text" name="username" lay-verify="required" lay-reqtext="用户名是必填项，岂能为空？"
                           placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">年代</label>
                <div class="layui-input-inline">
                    <input type="text" name="username" lay-verify="required" lay-reqtext="用户名是必填项，岂能为空？"
                           placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">来源</label>
                <div class="layui-input-inline">
                    <input type="text" name="username" lay-verify="required" lay-reqtext="用户名是必填项，岂能为空？"
                           placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">等级</label>
                <div class="layui-input-inline">
                    <input type="text" name="username" lay-verify="required" lay-reqtext="用户名是必填项，岂能为空？"
                           placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">尺寸</label>
                <div class="layui-input-inline">
                    <input type="text" name="username" lay-verify="required" lay-reqtext="用户名是必填项，岂能为空？"
                           placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">种类</label>
                <div class="layui-input-inline">
                    <input type="text" name="username" lay-verify="required" lay-reqtext="用户名是必填项，岂能为空？"
                           placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">质地</label>
                <div class="layui-input-inline">
                    <input type="text" name="username" lay-verify="required" lay-reqtext="用户名是必填项，岂能为空？"
                           placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">收藏单位</label>
                <div class="layui-input-inline">
                    <input type="text" name="username" lay-verify="required" lay-reqtext="用户名是必填项，岂能为空？"
                           placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">收藏时间</label>
                <div class="layui-input-inline">
                    <input type="text" name="username" lay-verify="required" lay-reqtext="用户名是必填项，岂能为空？"
                           placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">制造工艺</label>
                <div class="layui-input-inline">
                    <input type="text" name="username" lay-verify="required" lay-reqtext="用户名是必填项，岂能为空？"
                           placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">织物组织</label>
                <div class="layui-input-inline">
                    <input type="text" name="username" lay-verify="required" lay-reqtext="用户名是必填项，岂能为空？"
                           placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item" style="text-align:center">
            <div class="layui-upload-list">
                <img class="layui-upload-img" id="demo1" style="width: 165px; height: 165px;top:0;left:0;z-index: 0;margin:0px;">
                <p id="demoText">文物LOG图</p>
            </div>
        </div>

        <#--<div class="layui-form-item layui-form-text">-->
        <#--<label class="layui-form-label">普通文本域</label>-->
        <#--<div class="layui-input-block">-->
        <#--<textarea placeholder="请输入内容" class="layui-textarea"></textarea>-->
        <#--</div>-->
        <#--</div>-->
        <#--<div class="layui-form-item">-->
        <#--<div class="layui-input-block">-->
        <#--<button type="submit" class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>-->
        <#--<button type="reset" class="layui-btn layui-btn-primary">重置</button>-->
        <#--</div>-->
        <#--</div>-->
        <div class="layui-form-item">
            <div class="layui-input-block">
                <input type="hidden" name="permissionJson" id="permissionJson" value="">
                <button id="dataSave" class="layui-btn" lay-submit lay-filter="component-form-element">保存</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
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
    }).use(['index', 'upload', 'form'], function () {
        var $ = layui.$
            , tree = layui.tree
            , layer = layui.layer
            , upload = layui.upload
            , form = layui.form;

        //普通图片上传
        var uploadInst = upload.render({
            elem: '#demo1'
            , url: '/uploadImage/uploadRelicImg'
            , before: function (obj) {
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    $('#demo1').attr('src', result); //图片链接（base64）
                });
            }
            , done: function (res) {
                //如果上传失败
                if (res.code > 0) {
                    return layer.msg('上传失败');
                }
                //上传成功
            }
            , error: function () {
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function () {
                    uploadInst.upload();
                });
            }
        });


        //监听关闭按钮
        $('.layui-btn-normal').on('click', function () {
            window.parent.location.reload();
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });

        /* 监听提交 */
        form.on('submit(component-form-element)', function (data) {
            // 获取选中的权限
            var checkedData = tree.getChecked('permissionDemoId');
            $("#permissionJson").val(JSON.stringify(checkedData));
            var field = $(data.form).serialize();
            $("#dataSave").attr("disabled", "true");
            $.post("save", field, function (rs) {
                if (rs.success) {
                    layer.msg('保存成功', {
                        icon: 6,
                        time: 2000
                    }, function () {
                        $("#dataSave").removeAttr("disabled");
                        window.parent.location.reload();
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    });
                } else {
                    $("#dataSave").removeAttr("disabled");
                    layer.msg(rs.msg, {icon: 6, time: 2000});
                }
            }, "json");
            return false;
        });
    })
</script>
</body>
</html>