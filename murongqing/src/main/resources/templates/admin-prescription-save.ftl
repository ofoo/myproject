<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <#if (prescription.id)??>
        <#assign title="修改药方">
    <#else>
        <#assign title="添加药方">
    </#if>
    <#include "common/header-script.ftl">
</head>
<body>
<div class="container">
    <form class="form-horizontal" id="dataForm">
        <input type="hidden" name="id" value="${(prescription.id)!}">
        <div class="form-group mb40">
            <h1 class="col-md-2 control-label">${title}</h1>
        </div>
        <div class="form-group">
            <label for="sketch" class="col-md-2 control-label text-danger">名称</label>
            <div class="col-md-5">
                <input name="name" type="text" class="form-control" id="name" value="${(prescription.name)!}"
                       placeholder="药方名称">
            </div>
        </div>
        <#--<div class="form-group">
            <label for="sketch" class="col-md-2 control-label text-danger">简介</label>
            <div class="col-md-5">
                <input name="intro" type="text" class="form-control" id="intro" value="${(prescription.intro)!}" placeholder="药方简介">
            </div>
        </div>-->
        <div class="form-group">
            <div class="col-md-5 col-md-offset-2">
                <table class="table table-bordered mb0">
                    <#list prescription.pzList as pz>
                    <tr>
                        <td>${pz.name}</td>
                        <td>${pz.dosage}</td>
                    </tr>
                    </#list>
                    <#--<tr>
                        <td>123</td>
                        <td>
                            <div class="input-group">
                                <input type="text" class="form-control input-sm">
                                <span class="input-group-addon" id="basic-addon1">g</span>
                            </div>
                        </td>
                        <td>
                            <button type="button" class="btn btn-danger btn-sm">删除</button>
                        </td>
                    </tr>-->
                </table>
            </div>
        </div>
        <#--<div class="form-group">
            <div class="col-md-5 col-md-offset-2">
                <button type="button" class="btn btn-success" id="murongqing-select">选择药材</button>
            </div>
        </div>-->
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="button" class="btn btn-primary" id="murongqing-save">提交</button>
                <button type="reset" class="btn btn-primary" id="murongqing-reset">重置</button>
                <button type="button" class="btn btn-danger" id="murongqing-close-refresh">关闭</button>
            </div>
        </div>
    </form>
</div>

<#include "common/footer-script.ftl">
<script>
    // 保存任务
    $("#murongqing-save").on("click", function () {
        ajaxFunParam("/admin/prescription/ajax/update", $("#dataForm").serialize(), function (data) {
            msgFun(data.msg)
        })
    })

    // 选择药材
    $("#murongqing-select").on('click',function(){
        openPage('/admin/zhongyao/select');
    })
</script>
</body>
</html>