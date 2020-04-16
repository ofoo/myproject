<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <#assign title="中药库">
    <#include "common/header-script.ftl">
<#--<link href="/bootstrap/css/admin-style.css" rel="stylesheet">-->
    <style>
        .layout-left {
            position: fixed;
            width: 60%;
            height: 100%;
            top: 0;
            left: 0;
            bottom: 0;
            overflow: auto;
        }

        .layout-middle {
            border-left: 1px solid #ccc;
            width: 15%;
            height: 100%;
            position: fixed;
            top: 0;
            left: 60%;
            bottom: 0;
            overflow: auto;
        }

        .layout-right-top {
            border-left: 1px solid #ccc;
            width: 25%;
            height: 10%;
            position: fixed;
            top: 0;
            right: 0;
            bottom: 0;
        }

        .layout-right-bottom {
            border-left: 1px solid #ccc;
            width: 25%;
            height: 90%;
            position: fixed;
            top: 10%;
            right: 0;
            bottom: 0;
            overflow: auto;
        }

        .layout-middle ul {
            margin: 0;
            list-style-type: none;
            padding: 0;
            margin: 0;
            font-size: 20px;
        }

        .layout-middle ul li {
            padding: 0 30px 0 30px;
            margin-top: 30px;
        }

        .card {
            margin-top: 50px;
            padding-bottom: 20px;
            border-bottom: 1px solid #ccc;
        }

        .card .col-md-2 {
            margin-top: 20px;
        }

        .card a {
            display: table-cell;
            width: 100px;
            height: 100px;
            text-align: center;
            vertical-align: middle;
            font-weight: bold;
            font-size: 20px;
            border: 1px solid;
        }
    </style>
</head>
<body>
<div class="layout-left">
    <div class="container-fluid">
        <#list categoryList as c>
            <div class="card" id="id${c.id}">
                <h1>${c.name}</h1>
                <div class="row">
                <#list c.zhongyaoList as zy>
                    <div class="col-md-2">
                        <a href="javascript:void(0)" onclick="sel(this)" data-id="${zy.id}">${zy.name}</a>
                    </div>
                </#list>
                </div>
            </div>
        </#list>
    </div>
</div>
<div class="layout-middle">
    <ul>
        <#list categoryList as c>
            <li>
                <a href="javascript:void(0)" onclick="selectCategory(${c.id})">${c.name}</a>
            </li>
        </#list>
    </ul>
</div>
<div class="layout-right-top">
    <div style="margin:10px;">
        <button class="btn btn-danger btn-block btn-lg" onclick="sendYaofang()">发送药方</button>
    </div>
</div>
<div class="layout-right-bottom">
    <div style="margin: 10px;max-height: 70vh;">
        <table class="table table-bordered" style="font-size: 20px">
            <thead>
            <tr>
                <th width="30%">名称</th>
                <th width="40%">重量（克）</th>
                <th width="30%">操作</th>
            </tr>
            </thead>
            <tbody style="overflow: auto;" id="data-content">

            </tbody>
        </table>
    </div>
</div>
<form id="dataForm">

</form>

<#include "common/footer-script.ftl">
<script>
    // 选择
    function sel(em) {
        var str = '';
        var str2 = '';
        openPrompt("输入重量（克）", function (value) {
            //移除重复中药
            var id = $(em).data("id");
            $("#tr" + id).remove();
            //添加中药
            var name = $(em).text();
            str += '<tr id="tr' + id + '">' +
                    '<td>' + name + '</td>' +
                    '<td><input type="number" class="form-control input-lg" value="' + value + '"></td>' +
                    '<td><a href="javascript:void(0)" onclick="deleteZhongyao(this,' + id + ')">删除</a></td>' +
                    '</tr>'
            $("#data-content").append(str);
            //from表单添加中药
            str2 += '<input type="hidden" id="n' + id + '" name="zyNames" value="' + name + '">' +
                    '<input type="hidden" id="d' + id + '" name="dosages" value="' + value + '">';
            $("#dataForm").append(str2);
        })
    }

    // 发送药方
    function sendYaofang() {
        ajaxFunParam('${rootPath}/admin/prescription/ajax/save', $("#dataForm").serialize(), function (data) {
            if (data.status == 0) {
                layer.alert('药方编号：' + data.data, {title: '发送成功'},function(index){
                    $("#dataForm").html('');
                    $("#data-content").html('');
                    layer.close(index);
                });
            } else {
                layer.alert('发送失败！');
            }
        })
    }

    //选择分类
    function selectCategory(id) {
        $(".card").hide();
        $("#id" + id).show();
        location.hash = "#id" + id;
    }

    //删除中药
    function deleteZhongyao(em,id) {
        $(em).parent().parent().remove();
        $("#n"+id).remove();
        $("#d"+id).remove();
    }
</script>
</body>
</html>