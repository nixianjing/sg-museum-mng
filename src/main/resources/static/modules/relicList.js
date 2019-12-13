/**
 * 文物信息集合
 */
layui.define(['table', 'form'], function (exports) {
    var $ = layui.$
        , table = layui.table
        , form = layui.form;

    //分页集合
    table.render({
        elem: '#LAY-app-content-list'
        , url: layui.setter.base + 'relic/pageRelicList' //模拟接口
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: 'relicName', width: 100, title: '名称'}
            , {field: 'relicNo', width: 180, title: '文物号', minWidth: 100}
            , {field: 'years', width: 100, title: '年代', minWidth: 100}
            , {field: 'source', width: 150, title: '来源', minWidth: 100}
            , {field: 'grade', width: 100, title: '等级', minWidth: 100}
            , {field: 'createTime', width: 170, title: '创建时间', sort: false, fixed: false, templet: '#createTime'}
            , {title: '操作', minWidth: 250, align: 'center', fixed: 'right', toolbar: '#table-content-list'}
        ]]
        , page: true
        , limit: 10
        , limits: [10, 15, 20, 25, 30]
        , text:{none: '暂无相关数据'}
    });
    //监听工具条
    table.on('tool(LAY-app-content-list)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {
            layer.open({
                type: 2
                , title: '编辑'
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
        if (obj.event === 'relicScanningList') {
            layer.open({
                type: 2
                , title: '文物信息-修复记录'
                , content: 'openRelicScanningList?relicId=' + data.id
                , maxmin: true
                , area: ['100%', '100%']
            });
        }
    });
    exports('relicList', {})
});