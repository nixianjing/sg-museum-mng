<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>编辑</title>
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
                <input type="text" name="roleName" value="${adminRole.roleName}" placeholder="请输入用户名" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-inline">
                <input type="radio" name="status" value="0" title="有效" <#if adminRole.status == 0> checked</#if>>
                <input type="radio" name="status" value="1" title="无效" <#if adminRole.status == 1> checked</#if>>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-inline">
                <textarea name="remark" placeholder="" class="layui-textarea">${adminRole.remark!}</textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">权限</label>
            <div class="layui-input-inline" id="permissionId"></div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <input type="hidden" name="id" id="id" value="${adminRole.id}">
                <input type="hidden" name="roleCode" id="roleCode" value="${adminRole.roleCode}">
                <input type="hidden" name="permissionJson" id="permissionJson" value="">
                <button id="dataSave" class="layui-btn" lay-submit lay-filter="component-form-element">保存</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                <button type="reset" class="layui-btn layui-btn-normal">关闭</button>
            </div>
        </div>
    </form>
</div>
<script src="/jquery-1.11.1.min.js"></script>
<script src="/layui/layui.js"></script>
<script>
    layui.config({
        base: '/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'tree', 'form', 'layedit'], function () {
        var $ = layui.$
            , tree = layui.tree
            , layer = layui.layer
            , layedit = layui.layedit
            , form = layui.form;

        // 获取数据权限
        getPermissionData();
        var permissionData;

        function getPermissionData() {
            var roleCode = $("#roleCode").val();
            $.getJSON(layui.setter.base + "adminRole/getRolePermission", {'roleCode': roleCode}, function (rs) {
                permissionData = rs.data;
                tree.render({
                    elem: '#permissionId'
                    , data: permissionData
                    , id: 'permissionDemoId'
                    , click: function (obj) {
                        layer.msg(JSON.stringify(obj.data));
                        console.log(obj);
                    }
                    , oncheck: function (obj) {
                        console.log(obj);
                    }
                    , showCheckbox: true  //是否显示复选框
                    , accordion: 0  //是否开启手风琴模式
                    , onlyIconControl: true //是否仅允许节点左侧图标控制展开收缩
                    , isJump: 1  //点击文案跳转地址
                    , edit: false  //操作节点图标
                });
            }, "json");
        };

        /* 自定义验证规则 */
        form.verify({
            title: function (value) {
                if (value.length < 5) {
                    return '标题至少得5个字符啊';
                }
            }
            , pass: [/(.+){6,12}$/, '密码必须6到12位']
            , content: function (value) {
                layedit.sync(editIndex);
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
            $.post("update", field, function (rs) {
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