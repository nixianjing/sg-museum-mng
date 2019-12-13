/**

 @Name：layuiAdmin 内容系统
 @Author：star1029
 @Site：http://www.layui.com/admin/
 @License：LPPL

 */


layui.define(['table', 'form'], function(exports){
    var $ = layui.$
        ,table = layui.table
        ,form = layui.form;

    //文章管理
    table.render({
        elem: '#LAY-app-content-list'
        ,url: layui.setter.base + 'adminDict/pageList' //模拟接口
        ,cols: [[
            {type: 'checkbox', fixed: 'left'}
            ,{field: 'dictTypeCode', width: 250, title: '分类编码', minWidth: 100}
            ,{field: 'dictTypeName', width: 250, title: '分类名称', minWidth: 100}
            ,{field: 'dictName', width: 250, title: '名称', minWidth: 100}
            ,{field: 'createTime', title: '创建时间', sort: false, fixed:false, templet:'#createTime'}
            ,{title: '操作', minWidth: 100, align: 'center', fixed: 'right', toolbar: '#table-content-list'}
        ]]
        ,page: true
        ,limit: 10
        ,limits: [10, 15, 20, 25, 30]
        ,text:{none: '暂无相关数据'}
    });

    //监听工具条
    table.on('tool(LAY-app-content-list)', function(obj){
        var data = obj.data;
        if(obj.event === 'del'){
            layer.confirm('确定删除此文章？', function(index){
                obj.del();
                layer.close(index);
            });
        } else if(obj.event === 'edit'){
            layer.open({
                type: 2
                , title: '编辑字典'
                , content: 'edit?id=' + data.id
                , maxmin: false
                , area: ['450px', '550px']
            });
        }
    });
    exports('adminDictList', {})
});