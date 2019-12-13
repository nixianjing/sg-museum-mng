<!DOCTYPE html>
<html style="background: transparent;">
<head>
    <meta charset="utf-8">
    <title>文物浏览</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/style/admin.css" media="all">
    <link rel="stylesheet" href="/style/template.css" media="all">
    <style>
        .layui-laypage .layui-laypage-curr .layui-laypage-em {
            background-color: rgb(186, 209, 253);
        }

        .cmdlist-container img {
            height: 300px;
        }

        .layui-layout-admin .layui-body .layadmin-tabsbody-item {
            -webkit-background-size: 100%;
            background-size: 100%;
        }
    </style>
</head>
<body>
<div class="layui-fluid layadmin-cmdlist-fluid" id="LAY-user-login">
    <div class="layui-row layui-col-space15">
        <!-- 数据渲染 -->
        <div class="layui-row layui-col-space30" style="margin:0px;" id="dataId">

        </div>
        <!-- 分页 -->
        <div class="layui-form-item layui-layout-admin">
            <div class="layui-footer" style="left: 0; padding:0px;">
                <div id="pageDome"></div>
            </div>
        </div>
    </div>
</div>


<script src="/layui/layui.js"></script>
<script>
    layui.config({
        base: '/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index']);
    layui.use(['laypage', 'layer'], function () {
        var $ = layui.$, element = layui.element, laypage = layui.laypage, layer = layui.layer, form = layui.form;
        var currPage = 1; //当前页数
        var pageCount = 0; //当前总数量
        var pageLimit = 18; // 每页数据显示条数
        // 初始化第一页数据
        getListData();
        var active = {
            search: function () { //搜索
                getListData();
            },
            openInfo: function () { // 图片详情
                var key = $(this).data('key');
                window.open("/uploadImage/getImgPath?imgUrl=" + key);
            },
            openScanningInfo: function () { // 列表详情
                var relicId = $(this).data('key');
                layer.open({
                    type: 2
                    , title: '文物信息-修复记录'
                    , content: 'openRelicScanningList?relicId=' + relicId
                    , maxmin: true
                    , area: ['100%', '100%']
                });
            }
        };

        // ajax获取分页数据
        function getListData() {
            $.post("pageBrowseRelicList", {page: currPage, limit: pageLimit}, function (rs) {
                if (rs.success) {
                    pageCount = rs.count;
                    dataRomance(rs.data);
                    paged();
                }
            }, "json");
        };

        // 渲染数据
        function dataRomance(_data) {
            var _html = '';
            for (var i = 0; i < _data.length; i++) {
                var obj = _data[i];
                _html += '<div class="layui-col-md2 layui-col-sm4">' +
                    '                <div class="cmdlist-container">' +
                    '                    <dev class="cmdlist-container-sssss" data-type="openInfo"  data-key="' + obj.imgUrl + '">' +
                    '                        <img style="" src="/uploadImage/getImgPath?imgUrl=' + obj.compressImgUrl + '">' +
                    '                    </dev>' +
                    '                    <div class="cmdlist-text" data-type="openScanningInfo" data-key="' + obj.id + '" style="padding:0px;text-align:center;">' +
                    '                        <p class="info" style="margin-bottom: -10px;margin-top: 10px;">' + obj.relicName + '</p>' +
                    '                    </div>' +
                    '                </div>' +
                    '            </div>';
            }
            $('#dataId').html(_html);
            //监听点击子列表数据
            $('.cmdlist-container-sssss').on('click', function () {
                var type = $(this).data('type');
                active[type] ? active[type].call(this) : '';
            });
            //监听点击子列表数据
            $('.cmdlist-text').on('click', function () {
                var type = $(this).data('type');
                active[type] ? active[type].call(this) : '';
            });
        }

        // 加载分页
        function paged() {
            laypage.render({
                elem: 'pageDome'
                , limit: pageLimit // 没有显示条数
                , count: pageCount //数据总数
                , limits: [18]
                , layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']
                , curr: currPage
                , jump: function (obj, first) {
                    currPage = obj.curr;  //这里是后台返回给前端的当前页数
                    pageLimit = obj.limit; //这里指每页显示条数
                    if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr  ajax 再次请求
                        getListData();
                    }
                },
            });
        }
    });
</script>
</body>
</html>