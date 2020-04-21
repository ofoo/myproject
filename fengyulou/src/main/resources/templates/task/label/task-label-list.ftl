<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <#assign title="任务标签">
    <#include "../../common/header-script.ftl">
</head>
<body>
<div class="admin">
    <#include "../../common/layout-left.ftl">
    <div class="admin-right">
        <#include "../../common/layout-navtitle.ftl">
        <form class="search-from">
            <input type="hidden" name="pageNum" id="pageNum">
            <div class="row">
                <div class="col-md-3">
                    <input name="name" id="name" type="text" class="form-control" value="${(data.name)!}"
                           placeholder="任务标签名称">
                </div>
                <div class="col-md-2">
                    <button type="button" class="btn btn-info" data-page="1" id="fengyulou-search">查询</button>
                    <button type="reset" class="btn btn-warning">清空</button>
                </div>
            </div>
        </form>
        <div class="fun-btn btn-group" role="group" aria-label="Basic example">
            <button type="button" class="btn btn-success" id="fengyulou-insert">添加</button>
            <button type="button" class="btn btn-primary" id="fengyulou-update">修改</button>
            <button type="button" class="btn btn-danger" id="fengyulou-delete">删除</button>
        </div>
        <form id="dataForm"></form>
        <table class="table table-bordered table-hover">
            <thead>
            <tr>
                <th><strong>任务标签名称</strong></th>
            </tr>
            </thead>
            <tbody>
            <#list pageInfo.list as data>
            <tr>
                <td>${(data.name)!}</td>
            </tr>
            </#list>
            </tbody>
        </table>
        <#include "../../common/layout-page.ftl">
    </div>
</div>

<#include "../../common/footer-script.ftl">
<script>
    $(function(){
        // 添加
        $('#fengyulou-insert').on('click', function () {
            openPage('/fyl/taskLabel/insert')
        })
        // 修改
        $('#fengyulou-update').on('click', function () {
            if (!checkSelect("请选择数据")) {
                return;
            }
            var id = $(".checkbox:checked")[0].value;
            openPage('/fyl/taskLabel/update?id=' + id)
        })
        // 删除
        $('#fengyulou-delete').on('click', function () {
            if (!checkSelect("请选择数据")) {
                return;
            }
            delFun('/fyl/taskLabel/ajax/delete', $("#dataForm").serialize(), function (data) {
                msgFunCallBack(data.msg,function(){
                    if (data.status == 0) {
                        location.reload()
                    }
                })
            })
        })
    })
</script>
</body>
</html>