<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <#assign title="中药数据">
    <#include "common/header-script.ftl">
</head>
<body>
<div class="container-fluid">
    <p>${data}</p>
</div>

<#include "common/footer-script.ftl">
<script>
    // 保存任务
    $("#murongqing-save").on("click",function () {
        ajaxFunParam("/admin/zhongyao/ajax/save",$("#dataForm").serialize(),function(data){
            msgFun(data.msg)
        })
    })
</script>
</body>
</html>