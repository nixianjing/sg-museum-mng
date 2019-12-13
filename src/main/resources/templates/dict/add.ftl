<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>新增数据字典</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/layui/css/layui.css" media="all">
</head>
<body>

<div class="layui-form" lay-filter="layuiadmin-form-admin" id="layuiadmin-form-admin" style="padding: 20px 30px 0 0;">
    <form class="layui-form" action="" lay-filter="component-form-group">
        <div class="layui-form-item">
            <label class="layui-form-label">角色名称</label>
            <div class="layui-input-inline">
                <select name="dictTypeCode" >
                    <#if typeList??>
                        <option value="">请选择分类</option>
                        <#list typeList as item>
                            <option value="${item.code}">${item.name}</option>
                        </#list>
                    </#if>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">字典名称</label>
            <div class="layui-input-inline">
                <input type="text" name="dictName" lay-verify="required" placeholder="请输入字典名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">排序</label>
            <div class="layui-input-inline">
                <input type="text" name="sort" lay-verify="required" placeholder="请输入排序" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
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
    }).use(['index', 'form', 'layedit'], function () {
        var $ = layui.$
            , tree = layui.tree
            , layer = layui.layer
            , layedit = layui.layedit
            , form = layui.form;

        //监听关闭按钮
        $('.layui-btn-normal').on('click', function () {
            window.parent.location.reload();
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });

        /* 监听提交 */
        form.on('submit(component-form-element)', function (data) {
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