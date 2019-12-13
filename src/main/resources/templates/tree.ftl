<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>树组件 - layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">

    <link rel="stylesheet" href="/layui/css/layui.css" media="all">

    <style>
        body {
            padding: 100px;
        }

        #test1, #test2 {
            margin-bottom: 100px;
            width: 400px;
        }
    </style>
</head>
<body>

<div class="layui-btn-container">
    <button type="button" class="layui-btn" lay-demo="getChecked">获取选中数据</button>
    <button type="button" class="layui-btn" lay-demo="setChecked">设置节点勾选</button>
    <button type="button" class="layui-btn" lay-demo="reload">重载实例</button>
</div>

<div class="layui-form">
    <div class="layui-form-item">
        <label class="layui-form-label">表单内</label>
        <div class="layui-input-block">

        </div>
    </div>

    <div id="test1"></div>

    <script src="/layui/layui.js"></script>
    <script>
        layui.config({
            base: '/' //静态资源所在路径
        }).extend({
            index: 'lib/index' //主入口模块
        }).use(['index', 'tree', 'layer', 'form'], function () {
            var $ = layui.$
                , tree = layui.tree
                , layer = layui.layer
                , util = layui.util
                , element = layui.element
                , index = 100;
            // 获取数据权限
            getPermissionData();
            //数据源
            var data1;
            // ajax获取数据
            function getPermissionData() {
                $.getJSON(layui.setter.base + "getPermission", function (rs) {
                    layer.alert(JSON.stringify(rs.data), {shade: 0});
                    data1 = rs.data;
                    tree.render({
                        elem: '#test1'
                        , data: data1
                        , id: 'demoId1'
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


            //按钮事件
            util.event('lay-demo', {
                getChecked: function (othis) {
                    var checkedData = tree.getChecked('demoId1');
                    layer.alert(JSON.stringify(checkedData), {shade: 0});
                    console.log(checkedData);
                }
                , setChecked: function () {
                    tree.setChecked('demoId1', [1000, 1001, 1002]);
                }
                , reload: function () {
                    tree.reload('demoId1', {});
                }
            });

        });
    </script>

</body>
</html>