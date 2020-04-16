<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <#assign title="药方库">
    <#include "common/header-script.ftl">
    <link href="/bootstrap/css/admin-style.css" rel="stylesheet">
</head>
<body>
<div class="admin">
    <#include "common/layout-left.ftl">
    <div class="admin-right">
        <#include "common/layout-navtitle.ftl">
        <form class="search-from">
            <input type="hidden" name="pageNum" id="pageNum">
            <div class="row">
                <div class="col-xs-3">
                    <input name="name" id="name" type="text" class="form-control" value="${(prescription.name)!}"
                           placeholder="药方名称">
                </div>
                <div class="col-xs-3">
                    <button type="button" class="btn btn-info" data-page="1" id="murongqing-search">查询</button>
                    <button type="button" class="btn btn-warning" id="murongqing-clear">清空</button>
                </div>
            </div>
        </form>
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group fun-btn" role="group" aria-prescription="Basic example">
                <button type="button" class="btn btn-primary" id="murongqing-upd">修改</button>
                <button type="button" class="btn btn-danger" id="murongqing-del">删除</button>
            </div>
        </div>
        <form id="dataForm"></form>
        <table class="table table-bordered table-hover">
            <thead>
            <tr>
                <th scope="row" width="2%"><input type="checkbox" class="checkall"></th>
                <th width="20%"><strong>药方编号</strong></th>
                <th><strong>药方名称</strong></th>
            </tr>
            </thead>
            <tbody>
        <#list pageInfo.list as prescription>
        <tr>
            <th scope="row"><input type="checkbox" name="ids" value="${prescription.id}" class="checkbox"></th>
            <td>${(prescription.number)!}</td>
            <td>${(prescription.name)!}</td>
        </tr>
        </#list>
            </tbody>
        </table>
        <#include "common/layout-page.ftl">
    </div>
</div>

<#include "common/footer-script.ftl">
<script>
    // 删除药方
    $('#murongqing-del').on('click', function () {
        var length = $(".checkbox:checked").length;
        if (length == 0) {
            layer.msg("请选择药方");
            return;
        }
        ajaxFunParam('/admin/prescription/ajax/del', $("#dataForm").serialize(), function (data) {
            msgFunCallBack(data.msg, function () {
                if (data.status == 0) {
                    location.reload()
                }
            })
        })
    })

    //修改
    $("#murongqing-upd").on('click', function () {
        var length = $(".checkbox:checked").length;
        if (length == 0) {
            layer.msg("请选择药方");
            return;
        }
        var id = $(".checkbox:checked")[0].value;
        openPage("/admin/prescription/update?id=" + id);
    })

    // 清空查询条件
    $('#murongqing-clear').on('click', function () {
        $('#name').val('');
    })
</script>
</body>
</html>