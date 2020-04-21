<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <#assign title="登录"/>
    <#include "common/header-script.ftl">
</head>
<body>
<form id="dataForm" class="login">
    <div class="page-header">
        <h1 class="text-center">风雨楼</h1>
    </div>
    <div class="form-group">
        <input type="text" name="loginName" class="form-control input-lg" placeholder="账号" value="guochao">
    </div>
    <div class="form-group">
        <input type="password" name="password" class="form-control input-lg" placeholder="密码" value="123">
    </div>
    <button id="fengyulou-login" type="button" class="btn btn-primary btn-lg btn-block">登录</button>
</form>

<#include "common/footer-script.ftl">
<script>
    $(function(){
        $("#fengyulou-login").on("click", function () {
            ajaxFunParam("/fyl/user/ajaxLogin", $("#dataForm").serialize(), function (data) {
                msgFunCallBack(data.msg, function () {
                    if (data.status == 0) {
                        location.href = '/fyl/task/list/page';
                    }
                })
            })
        })
    })
</script>
</body>
</html>