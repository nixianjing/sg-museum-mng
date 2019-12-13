<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>文物修复记录详情</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/style/admin.css" media="all">
    <link rel="stylesheet" href="/style/template.css" media="all">
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
            <div class="layui-form-item">
                <div class="layui-inline" style="width: 64%;">
                    <label class="layui-form-label">项目名称</label>
                    <div class="layui-input-inline-one" style="width: 81.5%;">
                        <input type="text" disabled="disabled" value="${relicScanningDTO.projectName!''}"
                               autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">项目时间</label>
                    <div class="layui-input-inline">
                        <input type="text" disabled="disabled" value="${relicScanningDTO.projectTime!''}"
                               autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">名称</label>
                <div class="layui-input-inline">
                    <input type="text" disabled="disabled" value="${relicDTO.relicName!''}" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">文物编号</label>
                <div class="layui-input-inline">
                    <input type="text" disabled="disabled" value="${relicDTO.relicNo!''}" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">年代</label>
                <div class="layui-input-inline">
                    <input type="text" disabled="disabled" value="${relicDTO.years!''}" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">来源</label>
                <div class="layui-input-inline">
                    <input type="text" disabled="disabled" value="${relicDTO.source!''}" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">等级</label>
                <div class="layui-input-inline">
                    <input type="text" disabled="disabled" value="${relicDTO.grade!''}" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">尺寸</label>
                <div class="layui-input-inline">
                    <input type="text" disabled="disabled" value="${relicDTO.dimensions!''}" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">种类</label>
                <div class="layui-input-inline">
                    <input type="text" disabled="disabled" value="${relicDTO.relicType!''}" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">质地</label>
                <div class="layui-input-inline">
                    <input type="text" disabled="disabled" value="${relicDTO.texture!''}" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">收藏单位</label>
                <div class="layui-input-inline">
                    <input type="text" disabled="disabled" value="${relicDTO.collectionCompany!''}" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">收藏时间</label>
                <div class="layui-input-inline">
                    <input type="text" disabled="disabled" value="${relicDTO.collectionTime!''}" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">制造工艺</label>
                <div class="layui-input-inline">
                    <input type="text" disabled="disabled" value="${relicDTO.manufacture!''}" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">织物组织</label>
                <div class="layui-input-inline">
                    <input type="text" disabled="disabled" value="${relicDTO.textile!''}" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">织物密度-经线</label>
            <div class="layui-input-block" style="width: 84.5%;">
                <input type="text" name="title" autocomplete="off" value="${relicScanningDTO.densityLng!''}"
                       disabled="disabled" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">织物密度-纬线</label>
            <div class="layui-input-block" style="width: 84.5%;">
                <input type="text" name="title" autocomplete="off" value="${relicScanningDTO.densityLat!''}"
                       disabled="disabled" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">纱线颜色-经线</label>
            <div class="layui-input-block" style="width: 84.5%;">
                <input type="text" name="title" autocomplete="off" value="${relicScanningDTO.colourLng!''}"
                       disabled="disabled" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">纱线颜色-纬线</label>
            <div class="layui-input-block" style="width: 84.5%;">
                <input type="text" name="title" autocomplete="off" value="${relicScanningDTO.colourLat!''}"
                       disabled="disabled" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline" style="width: 64%;">
                <label class="layui-form-label">纱线细度</label>
                <div class="layui-input-inline-one" style="width: 81.5%;">
                    <input type="text" disabled="disabled" value="${relicScanningDTO.fineness!''}" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">纱线捻度</label>
                <div class="layui-input-inline">
                    <input type="text" disabled="disabled" value="${relicScanningDTO.twist!''}" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">纱线捻向</label>
            <div class="layui-input-block" style="width: 84.5%;">
                <input type="text" name="title" autocomplete="off" value="${relicScanningDTO.twistDirection!''}"
                       disabled="disabled" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">文字</label>
            <div class="layui-input-block" style="width: 85%;">
                <textarea class="layui-textarea">${relicScanningDTO.relicExplain!''}</textarea>
            </div>
        </div>


        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">方案设计单位</label>
                <div class="layui-input-inline">
                    <input type="text" disabled="disabled" value="${relicScanningDTO.designCompany!''}" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">保护修复单位</label>
                <div class="layui-input-inline">
                    <input type="text" disabled="disabled" value="${relicScanningDTO.repairCompany!''}" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">方案名称及编号</label>
                <div class="layui-input-inline">
                    <input type="text" disabled="disabled" value="${relicScanningDTO.planNameCode!''}" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">批准单位及文号</label>
                <div class="layui-input-inline">
                    <input type="text" disabled="disabled" value="${relicScanningDTO.companyCode!''}" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">提取日期</label>
                <div class="layui-input-inline">
                    <input type="text" disabled="disabled" value="${relicScanningDTO.extractTime!''}" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">提取经办人</label>
                <div class="layui-input-inline">
                    <input type="text" disabled="disabled" value="${relicScanningDTO.extractUserName!''}"
                           autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">返还日期</label>
                <div class="layui-input-inline">
                    <input type="text" disabled="disabled" value="${relicScanningDTO.returnTime!''}" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">返还经办人</label>
                <div class="layui-input-inline">
                    <input type="text" disabled="disabled" value="${relicScanningDTO.returnUserName!''}" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block" style="width: 85%;">
                <textarea class="layui-textarea">${relicScanningDTO.remarks!''}</textarea>
            </div>
        </div>

        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">藏品保存环境</label>
            <div class="layui-input-block" style="width: 85%;">
                <textarea class="layui-textarea">${relicScanningDTO.environment!''}</textarea>
            </div>
        </div>

        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">原保护修复情况</label>
            <div class="layui-input-block" style="width: 85%;">
                <textarea class="layui-textarea">${relicScanningDTO.protect!''}</textarea>
            </div>
        </div>

        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">病害状况描述</label>
            <div class="layui-input-block" style="width: 85%;">
                <textarea class="layui-textarea">${relicScanningDTO.disease!''}</textarea>
            </div>
        </div>
    </form>
