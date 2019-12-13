/**
 * 角色集合
 */
layui.define(['table', 'form'], function (exports) {
    var $ = layui.$
        , table = layui.table
        , form = layui.form;

    //分页集合
    table.render({
        elem: '#LAY-app-content-list'
        , url: layui.setter.base + 'adminRole/pageList' //模拟接口
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: 'roleName', width: 150, title: '角色名称', minWidth: 100}
            , {field: 'remark', width: 200, title: '备注', minWidth: 100}
            , {field: 'status', width: 100, title: '状态', minWidth: 100, sort: false, fixed: false, templet: '#status'}
            , {field: 'createUserName', width: 120, title: '创建人', minWidth: 100}
            , {field: 'createTime', width: 200, title: '创建时间', sort: false, fixed: false, templet: '#createTime'}
            , {title: '操作', minWidth: 90, align: 'center', fixed: 'right', toolbar: '#table-content-list'}
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
                , content: 'edit?id=' + data.id
                , maxmin: false
                , area: ['450px', '550px']
            });
        }
    });
    exports('adminRoleList', {})
});