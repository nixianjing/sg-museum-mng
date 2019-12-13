<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>管理员管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/style/admin.css" media="all">
</head>
<body>

<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">用户名</label>
                    <div class="layui-input-inline">
                        <input type="text" name="userCode" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">姓名</label>
                    <div class="layui-input-inline">
                        <input type="text" name="name" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">手机号码</label>
                    <div class="layui-input-inline">
                        <input type="text" name="mobile" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">状态</label>
                    <div class="layui-input-inline">
                        <select name="status">
                            <option value="">请选择</option>
                            <option value="0">启用</option>
                            <option value="1">禁用</option>
                        </select>
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
            <@permission token="1001,1001002,1001004,1001005">
                <div style="padding-bottom: 10px;">
                    <@permission token="1001005,1001005001">
                        <button class="layui-btn layui-btn-danger" data-type="batchdel">删除</button>
                    </@permission>
                    <@permission token="1001004,1001004001">
                        <button class="layui-btn layui-btn-warm" data-type="enable">启用</button>
                    </@permission>
                    <@permission token="1001004,1001004001">
                        <button class="layui-btn layui-bg-red" data-type="disable">禁用</button>
                    </@permission>
                    <@permission token="1001002,1001002001">
                        <button class="layui-btn layuiadmin-btn-list" data-type="add">添加</button>
                    </@permission>
                </div>
            </@permission>
            <table id="LAY-app-content-list" lay-filter="LAY-app-content-list"></table>
            <script src="/moment.js" type="text/javascript" charset="utf-8"></script>
            <script id="sex" type="text/html">
                {{#
                var sex = "";
                if(d.sex == 1) {
                sex = "男";
                } else if(d.sex == 2) {
                sex = "女";
                }
                return sex;
                }}
            </script>
            <script id="status" type="text/html">
                {{#
                var status = "";
                if(d.status == 0) {
                status = "开启";
                } else if(d.status == 1) {
                status = "关闭";
                }
                return status;
                }}
            </script>
            <script id="createTime" type="text/html">
                {{#
                var date = new Date();
                date.setTime(d.createTime);
                return moment(date).format("YYYY-MM-DD h:mm:ss");
                }}
            </script>
            <script type="text/html" id="table-content-list">
                <#--{{#  if(d.userCode != 'system' && d.userCode != 'admin'){ }}-->
                <@permission token="1001003,1001003001">
                    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit">
                        <i class="layui-icon layui-icon-edit"></i>编辑
                    </a>
                </@permission>
                <@permission token="1001006,1001006001">
                    <a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="initialize">初始密码</a>
                </@permission>
                <#--{{#  } }}-->
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
    }).use(['index', 'adminUserList', 'table'], function () {
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
            add: function () {
                layer.open({
                    type: 2
                    , title: '新增用户'
                    , content: 'add'
                    , maxmin: false
                    , area: ['550px', '600px']
                });
            },
            batchdel: function () { // 删除
                var checkStatus = table.checkStatus(tableId)
                    , checkData = checkStatus.data;
                if (checkData.length === 0) {
                    return layer.msg('请选择需要删除数据');
                }
                var ids = "";
                for (var i = 0; i < checkData.length; i++) {
                    ids += checkData[i].id + ","
                }
                layer.confirm('确定删除吗？', function (index) {
                    $.post(
                        "del",
                        {ids: ids},
                        function (rs) {
                            if (rs.success) {
                                layer.msg('成功', {
                                    icon: 6,
                                    time: 2000
                                }, function () {
                                    table.reload(tableId);
                                    layer.close(index);
                                });
                            } else {
                                $("#dataSave").removeAttr("disabled");
                                layer.msg(rs.msg, {icon: 6, time: 2000});
                            }
                        }, "json");
                    return false;
                });
            },
            enable: function () { // 启用
                var checkStatus = table.checkStatus(tableId)
                    , checkData = checkStatus.data;
                var count = 0;
                var ids = "";
                for (var i = 0; i < checkData.length; i++) {
                    if (checkData[i].status === 1) {
                        ids += checkData[i].id + ","
                        count++;
                    }
                }
                if (count === 0) {
                    return layer.msg('请选择需要启用数据');
                }
                layer.confirm('确定启用吗？', function (index) {
                    $.post(
                        "enable",
                        {ids: ids},
                        function (rs) {
                            if (rs.success) {
                                layer.msg('成功', {
                                    icon: 6,
                                    time: 2000
                                }, function () {
                                    table.reload(tableId);
                                    layer.close(index);
                                });
                            } else {
                                $("#dataSave").removeAttr("disabled");
                                layer.msg(rs.msg, {icon: 6, time: 2000});
                            }
                        }, "json");
                    return false;
                });
            },
            disable: function () { // 禁用
                var checkStatus = table.checkStatus(tableId)
                    , checkData = checkStatus.data;
                var count = 0;
                var ids = "";
                for (var i = 0; i < checkData.length; i++) {
                    if (checkData[i].status === 0) {
                        ids += checkData[i].id + ","
                        count++;
                    }
                }
                if (count === 0) {
                    return layer.msg('请选择需要禁用数据');
                }
                layer.confirm('确定禁用吗？', function (index) {
                    $.post(
                        "disable",
                        {ids: ids},
                        function (rs) {
                            if (rs.success) {
                                layer.msg('成功', {
                                    icon: 6,
                                    time: 2000
                                }, function () {
                                    table.reload(tableId);
                                    layer.close(index);
                                });
                            } else {
                                $("#dataSave").removeAttr("disabled");
                                layer.msg(rs.msg, {icon: 6, time: 2000});
                            }
                        }, "json");
                    return false;
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
