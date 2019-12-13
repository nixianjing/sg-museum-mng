<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>报损详情</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/layui/css/layui.css" media="all">
    <style>
        #permissionId {
            margin-bottom: 100px;
            width: 200px;
        }
    </style>
</head>
<body>

<div lay-filter="layuiadmin-form-admin" id="layuiadmin-form-admin" style="padding: 20px 30px 0 0;">
    <form class="layui-form" action="" lay-filter="component-form-group">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">名称</label>
                <div class="layui-input-inline">
                    <input type="text" disabled="disabled" value="${relicDTO.relicName}" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">文物编号</label>
                <div class="layui-input-inline">
                    <input type="text" disabled="disabled" value="${relicDTO.relicNo}" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">年代</label>
                <div class="layui-input-inline">
                    <input type="text" disabled="disabled" value="${relicDTO.years}" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">来源</label>
                <div class="layui-input-inline">
                    <input type="text" disabled="disabled" value="${relicDTO.source}" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">等级</label>
                <div class="layui-input-inline">
                    <input type="text" disabled="disabled" value="${relicDTO.grade}" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">尺寸</label>
                <div class="layui-input-inline">
                    <input type="text" disabled="disabled" value="${relicDTO.dimensions}" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">种类</label>
                <div class="layui-input-inline">
                    <input type="text" disabled="disabled" value="${relicDTO.relicType}" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">质地</label>
                <div class="layui-input-inline">
                    <input type="text" disabled="disabled" value="${relicDTO.texture}" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">收藏单位</label>
                <div class="layui-input-inline">
                    <input type="text" disabled="disabled" value="${relicDTO.collectionCompany}" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">收藏时间</label>
                <div class="layui-input-inline">
                    <input type="text" disabled="disabled" value="${relicDTO.collectionTime}" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">制造工艺</label>
                <div class="layui-input-inline">
                    <input type="text" disabled="disabled" value="${relicDTO.manufacture}" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">织物组织</label>
                <div class="layui-input-inline">
                    <input type="text" disabled="disabled" value="${relicDTO.textile}" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item" style="text-align:center">
            <div class="layui-upload-list">
                <#if relicDTO.imgUrl?? && relicDTO.imgUrl != ''>
                    <img class="layui-upload-img" id="relic-upload-img" src="/uploadImage/getImgPath?imgUrl=${relicDTO.imgUrl!''}" style="width: 400px; height: 400px;top:0;left:0;z-index: 0;margin:0px;">
                    <p id="test-upload-demoText">文物LOG图</p>
                <#else>
                    <img class="layui-upload-img" id="relic-upload-img" style="width: 400px; height: 400px;top:0;left:0;z-index: 0;margin:0px;">
                    <p id="test-upload-demoText">文物LOG图</p>
                </#if>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block" style="text-align: center;margin-left: 0;">
                <button type="reset" class="layui-btn layui-btn-normal">关闭</button>
            </div>
        </div>
    </form>
</div>
<script src="/layui/layui.js"></script>
<script>
    layui.config({
        base: '/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'upload', 'form'], function () {
        var $ = layui.$
            , tree = layui.tree
            , layer = layui.layer
            , upload = layui.upload
            , form = layui.form;


        //监听关闭按钮
        $('.layui-btn-normal').on('click', function () {
            window.parent.location.reload();
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });
    })
</script>
</body>
</html>