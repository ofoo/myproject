<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <#assign title="查看药方">
    <#include "common/header-script.ftl">
</head>
<body>
<div class="container">
    <div class="mt20 clearfix">
        <p style="font-size: 20px" class="pull-left">菊花</p>
        <p style="font-size: 20px" class="pull-right text-danger">10g</p>
    </div>
    <hr>
    <div class="mt20 clearfix">
        <p style="font-size: 20px" class="pull-left">八月扎</p>
        <p style="font-size: 20px" class="pull-right text-danger">10g</p>
    </div>
    <hr>
    <div class="mt20 clearfix">
        <p style="font-size: 20px" class="pull-left">金银花</p>
        <p style="font-size: 20px" class="pull-right text-danger">10g</p>
    </div>
    <hr>
</div>

<#include "common/footer-script.ftl">
<script>
    // 保存任务
    $("#murongqing-save").on("click", function () {
        ajaxFunParam("/admin/prescription/ajax/save", $("#dataForm").serialize(), function (data) {
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