/**
 * 日志集合
 */
layui.define(['table', 'form'], function (exports) {
    var $ = layui.$
        , table = layui.table
        , form = layui.form;

    //分页集合
    table.render({
        elem: '#LAY-app-content-list'
        , url: layui.setter.base + 'businessLog/pageList' //模拟接口
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: 'businessTypeName', width: 120, title: '业务名称'}
            , {field: 'updateTypeName', width: 100, title: '变更类型'}
            , {field: 'updateTypeMessage', title: '描述', minWidth: 100}
            , {field: 'updateUserName', width: 110, title: '操作人', minWidth: 100}
            , {field: 'updateTime', width: 160, title: '操作时间', sort: false, fixed: false, templet: '#updateTime'}
            , {title: '操作', minWidth: 100, align: 'center', fixed: 'right', toolbar: '#table-content-list'}
        ]]
        , page: true
        , limit: 10
        , limits: [10, 15, 20, 25, 30]
        , text:{none: '暂无相关数据'}
    });
    //监听工具条
    table.on('tool(LAY-app-content-list)', function (obj) {
        var data = obj.data;
        if (obj.event === 'info') {
            layer.open({
                type: 2
                , title: '详情'
                , content: 'openEdit?id=' + data.id
                , maxmin: false
                , area: ['100%', '100%']
            });
        }
    });
    exports('businessList', {})
});