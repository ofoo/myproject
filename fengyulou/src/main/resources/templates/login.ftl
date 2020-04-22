<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <#assign title="登录"/>
    <#include "common/header-script.ftl">
</head>
<body>
<div class="container" style="margin-top: 100px">
    <div class="col-md-offset-4 col-md-4">
        <form id="dataForm" class="well">
            <div class="page-header" style="margin-top: 0px">
                <h1 class="text-center" style="margin: 0px">风雨楼</h1>
            </div>
            <div class="form-group">
                <input type="text" name="loginName" class="form-control" placeholder="账号" value="guochao">
            </div>
            <div class="form-group">
                <input type="password" name="password" class="form-control" placeholder="密码" value="123">
            </div>
            <div class="form-group">
                <button id="fengyulou-login" type="button" class="btn btn-primary btn-block">登录</button>
            </div>
        </form>
    </div>
</div>

<#include "common/footer-script.ftl">
<script>
    $(function () {
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