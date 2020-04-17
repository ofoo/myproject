<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <#assign title="药方列表">
    <#include "common/header-script.ftl">
</head>
<body>
<table class="table table-striped table-bordered">
    <#list pageInfo.list as p>
    <tr onclick="location.href='/pinfo/${p.id}'">
        <td class="text-center"><strong>${p.number}</strong></td>
        <td class="text-center text-danger">
            <#if p.status==1>
                <strong class="text-success">已处理</strong>
            <#else>
                <strong class="text-danger">未处理</strong>
            </#if>
        </td>
    </tr>
    </#list>
</table>

<#include "common/footer-script.ftl">
</body>
</html>