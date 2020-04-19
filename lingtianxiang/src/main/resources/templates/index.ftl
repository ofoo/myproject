<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户列表</title>
    <link rel="stylesheet" href="/assets/css/bootstrap.css">
</head>
<body>
<div class="page-header"></div>
<form action="/create/code" id="dataForm">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <div class="well">
                    <div class="form-group">
                        <label for="database">数据库名称</label>
                        <input type="text" class="form-control" name="database" id="database" placeholder="数据库名称">
                    </div>
                    <div class="form-group">
                        <label for="explain">类说明</label>
                        <input type="text" class="form-control" name="explain" id="explain" placeholder="用户">
                    </div>
                    <div class="form-group">
                        <label for="entity">实体类名称</label>
                        <input type="text" class="form-control" name="entity" id="entity" placeholder="User">
                    </div>
                </div>
            </div>
            <div class="col-md-1">
                <p>
                    <button type="submit" class="btn btn-primary btn-block">提交</button>
                </p>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="well">
                    <p class="pull-right">
                        <button type="button" class="btn btn-primary btn-xs" id="addAttr">增加属性</button>
                    </p>
                    <div class="form-group">
                        <label>属性<strong>（类型/名称/描述）</strong></label>
                    </div>
                    <div class="form-group" id="attrCon">
                        <div class="input-group">
                            <div class="input-group-btn">
                                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"
                                        aria-haspopup="true" aria-expanded="false"><span class="typeName">类型</span> <span
                                        class="caret"></span></button>
                                <ul class="dropdown-menu">
                                    <li><a href="javascript:void(0)" class="attrType">String</a></li>
                                    <li><a href="javascript:void(0)" class="attrType">Integer</a></li>
                                    <li><a href="javascript:void(0)" class="attrType">BigDecimal</a></li>
                                    <li><a href="javascript:void(0)" class="attrType">Date</a></li>
                                </ul>
                            </div><!-- /btn-group -->
                            <input type="text" class="form-control" name="attrValue" placeholder="类型/名称/描述">
                            <input type="hidden" name="attr">
                            <a href="javascript:void(0)" class="input-group-addon delAttr">
                                <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>

<script src="/assets/js/jquery-3.3.1.js"></script>
<script src="/assets/js/bootstrap.min.js"></script>
<script>
    $("#addAttr").on("click", function () {
        var tabindex = $("[name='attrValue']").length + 1;
        var str = '<p></p>' +
                '<div class="input-group">' +
                '    <div class="input-group-btn">' +
                '        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"' +
                '                aria-haspopup="true" aria-expanded="false"><span class="typeName">类型</span> <span\n' +
                '                class="caret"></span></button>' +
                '        <ul class="dropdown-menu">' +
                '            <li><a href="javascript:void(0)" class="attrType">String</a></li>' +
                '            <li><a href="javascript:void(0)" class="attrType">Integer</a></li>' +
                '            <li><a href="javascript:void(0)" class="attrType">BigDecimal</a></li>' +
                '            <li><a href="javascript:void(0)" class="attrType">Date</a></li>' +
                '        </ul>' +
                '    </div><!-- /btn-group -->' +
                '    <input type="text" class="form-control" name="attr" placeholder="类型/名称/描述" tabindex="' + (tabindex) + '">' +
                '    <input type="hidden" name="attr">' +
                '    <a href="javascript:void(0)" class="input-group-addon" onclick="delAttr(this)">' +
                '        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>' +
                '    </a>' +
                '</div>'
        $("#attrCon").append(str);
    })
    $(".delAttr").on("click", function () {
        delAttr(this);
    })

    $("#attrValue").on("onkeydown",function(){
        changeAttr(this);
    })

    function delAttr(em) {
        $(em).parent().remove();
        resetTabindex();
    }

    function changeAttr(em){
        console.log($(em).val());
    }

    function resetTabindex() {
        $("[name='attr']").each(function (index) {
            $(this).attr("tabindex", (index + 1));
        })
    }

    $(".attrType").on("click", function () {
        $("#attrType").text($(this).text());

    })

    $("#createCode").on("click", function () {
        $.ajax({
            url: "/create/code",
            type: "post",
            data: $("#dataForm").serialize(),
            dataType: "json",
            success: function (result) {
            }
        });
    })
</script>
</body>
</html>