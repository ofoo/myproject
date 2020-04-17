<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <#if (zhongyao.id)??>
        <#assign title="修改中药">
    <#else>
        <#assign title="添加中药">
    </#if>
    <#include "common/header-script.ftl">
</head>
<body>
<div class="container">
    <form class="form-horizontal" id="dataForm">
        <input type="hidden" name="id" value="${(zhongyao.id)!}">
        <div class="form-group mb40">
            <h1 class="col-md-2 control-label">${title}</h1>
        </div>
        <div class="form-group">
            <label for="sketch" class="col-md-2 control-label text-danger">名称</label>
            <div class="col-md-5">
                <input name="name" type="text" class="form-control" id="name" value="${(zhongyao.name)!}"
                       placeholder="中药名称">
            </div>
        </div>
        <div class="form-group">
            <label for="sketch" class="col-md-2 control-label text-danger">行号</label>
            <div class="col-md-5">
                <input name="x" type="text" class="form-control" id="x" value="${(zhongyao.x)!}" placeholder="中药行数">
            </div>
        </div>
        <div class="form-group">
            <label for="sketch" class="col-md-2 control-label text-danger">列号</label>
            <div class="col-md-5">
                <input name="y" type="text" class="form-control" id="y" value="${(zhongyao.y)!}" placeholder="中药列数">
            </div>
        </div>
        <div class="form-group">
            <label for="sketch" class="col-md-2 control-label text-danger">价格</label>
            <div class="col-md-5">
                <input name="price" type="text" class="form-control" id="y" value="${(zhongyao.price)!}"
                       placeholder="中药价格">
            </div>
        </div>
        <div class="form-group">
            <label for="sketch" class="col-md-2 control-label text-danger">重量</label>
            <div class="col-md-5">
                <input name="weight" type="text" class="form-control" id="y" value="${(zhongyao.weight)!}"
                       placeholder="总重量">
            </div>
        </div>
        <div class="form-group">
            <label for="sketch" class="col-md-2 control-label text-danger">图片</label>
            <div class="col-md-5">
                <input name="image" type="text" class="form-control" id="image" value="${(zhongyao.image)!}"
                       placeholder="中药图片">
            </div>
        </div>
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
        ajaxFunParam("/admin/zhongyao/ajax/save", $("#dataForm").serialize(), function (data) {
            msgFun(data.msg)
        })
    })
</script>
</body>
</html>