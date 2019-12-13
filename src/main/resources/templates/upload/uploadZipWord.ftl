<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/layui/css/layui.css" media="all">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>

<blockquote class="layui-elem-quote">为节省服务器开销，以下示例均未配置真实上传接口，所以每次上传都会报提示：请求上传接口出现异常，这属于正常现象。</blockquote>


<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
    <legend>选完文件后不自动上传</legend>
</fieldset>
<input type="hidden" id="nameLevel" name="nameLevel" value="123">
<div class="layui-upload">
    <button type="button" class="layui-btn layui-btn-normal" id="test8">选择文件</button>
    <button type="button" class="layui-btn" id="test9">开始上传</button>
</div>



<script src="/jquery/jquery.min.js"></script>
<script src="/layui/layui.js"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    layui.use('upload', function(){
        var $ = layui.jquery
            ,upload = layui.upload;
        //选完文件后不自动上传
        upload.render({
            elem: '#test8'
            ,url: '/uploadFile/uploadWord'
            ,method: 'POST'
            ,accept: 'file'
            ,auto: false
            ,size: 0
            ,bindAction: '#test9'
            ,data: {
                id: function(){
                    var nameLevel = $('#nameLevel').val();
                    if(nameLevel === '') {
                        layer.msg("参数不能为空", {icon: 6, time: 2000});
                        return;
                    }
                    return nameLevel;
                }
            }
            ,done: function(res){
                if (res.success) {
                    layer.msg('上传成功成功', {
                        icon: 6,
                        time: 2000
                    });
                } else {
                    layer.msg("错误", {icon: 5, time: 2000});
                }
            }
            , error: function () {
                layer.msg('网络异常，请稍后重试！');
            }
        });
    });
</script>

</body>
</html>