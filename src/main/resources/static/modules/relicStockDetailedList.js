/**
 * 文物出入库汇总明细信息集合
 */
layui.define(['table', 'form'], function (exports) {
    var $ = layui.$
        , table = layui.table
        , form = layui.form;

    //分页集合
    table.render({
        elem: '#LAY-app-content-list'
        , url: layui.setter.base + 'relicStock/pageStockDetailedList' //模拟接口
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: 'relicName', width: 100, title: '名称'}
            , {field: 'relicNo', width: 180, title: '文物号', minWidth: 100}
            , {field: 'outTime', width: 100, title: '出库', minWidth: 100}
            , {field: 'userName', width: 100, title: '出库操作人', minWidth: 100}
            , {field: 'enterTime', width: 150, title: '入库', minWidth: 100}
            , {field: 'updateUserName', width: 100, title: '入库操作人', minWidth: 100}
            , {field: 'status', width: 100, title: '状态', minWidth: 100}
            , {field: 'createTime', width: 170, title: '创建时间', sort: false, fixed: false, templet: '#createTime'}
            , {title: '操作', minWidth: 250, align: 'center', fixed: 'right', toolbar: '#table-content-list'}
        ]]
        , page: true
        , limit: 10
        , limits: [10, 15, 20, 25, 30]
        , text: {none: '暂无相关数据'}
    });
    //监听工具条
    table.on('tool(LAY-app-content-list)', function (obj) {
        var data = obj.data;
        if (obj.event === 'outRelic') {
            layer.open({
                type: 2
                , title: '出库操作'
                , content: 'openEditRelic?relicId=' + data.id
                , maxmin: false
                , area: ['100%', '100%']
            });
        }
        if (obj.event === 'enterRelic') {
            layer.open({
                type: 2
                , title: '入库操作'
                , content: 'openEditRelic?relicId=' + data.id
                , maxmin: false
                , area: ['100%', '100%']
            });
        }
        if (obj.event === 'info') {
            layer.open({
                type: 2
                , title: '详情'
                , content: 'openInfoRelic?relicId=' + data.id
                , maxmin: false
                , area: ['100%', '100%']
            });
        }
    });
    exports('relicStockDetailedList', {})
});