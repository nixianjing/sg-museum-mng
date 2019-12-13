<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>报损列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/style/admin.css" media="all">
    <script type="text/javascript" src="/jquery.min.js"></script>
    <script type="text/javascript" src="/pageoffice.js" id="po_js_main"></script>
</head>
<body>

<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label" style="width: 80px;">开始时间</label>
                    <div class="layui-input-inline" style="width: 200px;">
                        <input type="text" name="relicName" placeholder="文物名称" autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label" style="width: 80px;">结束时间</label>
                    <div class="layui-input-inline" style="width: 200px;">
                        <input type="text" name="relicNo" placeholder="文物编号" autocomplete="off" class="layui-input">
                    </div>
                </div>

                <div class="layui-inline" style="margin-right: -10px;">
                    <button class="layui-btn layuiadmin-btn-list" lay-submit lay-filter="LAY-app-contlist-search">
                        <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
                    </button>
                </div>
            </div>
        </div>

        <div class="layui-card-body">
            <div style="padding-bottom: 10px;">
                <button class="layui-btn layuiadmin-btn-list" data-type="addWord">
                    <i class="layui-icon layui-icon-upload"></i>导出EXCEL
                </button>
            </div>
            <table id="LAY-app-content-list" lay-filter="LAY-app-content-list"></table>
            <script src="/moment.js" type="text/javascript" charset="utf-8"></script>
            <script id="createTime" type="text/html">
                {{#
                var date = new Date();
                date.setTime(d.createTime);
                return moment(date).format("YYYY-MM-DD h:mm:ss");
                }}
            </script>
            <script type="text/html" id="table-content-list">
                <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="info">
                    <i class="layui-icon layui-icon-search"></i>详情
                </a>
                <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="setRestore">
                    <i class="layui-icon layui-icon-search"></i>修复中
                </a>
                <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="setRestoreComplete">
                    <i class="layui-icon layui-icon-search"></i>修复完成
                </a>
            </script>
        </div>
    </div>
</div>

<script src="/layui/layui.js"></script>
<script>
    layui.config({
        base: '/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'relicReportList', 'table'], function () {
        var table = layui.table
            , form = layui.form;
        var tableId = 'LAY-app-content-list'; // 列表数据ID

        //监听搜索
        form.on('submit(LAY-app-contlist-search)', function (data) {
            var field = data.field;
            //执行重载
            table.reload(tableId, {
                where: field
            });
        });

        //事件
        var $ = layui.$, active = {
            addZipWord: function () {
                layer.open({
                    type: 2
                    , title: '上传压缩包'
                    , content: 'openUploadZipWord'
                    , maxmin: false
                    , area: ['550px', '600px']
                });
            }
        };

        $('.layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
</script>
</body>
</html>
