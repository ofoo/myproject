<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>代码显示</title>
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top">
        <#list list>
            <ul class="nav navbar-nav">
            <#items as file>
                <li <#if file.type=='Entity'>class="active"</#if> onclick="pointCode('${file.type}',this)"><a
                        href="javascript:void(0)">${file.name}</a></li>
            </#items>
            </ul>
        </#list>
</nav>
<div class="container">
    <#list list as file>
        <div class="row" id="${file.type}" style="padding-top: 70px">
            <div class="col-md-12">
                <h3>${file.name}</h3>
                <p>
                    <button type="button" class="btn btn-primary" data-id="1" data-type="${file.type}">复制代码</button>
                </p>
                <pre id="${file.type}Code">${file.content?html}</pre>
            </div>
        </div>
    </#list>
</div>

<script src="/assets/js/jquery-3.3.1.min.js"></script>
<script src="/assets/js/bootstrap.min.js"></script>
<script src="/assets/js/clipboard.min.js"></script>
<script src="/assets/layer/layer.js"></script>
<script>
    var clipboard = new ClipboardJS('.btn', {
        // alert($("#" + $(this).data("type") + "Code").html());
        text: function (trigger) {
            return $("#" + $(trigger).data("type") + "Code").text();
        }
    });
    clipboard.on('success', function (e) {
        // console.log(e);
        layer.msg("复制成功 ~~~", {icon: 6});
    });

    clipboard.on('error', function (e) {
        // console.log(e);
        layer.msg("复制失败 ~~~", {icon: 5});
    });

    // 定位代码
    function pointCode(index, em) {
        $("li[onclick^='pointCode']").removeClass("active");
        $(em).addClass("active");
        location.hash = "#" + index;
    }
</script>
</body>
</html>