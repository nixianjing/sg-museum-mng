<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>修复记录</title>
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
                    <label class="layui-form-label" style="width: 80px;">项目名称</label>
                    <div class="layui-input-inline" style="width: 200px;">
                        <input type="text" name="projectName" placeholder="项目名称" autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label" style="width: 80px;">项目时间</label>
                    <div class="layui-input-inline" style="width: 200px;">
                        <input type="text" name="projectTime" placeholder="项目时间" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label" style="width: 80px;">方案设计</label>
                    <div class="layui-input-inline" style="width: 200px;">
                        <input type="text" name="designCompany" placeholder="方案设计单位" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label" style="width: 80px;">保护修复</label>
                    <div class="layui-input-inline" style="width: 200px;">
                        <input type="text" name="repairCompany" placeholder="保护修复单位" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <button class="layui-btn layuiadmin-btn-list" lay-submit lay-filter="LAY-app-contlist-search">
                        <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
                    </button>
                </div>
            </div>
        </div>

        <div class="layui-card-body">
            <div style="padding-bottom: 10px;">
                <button class="layui-btn layuiadmin-btn-list" data-type="addScanningWord">
                    <i class="layui-icon layui-icon-upload"></i>上传修复记录
                </button>
            </div>
            <input type="hidden" name="relicId" id="relicId" value="${relicId}">
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
                <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit">
                    <i class="layui-icon layui-icon-upload"></i>上传图片
                </a>
                <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="openWord">
                    <i class="layui-icon layui-icon-read"></i>编辑Word
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
    }).use(['index', 'relicScanningList', 'table'], function () {
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
            addScanningWord: function () {
                var relicId = $("#relicId").val();
                layer.open({
                    type: 2
                    , title: '上传WORD文件'
                    , content: 'openScanningUploadWord?relicId=' + relicId
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
