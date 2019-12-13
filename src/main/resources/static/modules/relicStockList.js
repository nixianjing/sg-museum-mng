/**
 * 文物出入库汇总信息集合
 */
layui.define(['table', 'form'], function (exports) {
    var $ = layui.$
        , table = layui.table
        , form = layui.form;

    //分页集合
    table.render({
        elem: '#LAY-app-content-list'
        , url: layui.setter.base + 'relicStock/pageStockList' //模拟接口
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: 'stayStockNum', width: 100, title: '待出库'}
            , {field: 'outStockNum', width: 180, title: '已出库', minWidth: 100}
            , {field: 'enterStockNum', width: 100, title: '已入库', minWidth: 100}
            , {field: 'userName', width: 150, title: '操作人', minWidth: 100}
            , {field: 'createTime', width: 170, title: '操作时间', sort: false, fixed: false, templet: '#createTime'}
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
        if (obj.event === 'edit') {
            layer.open({
                type: 2
                , title: '修复'
                , content: 'openEditRelic?relicId=' + data.id
                , maxmin: false
                , area: ['100%', '100%']
            });
        }
        if (obj.event === 'info') {
            layer.open({
                type: 2
                , title: '修复完成'
                , content: 'openInfoRelic?relicId=' + data.id
                , maxmin: false
                , area: ['100%', '100%']
            });
        }
    });
    exports('relicStockList', {})
});