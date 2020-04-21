<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <#assign title="${pageTitle}">
    <#include "../common/header-script.ftl">
</head>
<body>
<div class="container-fluid">
    <form class="form-horizontal data-form" id="dataForm">
        <input type="hidden" name="id" value="${(data.id)!}">
        <div class="form-group">
            <label class="col-md-2 control-label text-danger">人员姓名</label>
            <div class="col-md-5">
                <input name="name" type="text" class="form-control" id="name" value="${(data.name)!}"
                       placeholder="请输入人员姓名">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">人员标签</label>
            <div class="col-sm-3">
                <select class="form-control" id="memberLabelId" name="memberLabelId">
                <#list memberLabelList as item>
                    <option value="${item.id}"
                                <#if ((data.memberLabelId)!0)==item.id>selected</#if>>${item.name}</option>
                </#list>
                </select>
            </div>
            <div class="col-sm-2">
                <button type="button" class="btn btn-success" id="memberLabel-insert">添加</button>
            </div>
        </div>
        <div class="form-group">
            <label for="mobile" class="col-md-2 control-label text-danger">人员手机号</label>
            <div class="col-md-5">
                <input name="mobile" type="text" class="form-control" id="mobile" value="${(data.type)!}"
                       placeholder="请输入人员手机号">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="button" class="btn btn-primary" id="fengyulou-save">提交</button>
                <button type="reset" class="btn btn-primary">重置</button>
                <button type="button" class="btn btn-danger" id="fengyulou-close-refresh">关闭</button>
            </div>
        </div>
    </form>
</div>

<#include "../common/footer-script.ftl">
<script>
    // 保存任务
    $("#fengyulou-save").on("click", function () {
        ajaxFunParam("/admin/member/ajax/save", $("#dataForm").serialize(), function (data) {
            msgFun(data.msg)
        })
    })
</script>
</body>
</html>