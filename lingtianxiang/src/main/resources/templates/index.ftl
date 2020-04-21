<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户列表</title>
    <link rel="stylesheet" href="/assets/css/bootstrap.css">
</head>
<body>
<div class="container">
    <div class="page-header">
        <h1>代码生成工具
            <small>Subtext for header</small>
        </h1>
    </div>
    <div class="row">
        <div class="col-md-6">
            <div class="well">
                <div class="form-group">
                    <label>类属性</label>
                </div>
                <div class="form-group">
                    <form id="form2" class="form-horizontal">
                    <#--<div class="panel panel-default">
                            <div class="panel-body">
                                <div class="form-group">
                                    <label for="inputEmail3" class="col-sm-2 control-label">类型</label>
                                    <div class="col-sm-10">
                                        <select class="form-control">
                                            <option>请选择属性类型</option>
                                            <option>String</option>
                                            <option>Integer</option>
                                            <option>BigDecimal</option>
                                            <option>Date</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputEmail3" class="col-sm-2 control-label">名称</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" placeholder="请输入属性名称">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputEmail3" class="col-sm-2 control-label">描述</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" placeholder="请输入属性描述">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <button type="button" class="btn btn-danger">删除</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <div class="form-group">
                                    <label for="inputEmail3" class="col-sm-2 control-label">类型</label>
                                    <div class="col-sm-10">
                                        <select class="form-control">
                                            <option>请选择属性类型</option>
                                            <option>String</option>
                                            <option>Integer</option>
                                            <option>BigDecimal</option>
                                            <option>Date</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputEmail3" class="col-sm-2 control-label">名称</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" placeholder="请输入属性名称">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputEmail3" class="col-sm-2 control-label">描述</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" placeholder="请输入属性描述">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <button type="button" class="btn btn-danger">删除</button>
                                    </div>
                                </div>
                            </div>
                        </div>-->
                    </form>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="well">
                <form id="dataForm" action="/create/code" method="post">
                    <div class="form-group">
                        <label for="explain">请求地址根路径</label>
                        <input autocomplete="off" type="text" class="form-control" name="requestRootUrl" id="requestRootUrl" placeholder="请输入请求地址根路径" value="${requestRootUrl}">
                    </div>
                    <div class="form-group">
                        <label for="explain">Code文件生成路径</label>
                        <input autocomplete="off" type="text" class="form-control" name="codeFilePath" id="codeFilePath" placeholder="请输入Code文件生成路径" value="${codeFilePath}">
                    </div>
                    <div class="form-group">
                        <label for="explain">Mapper文件生成路径</label>
                        <input autocomplete="off" type="text" class="form-control" name="mapperFilePath" id="mapperFilePath" placeholder="请输入Mapper文件生成路径" value="${mapperFilePath}">
                    </div>
                    <div class="form-group">
                        <label for="explain">Page文件生成路径</label>
                        <input autocomplete="off" type="text" class="form-control" name="pageFilePath" id="pageFilePath" placeholder="请输入Page文件生成路径" value="${pageFilePath}">
                    </div>
                    <div class="form-group">
                        <label for="explain">包名称</label>
                        <input autocomplete="off" type="text" class="form-control" name="packName" id="packName" placeholder="请输入包名称" value="${packName}">
                    </div>
                    <div class="form-group">
                        <label for="explain">类描述</label>
                        <input autocomplete="off" type="text" class="form-control" name="explain" id="explain" placeholder="请输入类描述">
                    </div>
                    <div class="form-group">
                        <label for="entity">类名称</label>
                        <input autocomplete="off" type="text" class="form-control" name="entity" id="entity" placeholder="请输入类名称">
                    </div>
                    <div class="form-group">
                        <label for="entity">表名称</label>
                        <input autocomplete="off" type="text" class="form-control" name="table" id="table" placeholder="请输入数据库表名称">
                    </div>
                    <div class="form-group">
                        <button type="button" class="btn btn-success" onclick="addAttrPanel()">添加属性</button>
                        <button type="button" class="btn btn-primary" onclick="createCode()">生成代码</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="/assets/js/jquery-3.3.1.js"></script>
<script src="/assets/js/bootstrap.min.js"></script>
<script src="/assets/layer/layer.js"></script>
<script>
    // 添加属性面板
    function addAttrPanel() {
        var tabindex = $("#form2>div").length;
        var str = '<div class="panel panel-default" id="attrPanel' + tabindex + '">' +
                '    <div class="panel-body">' +
                '        <div class="form-group">' +
                '            <label class="col-sm-2 control-label">类型</label>' +
                '            <div class="col-sm-10">' +
                '                <select class="form-control attrType">' +
                '                    <option>String</option>' +
                '                    <option>Integer</option>' +
                '                    <option>BigDecimal</option>' +
                '                    <option>Date</option>' +
                '                </select>' +
                '            </div>' +
                '        </div>' +
                '        <div class="form-group">' +
                '            <label for="inputEmail3" class="col-sm-2 control-label">名称</label>' +
                '            <div class="col-sm-10">' +
                '                <input autocomplete="off" type="text" class="form-control attrName" placeholder="请输入属性名称">' +
                '            </div>' +
                '        </div>' +
                '        <div class="form-group">' +
                '            <label for="inputEmail3" class="col-sm-2 control-label">描述</label>' +
                '            <div class="col-sm-10">' +
                '                <input autocomplete="off" type="text" class="form-control attrDesc" placeholder="请输入属性描述">' +
                '            </div>' +
                '        </div>' +
                '        <div class="form-group">' +
                '            <div class="col-sm-offset-2 col-sm-10">' +
                '                <button type="button" class="btn btn-danger delBtn" onclick="delAttrPanel(' + tabindex + ')">删除</button>' +
                '            </div>' +
                '        </div>' +
                '    </div>' +
                '</div>'
        $("#form2").append(str);
    }

    // 删除属性面板
    function delAttrPanel(index) {
        $("#attrPanel" + index).remove();
        resetTabindex();
    }

    // 重置属性面板索引
    function resetTabindex() {
        $("div[id^='attrPanel']").each(function (index) {
            $(this).attr("id", "attrPanel" + index);
            $(this).find(".delBtn").attr("onclick", "delAttrPanel(" + index + ")");
        })
    }

    // 加工类属性
    function getAttrList() {
        $("div[id^='attrPanel']").each(function (index, element) {
            var attrType = $(this).find(".attrType").val();
            var attrName = $(this).find(".attrName").val();
            var attrDesc = $(this).find(".attrDesc").val();
            var attr = attrType + "#" + attrName + "#" + attrDesc;
            var attrInput = '<input type="hidden" name="attr" value="' + attr + '">';
            $("#dataForm").append(attrInput);
        })
    }

    // 创建代码
    function createCode() {
        layer.confirm('是否生成代码', {
            icon: 3, title: '提示',
            btn: ['是', '否'] //按钮
        }, function () {
            getAttrList();
            $("#dataForm").submit();
            /*$.ajax({
                url: "/create/code",
                type: "post",
                data: $("#dataForm").serialize(),
                dataType: "json",
                success: function (result) {

                },
                error: function () {
                    layer.msg('操作失败', {time: 1000, icon: 5});
                }
            });*/
        });
    }

</script>
</body>
</html>