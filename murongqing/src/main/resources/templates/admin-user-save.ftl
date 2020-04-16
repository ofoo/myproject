<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <#assign title="修改信息">
    <#include "common/header-script.ftl">
</head>
<body>
<div class="container-fluid">
    <div class="row data-form">
        <div class="col-md-offset-2 col-md-2">
            <img src="${(user.image)!"/img/default.jpg"}" class="img-thumbnail" id="imageUrl">
        </div>
        <div class="col-md-8">
            <form class="form-horizontal" id="dataForm">
                <input type="hidden" name="image" id="image" value="${(user.image)!"/img/default.jpg"}"">
                <div class="form-group">
                    <label for="name" class="col-md-2 control-label text-danger">用户名称</label>
                    <div class="col-md-5">
                        <input name="name" type="text" class="form-control" id="name" value="${(user.name)!}"
                               placeholder="名称">
                    </div>
                </div>
                <div class="form-group">
                    <label for="label" class="col-md-2 control-label text-danger">用户职位</label>
                    <div class="col-md-5">
                        <input name="position" type="text" class="form-control" id="position"
                               value="${(user.position)!}"
                               placeholder="职位">
                    </div>
                </div>
                <div class="form-group">
                    <label for="label" class="col-md-2 control-label text-muted">用户头像</label>
                    <div class="col-md-5">
                        <div id="uploader-demo">
                            <div id="fileList" class="uploader-list"></div>
                            <div id="filePicker">上传</div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-1 col-md-10">
                        <button type="button" class="btn btn-primary" id="murongqing-save">提交</button>
                        <button type="reset" class="btn btn-primary" id="murongqing-reset">重置</button>
                        <button type="button" class="btn btn-danger" id="murongqing-retreat">关闭</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<#include "common/footer-script.ftl">
<script>
    // 图片上传demo
    jQuery(function () {
        var $ = jQuery,
                $list = $('#fileList'),
                // 优化retina, 在retina下这个值是2
                ratio = window.devicePixelRatio || 1,

                // 缩略图大小
                thumbnailWidth = 100 * ratio,
                thumbnailHeight = 100 * ratio,

                // Web Uploader实例
                uploader;

        // 初始化Web Uploader
        var uploader = WebUploader.create({

            // 选完文件后，是否自动上传。
            auto: true,

            // swf文件路径
            swf: '/bootstrap/js/Uploader.swf',

            // 文件接收服务端。
            server: '/upload/file',

            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#filePicker',

            // 只允许选择图片文件。
            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/*'
            }
        });

        // 当有文件添加进来的时候
        uploader.on('fileQueued', function (file) {
            /*var $li = $(
                    '<div id="' + file.id + '" class="file-item thumbnail">' +
                    '<img>' +
                    '<div class="info">' + file.name + '</div>' +
                    '</div>'
                    ),
                    $img = $li.find('img');

            $list.append($li);

            // 创建缩略图
            uploader.makeThumb(file, function (error, src) {
                if (error) {
                    $img.replaceWith('<span>不能预览</span>');
                    return;
                }

                $img.attr('src', src);
            }, thumbnailWidth, thumbnailHeight);*/
        });

        // 文件上传过程中创建进度条实时显示。
        uploader.on('uploadProgress', function (file, percentage) {
            /*var $li = $('#' + file.id),
                    $percent = $li.find('.progress span');

            // 避免重复创建
            if (!$percent.length) {
                $percent = $('<p class="progress"><span></span></p>')
                        .appendTo($li)
                        .find('span');
            }

            $percent.css('width', percentage * 100 + '%');*/
            console.log(percentage * 100)
        });

        // 文件上传成功，给item添加成功class, 用样式标记上传成功。
        uploader.on('uploadSuccess', function (file, response) {
            $("#imageUrl").attr("src", response.url);
            $("#image").val(response.url);
            // $('#' + file.id).addClass('upload-state-done');
            console.log("上传成功")
        });

        // 文件上传失败，现实上传出错。
        uploader.on('uploadError', function (file) {
            /*var $li = $('#' + file.id),
                    $error = $li.find('div.error');

            // 避免重复创建
            if (!$error.length) {
                $error = $('<div class="error"></div>').appendTo($li);
            }

            $error.text('上传失败');*/
            console.log("上传失败")
        });

        // 完成上传完了，成功或者失败，先删除进度条。
        uploader.on('uploadComplete', function (file) {
            // $('#' + file.id).find('.progress').remove();
            console.log("上传完成")
        });
    });
    $("#murongqing-save").on("click", function () {
        ajaxFunParam('/admin/user/ajax/save', $("#dataForm").serialize(), function (data) {
            msgFun(data.msg)
        })
    })
</script>
</body>
</html>