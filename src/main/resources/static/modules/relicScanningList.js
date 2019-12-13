/**
 * 文物信息集合
 */
layui.define(['table', 'form'], function (exports) {
    var $ = layui.$
        , table = layui.table
        , form = layui.form;

    var _relicId = $("#relicId").val();
    //分页集合
    table.render({
        elem: '#LAY-app-content-list'
        , url: layui.setter.base + 'relic/pageRelicScanningList' //模拟接口
        , where: {relicId: _relicId}
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: 'projectName', width: 180, title: '项目名称'}
            , {field: 'projectTime', width: 120, title: '项目时间', minWidth: 100}
            , {field: 'designCompany', width: 160, title: '方案设计单位', minWidth: 100}
            , {field: 'repairCompany', width: 180, title: '保护修复单位', minWidth: 100}
            , {field: 'planNameCode', width: 180, title: '方案名称及编号', minWidth: 100}
            , {field: 'createTime', width: 170, title: '创建时间', sort: false, fixed: false, templet: '#createTime'}
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
        if (obj.event === 'edit') {
            layer.open({
                type: 2
                , title: '上传修复扫描记录文件'
                , content: 'openEditRelicScanning?relicScanningId=' + data.id
                , maxmin: false
                , area: ['100%', '100%']
            });
        }
        if (obj.event === 'info') {
            layer.open({
                type: 2
                , title: '修复记录信息详情'
                , content: 'openInfoRelicScanning?relicScanningId=' + data.id
                , maxmin: false
                , area: ['100%', '100%']
            });
        }
        if (obj.event === 'openWord') {
            POBrowser.openWindowModeless('/word/openWord?relicScanningId=' + data.id, 'width=1200px;height=800px;');
        }
    });
    exports('relicScanningList', {})
});