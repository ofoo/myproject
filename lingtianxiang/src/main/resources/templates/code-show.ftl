<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>代码显示</title>
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <ul class="nav navbar-nav">
            <li><a href="#Entity">Entity</a></li>
            <li><a href="#EntityCondition">EntityCondition</a></li>
            <li><a href="#Dao">Dao</a></li>
            <li><a href="#Biz">Biz</a></li>
            <li><a href="#Controller">Controller</a></li>
            <li><a href="#Service">Service</a></li>
        </ul>
    </div>
</nav>
<div class="container">
    <#list list as file>
    <div class="row" id="${file.type}" style="padding-top: 70px">
        <div class="col-md-12">
            <h3>${file.name}.java</h3>
            <p>
                <button type="button" class="btn btn-primary" data-id="1" data-type="${file.type}">复制代码</button>
            </p>
            <pre id="${file.type}Code">${file.content}</pre>
        </div>
    </div>
    </#list>
</div>

<script src="/assets/js/jquery-3.3.1.min.js"></script>
<script src="/assets/js/bootstrap.min.js"></script>
<script src="/assets/js/clipboard.min.js"></script>
<script>
    var clipboard = new ClipboardJS('.btn', {
        // alert($("#" + $(this).data("type") + "Code").html());
        text: function(trigger) {
            return $("#" + $(trigger).data("type") + "Code").text();
        }
    });
    clipboard.on('success', function(e) {
        console.log(e);
    });

    clipboard.on('error', function(e) {
        console.log(e);
    });
</script>
</body>
</html>