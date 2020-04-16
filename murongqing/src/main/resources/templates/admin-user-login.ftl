<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <#assign title="登录"/>
    <#include "common/header-script.ftl">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-offset-4 col-md-4 col-sm-offset-3 col-sm-6">
            <form id="dataForm" class="login">
                <h1>风雨楼</h1>
                <div class="form-group">
                    <input type="text" name="name" class="form-control" placeholder="名称" value="郭超">
                </div>
                <div class="form-group">
                    <input type="password" name="pwd" class="form-control" placeholder="密码" value="guochao">
                </div>
                <div class="form-group">
                    <button id="murongqing-login" type="button" class="btn btn-primary btn-block">免注册，一键登录</button>
                </div>
            </form>
        </div>
    </div>
</div>

<#include "common/footer-script.ftl">
<script>
    $("#murongqing-login").on("click", function () {
        ajaxFunParam("/admin/user/ajax/login", $("#dataForm").serialize(), function (data) {
            msgFunCallBack(data.msg, function () {
                if (data.status == 0) {
                    location.href = '/admin/zhongyao/list';
                }
            })
        })
    })
</script>
</body>
</html>