</div>
<!-- 图片信息 -->
<div class="layui-fluid layadmin-cmdlist-fluid">
    <#if imgDTOList??>
        <#list imgDTOList as item>
            <#if item.imgDTOList??>
                <div class="layui-form-item">
                    <div class="layui-input-block" style="text-align: center;margin-left: 0;">
                        <h1>${item.imgTypeName}</h1>
                    </div>
                </div>
                <div class="layui-row layui-col-space30">
                <#list item.imgDTOList as items>
                    <div class="layui-col-md2 layui-col-sm4" style="padding: 5px; width: 32.667%;">
                        <div class="cmdlist-container">
                            <a href="javascript:;">
                                <img style="height: 300px;" src="/uploadImage/getImgPath?imgUrl=${items.compressImgUrl!''}">
                            </a>
                            <div class="cmdlist-text">
                                <p class="info">${items.relicExplain!''}</p>
                            </div>
                        </div>
                    </div>
                </#list>
                </div>
            </#if>
        </#list>
    </#if>
    <div class="layui-form-item">
        <div class="layui-input-block" style="text-align: center;margin-left: 0;">
            <input type="hidden" id="id" name="id" value="${relicDTO.id}">
            <button type="reset" class="layui-btn layui-btn-normal">关闭</button>
        </div>
    </div>
</div>
<script src="/layui/layui.js"></script>
<script>
    layui.config({
        base: '/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'upload', 'form'], function () {
        var $ = layui.$
            , layer = layui.layer
            , upload = layui.upload;

        //普通图片上传
        var uploadInst = upload.render({
            elem: '#relic-upload-img'
            , url: '/uploadImage/uploadRelicImg'
            , method: 'POST'
            , before: function (obj) {
                this.data = {"relicId": $("#id").val()};
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    $('#test-upload-demoText').remove();
                    $('#relic-upload-img').attr('src', result); //图片链接（base64）
                });
            }
            , done: function (res) {
                if (res.code == 0) {
                    layer.msg('上传成功', {
                        icon: 6,
                        time: 2000
                    });
                } else {
                    layer.msg("上传失败", {icon: 6, time: 2000});
                }
            }
            , error: function () {//请求异常回调
                layer.closeAll('loading');
                layer.msg('网络异常，请稍后重试！');
            }
        });


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