<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <#assign title="中药库">
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
                    <input name="name" id="name" type="text" class="form-control" value="${(zhongyao.name)!}"
                           placeholder="中药名称">
                </div>
                <div class="col-xs-3">
                    <button type="button" class="btn btn-info" data-page="1" id="murongqing-search">查询</button>
                    <button type="button" class="btn btn-warning" id="murongqing-clear">清空</button>
                </div>
            </div>
        </form>
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group fun-btn" role="group" aria-zhongyao="Basic example">
                <button type="button" class="btn btn-success" id="murongqing-add">添加</button>
                <button type="button" class="btn btn-primary" id="murongqing-upd">修改</button>
                <button type="button" class="btn btn-danger" id="murongqing-del">删除</button>
            </div>
            <div class="btn-group fun-btn" role="group" aria-zhongyao="Basic example">
                <a class="btn btn-success" href="/admin/zhongyao/show" target="_blank">数据</a>
            </div>
        </div>
        <form id="dataForm"></form>
        <table class="table table-bordered table-hover">
            <thead>
            <tr>
                <th scope="row" width="2%"><input type="checkbox" class="checkall"></th>
                <th><strong>名称</strong></th>
                <th><strong>行号</strong></th>
                <th><strong>列号</strong></th>
                <th><strong>价格（<span class="text-danger">1000</span>克）</strong></th>
                <th><strong>重量（克）</strong></th>
                <th><strong>图片</strong></th>
            </tr>
            </thead>
            <tbody>
        <#list pageInfo.list as zhongyao>
        <tr>
            <th scope="row"><input type="checkbox" name="ids" value="${zhongyao.id}" class="checkbox"></th>
            <td>${(zhongyao.name)!}</td>
            <td>${(zhongyao.x)!}</td>
            <td>${(zhongyao.y)!}</td>
            <td>
                <#if (zhongyao.price)?? && (zhongyao.price)!=''>
                    <strong class="text-danger">${(zhongyao.price)!}</strong>元
                </#if>
            </td>
            <td>${(zhongyao.weight)!}</td>
            <td>
                <#if ((zhongyao.image)!"")?length gt 1>
                    <a target="_blank" href="${(zhongyao.image)}">查看</a>
                <#else>
                    暂无
                </#if>
            </td>
        </tr>
        </#list>
            </tbody>
        </table>
        <#include "common/layout-page.ftl">
    </div>
</div>

<#include "common/footer-script.ftl">
<script>
    // 删除中药
    $('#murongqing-del').on('click', function () {
        var length = $(".checkbox:checked").length;
        if (length == 0) {
            layer.msg("请选择中药");
            return;
        }
        ajaxFunParam('/admin/zhongyao/ajax/del', $("#dataForm").serialize(), function (data) {
            msgFunCallBack(data.msg, function () {
                if (data.status == 0) {
                    location.reload()
                }
            })
        })
    })

    // 添加中药
    $('#murongqing-add').on('click', function () {
        openPage('/admin/zhongyao/save');
    })

    // 修改中药
    $('#murongqing-upd').on('click', function () {
        var length = $(".checkbox:checked").length;
        if (length == 0) {
            layer.msg("请选择中药");
            return;
        }
        var id = $(".checkbox:checked")[0].value;
        openPage('/admin/zhongyao/save?id=' + id);
    })

    // 清空查询条件
    $('#murongqing-clear').on('click', function () {
        $('#name').val('');
    })
</script>
</body>
</html>