/**
 * 文物信息集合
 */
layui.define(['table', 'form'], function (exports) {
    var $ = layui.$
        , table = layui.table
        , form = layui.form;
    var tableId = 'LAY-app-content-list';

    //分页集合
    table.render({
        elem: '#LAY-app-content-list'
        , url: layui.setter.base + 'relicReportLoss/pageReportList' //模拟接口
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: 'relicName', width: 180, title: '名称'}
            , {field: 'relicNo', width: 180, title: '编号'}
            , {field: 'reimburseTime', width: 120, title: '报损时间', minWidth: 100}
            , {field: 'userName', width: 160, title: '报损人', minWidth: 100}
            , {field: 'repairTime', width: 180, title: '修复时间', minWidth: 100}
            , {field: 'repairUserName', width: 180, title: '修复人', minWidth: 100}
            , {field: 'status', width: 80, title: '状态', minWidth: 100, sort: false, fixed: false, templet: '#status'}
            , {title: '操作', minWidth: 270, align: 'center', fixed: 'right', toolbar: '#table-content-list'}
        ]]
        , page: true
        , limit: 10
        , limits: [10, 15, 20, 25, 30]
        , text: {none: '暂无相关数据'}
    });
    //监听工具条
    table.on('tool(LAY-app-content-list)', function (obj) {
        var data = obj.data;
        if (obj.event === 'setRestore') {
            layer.confirm('确定修复吗？', function () {
                $.post(
                    "setRestore",
                    {id: data.id},
                    function (rs) {
                        if (rs.success) {
                            layer.msg('成功', {
                                icon: 6,
                                time: 2000
                            }, function () {
                                table.reload(tableId);
                            });
                        } else {
                            layer.msg(rs.msg, {icon: 6, time: 2000});
                        }
                    }, "json");
                return false;
            });
        }
        if (obj.event === 'setRestoreComplete') {
            layer.open({
                type: 2
                , title: '设置修复完成'
                , content: 'openSetRestoreComplete?id=' + data.id
                , maxmin: false
                , area: ['500px', '500px']
            });
        }
        if (obj.event === 'info') {
            layer.open({
                type: 2
                , title: '报损记录详情'
                , content: 'openInfoReport?id=' + data.id
                , maxmin: false
                , area: ['500px', '500px']
            });
        }
    });
    exports('relicReportList', {})
});