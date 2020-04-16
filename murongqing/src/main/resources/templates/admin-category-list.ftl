<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <#assign title="类别库">
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
                    <input name="name" id="name" type="text" class="form-control" value="${(category.name)!}"
                           placeholder="中药类别">
                </div>
                <div class="col-xs-3">
                    <button type="button" class="btn btn-info" data-page="1" id="murongqing-search">查询</button>
                    <button type="button" class="btn btn-warning" id="murongqing-clear">清空</button>
                </div>
            </div>
        </form>
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group fun-btn" role="group" aria-category="Basic example">
                <button type="button" class="btn btn-success" id="murongqing-add">添加</button>
                <button type="button" class="btn btn-primary" id="murongqing-upd">修改</button>
                <button type="button" class="btn btn-danger" id="murongqing-del">删除</button>
            </div>
            <div class="btn-group fun-btn" role="group" aria-category="Basic example">
                <a class="btn btn-success" href="/admin/category/show" target="_blank">数据</a>
            </div>
        </div>
        <form id="dataForm"></form>
        <table class="table table-bordered table-hover">
            <thead>
            <tr>
                <th scope="row" width="2%"><input type="checkbox" class="checkall"></th>
                <th><strong>名称</strong></th>
            </tr>
            </thead>
            <tbody>
        <#list pageInfo.list as category>
        <tr>
            <th scope="row"><input type="checkbox" name="ids" value="${category.id}" class="checkbox"></th>
            <td id="name${category.id}">${(category.name)!}</td>
        </tr>
        </#list>
            </tbody>
        </table>
        <#include "common/layout-page.ftl">
    </div>
</div>

<#include "common/footer-script.ftl">
<script>
    // 删除类别
    $('#murongqing-del').on('click', function () {
        var length = $(".checkbox:checked").length;
        if (length == 0) {
            layer.msg("请选择类别");
            return;
        }
        ajaxFunParam('/admin/category/ajax/del', $("#dataForm").serialize(), function (data) {
            msgFunCallBack(data.msg, function () {
                if (data.status == 0) {
                    location.reload()
                }
            })
        })
    })

    // 添加类别
    $('#murongqing-add').on('click', function () {
        openPrompt("添加类别",function(text){
            ajaxFunParam("/admin/category/ajax/save",{"name":text},function(data){
                msgFunCallBack(data.msg, function () {
                    if (data.status == 0) {
                        location.reload()
                    }
                })
            })
        })
    })

    // 修改类别
    $('#murongqing-upd').on('click', function () {
        var length = $(".checkbox:checked").length;
        if (length == 0) {
            layer.msg("请选择类别");
            return;
        }
        var id = $(".checkbox:checked")[0].value;
        var value = $("#name"+id).text();
        openPromptValue("修改类别",value,function(text){
            ajaxFunParam("/admin/category/ajax/save",{"id":id,"name":text},function(data){
                msgFunCallBack(data.msg, function () {
                    if (data.status == 0) {
                        location.reload()
                    }
                })
            })
        })
    })

    // 清空查询条件
    $('#murongqing-clear').on('click', function () {
        $('#name').val('');
    })
</script>
</body>
</html>