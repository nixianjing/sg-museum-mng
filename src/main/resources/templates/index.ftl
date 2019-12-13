<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>国家文物管理系统</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/style/admin.css" media="all">
    <script>
        /^http(s*):\/\//.test(location.href) || alert('请先部署到 localhost 下再访问');
    </script>
</head>
<body class="layui-layout-body">
<div id="LAY_app">
    <div class="layui-layout layui-layout-admin">
        <div class="layui-header">
            <!-- 头部区域 -->
            <ul class="layui-nav layui-layout-left">
                <li class="layui-nav-item layadmin-flexible" lay-unselect>
                    <a href="javascript:;" layadmin-event="flexible" title="侧边伸缩">
                        <i class="layui-icon layui-icon-shrink-right" id="LAY_app_flexible"></i>
                    </a>
                </li>
                <li class="layui-nav-item" lay-unselect>
                    <a href="javascript:;" layadmin-event="refresh" title="刷新">
                        <i class="layui-icon layui-icon-refresh-3"></i>
                    </a>
                </li>
            </ul>
            <ul class="layui-nav layui-layout-right" lay-filter="layadmin-layout-right">
                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="javascript:;" layadmin-event="fullscreen">
                        <i class="layui-icon layui-icon-screen-full"></i>
                    </a>
                </li>
                <li class="layui-nav-item" lay-unselect>
                    <a href="javascript:;">
                        <cite>${adminUser.name}</cite>
                    </a>
                </li>
                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a lay-href="adminUser/openRestPwd.html" >
                        修改密码
                    </a>
                </li>
                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="javascript:;" layadmin-event="logout">
                        退出
                    </a>
                </li>
            </ul>
        </div>

        <!-- 侧边菜单 -->
        <div class="layui-side layui-side-menu">
            <div class="layui-side-scroll">
                <div class="layui-logo" lay-href="home/console.html">
                    <span>MNG</span>
                </div>

                <ul class="layui-nav layui-nav-tree" lay-shrink="all" id="LAY-system-side-menu"
                    lay-filter="layadmin-system-side-menu">
                    <@permission token="100,1001,1002,1003,1004">
                        <li data-name="set" class="layui-nav-item">
                            <a href="javascript:;" lay-tips="系统设置" lay-direction="1">
                                <i class="layui-icon layui-icon-set-fill"></i>
                                <cite>系统设置</cite>
                            </a>
                            <dl class="layui-nav-child">
                                <@permission token="1001,1001001">
                                    <dd data-name="console" class="layui-this">
                                        <a lay-href="adminUser/openList">用户管理</a>
                                    </dd>
                                </@permission>
                                <@permission token="1002,1002001">
                                    <dd data-name="console">
                                        <a lay-href="adminRole/openList">角色管理</a>
                                    </dd>
                                </@permission>
                                <@permission token="1003,1003001">
                                    <dd data-name="console">
                                        <a lay-href="adminDict/openList">字典管理</a>
                                    </dd>
                                </@permission>
                                <@permission token="1004,1004001">
                                    <dd data-name="console">
                                        <a lay-href="businessLog/openList">日志管理</a>
                                    </dd>
                                </@permission>
                            </dl>
                        </li>
                    </@permission>
                    <@permission token="200,2001,2002">
                        <li data-name="template" class="layui-nav-item">
                            <a href="javascript:;" lay-tips="文物管理" lay-direction="1">
                                <i class="layui-icon layui-icon-home"></i>
                                <cite>资料管理</cite>
                            </a>
                            <dl class="layui-nav-child">
                                <@permission token="2002,2002001">
                                    <dd data-name="console" class="layui-this">
                                        <a lay-href="relic/openBrowseRelicList">文物浏览</a>
                                    </dd>
                                </@permission>
                                <@permission token="2001,2001001">
                                    <dd data-name="console">
                                        <a lay-href="relic/openRelicList">文物信息</a>
                                    </dd>
                                </@permission>

                            </dl>
                        </li>
                    </@permission>
                </ul>
            </div>
        </div>

        <!-- 页面标签 -->
        <div class="layadmin-pagetabs" id="LAY_app_tabs">
            <div class="layui-icon layadmin-tabs-control layui-icon-prev" layadmin-event="leftPage"></div>
            <div class="layui-icon layadmin-tabs-control layui-icon-next" layadmin-event="rightPage"></div>
            <div class="layui-icon layadmin-tabs-control layui-icon-down">
                <ul class="layui-nav layadmin-tabs-select" lay-filter="layadmin-pagetabs-nav">
                    <li class="layui-nav-item" lay-unselect>
                        <a href="javascript:;"></a>
                        <dl class="layui-nav-child layui-anim-fadein">
                            <dd layadmin-event="closeThisTabs"><a href="javascript:;">关闭当前标签页</a></dd>
                            <dd layadmin-event="closeOtherTabs"><a href="javascript:;">关闭其它标签页</a></dd>
                            <dd layadmin-event="closeAllTabs"><a href="javascript:;">关闭全部标签页</a></dd>
                        </dl>
                    </li>
                </ul>
            </div>
            <div class="layui-tab" lay-unauto lay-allowClose="true" lay-filter="layadmin-layout-tabs">
                <ul class="layui-tab-title" id="LAY_app_tabsheader">
                    <li lay-id="console.html" lay-attr="console.html" class="layui-this"><i
                                class="layui-icon layui-icon-home"></i></li>
                </ul>
            </div>
        </div>
        <!-- 主体内容 -->
        <div class="layui-body" id="LAY_app_body">
            <div class="layadmin-tabsbody-item layui-show">
                <iframe src="console.html" frameborder="0" class="layadmin-iframe"></iframe>
            </div>
        </div>
        <!-- 辅助元素，一般用于移动设备下遮罩 -->
        <div class="layadmin-body-shade" layadmin-event="shade"></div>
    </div>
</div>

<script src="/layui/layui.js"></script>
<script>
    layui.config({
        base: '/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use('index');
</script>
</body>
</html>