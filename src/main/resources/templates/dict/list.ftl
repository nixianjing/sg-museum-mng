<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>数据字典管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/style/admin.css" media="all">
    <script src="/moment.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>

<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">字典类型</label>
                    <div class="layui-input-block">
                        <select name="dictTypeCode">
                            <#if typeList??>
                                <option value="">请选择分类</option>
                                <#list typeList as item>
                                    <option value="${item.code}">${item.name}</option>
                                </#list>
                            </#if>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">名称</label>
                    <div class="layui-input-inline">
                        <input type="text" name="dictName" placeholder="请输入" autocomplete="off" class="layui-input">
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
            <!-- 操作按钮开始 -->
            <@permission token="1003,1003002">
                <div style="padding-bottom: 10px;">
                    <@permission token="1003002,1003002001">
                        <button class="layui-btn layuiadmin-btn-list" data-type="add">添加</button>
                    </@permission>
                </div>
            </@permission>
            <!-- 操作按钮结束 -->

            <!-- 数据列表开始 -->
            <table id="LAY-app-content-list" lay-filter="LAY-app-content-list"></table>
            <script id="createTime" type="text/html">
                {{#
                var date = new Date();
                date.setTime(d.createTime);
                return moment(date).format("YYYY-MM-DD h:mm:ss");
                }}
            </script>
            <script type="text/html" id="table-content-list">
                <@permission token="1003003,1003003001">
                    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i
                                class="layui-icon layui-icon-edit"></i>编辑</a>
                </@permission>
            </script>
            <!-- 数据列表结束 -->
        </div>
    </div>
</div>

<script src="/layui/layui.js"></script>
<script>
    layui.config({
        base: '/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'adminDictList', 'table'], function () {
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

        var $ = layui.$, active = {
            add: function () {
                layer.open({
                    type: 2
                    , title: '新增字典'
                    , content: 'add'
                    , maxmin: false
                    , area: ['550px', '600px']
                });
            }
        };

        $('.layui-btn.layuiadmin-btn-list').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });
</script>
</body>
</html>
