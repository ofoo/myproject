<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <#assign title="用户库">
    <#include "common/header-script.ftl">
</head>
<body>
<div class="admin">
    <#include "common/layout-left.ftl">
    <div class="admin-right">
        <#include "common/layout-navtitle.ftl">
        <form class="search-from">
            <input type="hidden" name="pageNum" id="pageNum">
            <div class="row">
                <div class="col-md-3">
                    <input name="name" id="name" type="text" class="form-control" value="${(user.name)!}" placeholder="用户名称">
                </div>
                <div class="col-md-2">
                    <button type="button" class="btn btn-info" data-page="1" id="murongqing-search">查询</button>
                    <button type="button" class="btn btn-warning" id="murongqing-clear">清空</button>
                </div>
            </div>
        </form>
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th width="15%"><strong>用户名称</strong></th>
                    <th><strong>用户职位</strong></th>
                </tr>
                </thead>
                <tbody>
            <#list pageInfo.list as user>
            <tr>
                <td><span class="label label-success">${(user.name)!}</span></td>
                <td><span class="label label-danger">${(user.position)!"暂无"}</span></td>
            </tr>
            </#list>
                </tbody>
            </table>
        <#include "common/layout-page.ftl">
    </div>
</div>

<#include "common/footer-script.ftl">
<script>
    $('#murongqing-clear').on('click',function(){
        $('#name').val('');
    })
</script>
</body>
</html>