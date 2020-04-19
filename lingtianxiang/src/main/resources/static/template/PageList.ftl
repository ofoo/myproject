<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title th:text="$${"{listPageTitle}"}">页面标题</title>
    <link rel="stylesheet" href="/layui/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo" th:text="$${"{sysName}"}">系统名称</div>
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="/warehouse/console/index">控制台</a></li>
            <li class="layui-nav-item"><a href="/warehouse/brand/list">商品</a></li>
            <li class="layui-nav-item"><a href="/warehouse/maintain/list">维修</a></li>
            <li class="layui-nav-item layui-this"><a href="/warehouse/brand/list">品牌</a></li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    <span th:text="凌天香">管理员</span>
                </a>
            </li>
            <li class="layui-nav-item"><a href="/logout">退出</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item layui-this"><a href="${requestRootUrl}/list"
                                                         th:text="$${"{listPageTitle}"}">列表</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body" style="background-color: #F2F2F2;">
        <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
            <ul class="layui-tab-title">
                <li class="layui-this" th:text="$${"{listPageTitle}"}">列表</li>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">
                    <div class="layui-card">
                        <div class="layui-card-body">
                            <form class="layui-form">
                                <div class="layui-row">
                                    <#list attrList as attr>
                                    <div class="layui-col-md3">
                                        <label class="layui-form-label">${attr.desc}</label>
                                        <div class="layui-input-inline">
                                            <input type="text" name="${attr.name}" lay-verify="required"
                                                   id="${attr.name}"
                                                   autocomplete="off"
                                                   class="layui-input">
                                        </div>
                                    </div>
                                    </#list>
                                    <div class="layui-col-md3">
                                        <button type="button" class="layui-btn" onclick="tableReload()">查询</button>
                                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="layui-card">
                        <div class="layui-card-body">
                            <table id="demo" lay-filter="test"></table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © 郭郭
    </div>
</div>
<script src="/layui/layui.all.js"></script>
<script th:inline="none">
    layui.$(function () {
        layui.table.render({
            elem: '#demo'
            , url: '${requestRootUrl}/ajax/list'
            , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            , cols: [[
                {type: 'checkbox'}
                <#list attrList as attr>
                , {field: '${attr.name}', title: '${attr.desc}'}
                </#list>
                , {title: '操作', toolbar: '#barDemo', width: 150}
            ]]
            // , toolbar: 'default'
            , page: true
            , title: '${explain}列表'
            , autoSort: false
            , skin: 'line' //行边框风格
            , none: '暂无相关数据'
            , toolbar: '#toolbarDemo'
        });
        layui.table.on('toolbar(test)', function (obj) {
            var checkStatus = layui.table.checkStatus(obj.config.id);
            switch (obj.event) {
                case 'add':
                    openPage('${requestRootUrl}/add', "添加${explain}");
                    break;
                case 'delete':
                    if (checkStatus.data.length <= 0) {
                        layer.msg("请选择数据");
                        return;
                    }
                    deleteDate(checkStatus.data);
                    break;
                case 'refresh':
                    tableReload()
                    break;
            }
        })
        //监听工具条
        layui.table.on('tool(test)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）

            if (layEvent === 'delete') { //删除
                deleteDate(new Array(data));
            } else if (layEvent === 'update') { //编辑
                openPage("/ynz/${entity?lower_case}/update/" + data.id,"修改${explain}")
            }
        });
    })

    // 删除数据
    function deleteDate(data) {
        layer.confirm('是否删除？', function (index) {
            layui.$.ajax({
                url: '${requestRootUrl}/ajax/delete',
                type: 'post',
                data: JSON.stringify(data),
                dataType: 'json',
                contentType: "application/json",
                success: function (result) {
                    if (result.success) {
                        layer.msg(result.msg, {time: 1000}, function () {
                            tableReload();
                        })
                    } else {
                        layer.msg(result.msg)
                    }
                }
            });
        });
    }

    // 打开页面
    function openPage(url, title) {
        layui.$.post(url, {}, function (str) {
            layer.open({
                type: 1
                , content: str //注意，如果str是object，那么需要字符拼接。
                , area: ['50%', '45%']
                , title: title
            });
        });
    }

    // 表格重载
    function tableReload() {
        layui.table.reload('demo', {
            where: {
                "page": 1,
                "name": layui.$("#name").val(),
                "mobile": layui.$("#mobile").val()
            }
        });
    }
</script>
<script type="text/html" id="toolbarDemo">
    <div class="layui-inline" lay-event="add"><i class="layui-icon layui-icon-add-1"></i></div>
    <div class="layui-inline" lay-event="delete"><i class="layui-icon layui-icon-delete"></i></div>
    <div class="layui-inline" lay-event="refresh"><i class="layui-icon layui-icon-refresh"></i></div>
</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="update">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</a>
</script>
</body>
</html>