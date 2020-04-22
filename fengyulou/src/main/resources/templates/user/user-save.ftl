<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <#assign title="编辑任务">
    <#include "../common/header-script.ftl">
</head>
<body>
<div class="container-fluid">
    <form class="form-horizontal data-form" id="dataForm">
        <input type="hidden" name="id" value="${(task.id)!}">
        <div class="form-group">
            <label for="sketch" class="col-md-2 control-label text-danger">任务简述</label>
            <div class="col-md-5">
                <textarea name="sketch" class="form-control" rows="3" id="sketch" placeholder="任务简述">${(task.sketch)!}</textarea>
            </div>
        </div>
        <div class="form-group">
            <label for="type" class="col-md-2 control-label text-danger">任务类型</label>
            <div class="col-md-5">
                <input name="type" type="text" class="form-control" id="type" value="${(task.type)!}" placeholder="任务类型">
            </div>
        </div>
        <div class="form-group">
            <label for="status" class="col-sm-2 control-label text-danger">任务状态</label>
            <div class="col-sm-5">
                <select class="form-control" id="status" name="status">
                    <option value="1" <#if ((task.status)!0)==1>selected</#if>>未完成</option>
                    <option value="2" <#if ((task.status)!0)==2>selected</#if>>已完成</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="project" class="col-md-2 control-label text-danger">项目名称</label>
            <div class="col-md-5">
                <input name="project" type="text" class="form-control" id="project" value="${(task.project)!}" placeholder="任务类型">
            </div>
        </div>
        <div class="form-group">
            <label for="userName" class="col-sm-2 control-label text-danger">负责用户</label>
            <div class="col-sm-3">
                <input class="hide" name="userId" type="text" id="userId" value="${(task.userId)!}">
                <input readonly type="text" class="form-control fengyulou-select" id="userName" value="${(task.userName)!}" placeholder="点击选择">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="button" class="btn btn-primary" id="fengyulou-save">提交</button>
                <button type="reset" class="btn btn-primary" id="fengyulou-reset">重置</button>
                <button type="button" class="btn btn-danger" id="fengyulou-close-refresh">关闭</button>
            </div>
        </div>
    </form>
</div>

<#include "../common/footer-script.ftl">
<script>
    // 保存任务
    $("#fengyulou-save").on("click",function () {
        ajaxFunParam("/fyl/task/ajax/save",$("#dataForm").serialize(),function(data){
            msgFun(data.msg)
        })
    })

    // 选择用户页面
    $(".fengyulou-select").on("click",function(){
        openPage('/fyl/user/list/select');
    })

    // 设置用户信息
    function setUserData(userId,userName){
        $("#userId").val(userId);
        $("#userName").val(userName);
    }
</script>
</body>
</html>