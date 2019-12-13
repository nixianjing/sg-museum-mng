<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>国家文物管理系统</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/style/admin.css" media="all">
    <link rel="stylesheet" href="/style/login.css" media="all">
</head>
<body>
<div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login">
    <div class="layadmin-user-login-main">
        <div class="layadmin-user-login-box layadmin-user-login-header">
            <h2>文物管理平台</h2>
            <p>国家文物管理系统</p>
        </div>
        <div class="layadmin-user-login-box layadmin-user-login-body layui-form">
            <div class="layui-form-item">
                <label class="layadmin-user-login-icon layui-icon layui-icon-username" for="LAY-user-login-username"></label>
                <input type="text" name="username" id="LAY-user-login-username" lay-verify="required" placeholder="用户名/手机号码" class="layui-input">
            </div>
            <div class="layui-form-item">
                <label class="layadmin-user-login-icon layui-icon layui-icon-password" for="LAY-user-login-password"></label>
                <input type="password" name="password" id="LAY-user-login-password" lay-verify="required" placeholder="密码" class="layui-input">
            </div>
            <div class="layui-form-item" style="margin-bottom: 20px;">
                <input type="checkbox" name="remember" lay-skin="primary" title="记住密码">
            </div>
            <#--<div class="layui-form-item">-->
                <#--<div class="layui-row">-->
                    <#--<div class="layui-col-xs7">-->
                        <#--<label class="layadmin-user-login-icon layui-icon layui-icon-vercode" for="LAY-user-login-vercode"></label>-->
                        <#--<input type="text" name="vercode" id="LAY-user-login-vercode" lay-verify="required" placeholder="图形验证码" class="layui-input">-->
                    <#--</div>-->
                    <#--<div class="layui-col-xs5">-->
                        <#--<div style="margin-left: 10px;">-->
                            <#--<img src="https://www.oschina.net/action/user/captcha" class="layadmin-user-login-codeimg" id="LAY-user-get-vercode">-->
                        <#--</div>-->
                    <#--</div>-->
                <#--</div>-->
            <#--</div>-->
            <div class="layui-form-item">
                <button class="layui-btn layui-btn-fluid" lay-submit lay-filter="LAY-user-login-submit">登 入</button>
            </div>
        </div>
    </div>
    <div class="layui-trans layadmin-user-login-footer">
        <p>© 2019 <a>浙江大学文物修复研究院</a></p>
        <p>邮箱: <a href="" target="_blank">zyqcsm@163.com</a></p>
    </div>
</div>

<script src="/layui/layui.js"></script>
<script>
    layui.config({
        base: '/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'login'], function(){
        var $ = layui.$
            ,setter = layui.setter
            ,admin = layui.admin
            ,form = layui.form
            ,router = layui.router()
            ,search = router.search;
        form.render();
        //提交
        form.on('submit(LAY-user-login-submit)', function(obj){
            var _userCode = $("#LAY-user-login-username").val();
            var _password = $("#LAY-user-login-password").val();
            $.post("/login.json", {'numberCode': _userCode, 'password': _password}, function (rs) {
                if (rs.success) {
                    //登入成功的提示与跳转
                    layer.msg('登入成功', {
                        offset: '15px'
                        ,icon: 1
                        ,time: 1000
                    }, function(){
                        location.href = '/index.html'; //后台主页
                    });
                } else {
                    layer.msg(rs.message, {
                        offset: '30px'
                        ,icon: 2
                        ,time: 1000
                    });
                }
            }, "json");
        });
        return false;
    });
</script>
</body>
</html>