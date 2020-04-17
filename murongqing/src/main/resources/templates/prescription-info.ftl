<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <#assign title="药方详情">
    <#include "common/header-script.ftl">
</head>
<body>
<div class="container-fluid" style="padding-top: 10px;padding-bottom: 10px">
    <div class="row text-center" style="font-size: 18px">
        <div class="col-xs-2">
            <a href="/plist">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            </a>
        </div>
        <div class="col-xs-8">
            <strong>${p.number}</strong>
        </div>
        <div class="col-xs-2">
            <#if p.status==0>
                <a href="${rootPath}/pu/status/${p.id}">
                    <span class="glyphicon glyphicon-ok text-success" aria-hidden="true"></span>
                </a>
            </#if>
        </div>
    </div>
</div>
<table class="table table-striped table-bordered">
    <#list p.pzList as pz>
        <tr>
            <td class="text-center"><strong>${pz.name}</strong></td>
            <td class="text-center text-danger"><strong>${pz.dosage}克</strong></td>
        </tr>
    </#list>
</table>

<#include "common/footer-script.ftl">
</body>
</html>