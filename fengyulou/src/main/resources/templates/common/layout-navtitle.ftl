<div class="page-header">
    <h1>${title}
        <#--<small>Subtext for header</small>-->
    </h1>
    <!-- Single button -->
    <div class="btn-group exit">
        <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"
                aria-expanded="false">
        <#--${loginUser.name}-->风雨楼 <span class="caret"></span>
        </button>
        <ul class="dropdown-menu dropdown-menu-right">
            <li><a href="/admin/user/save">修改信息</a></li>
            <li><a href="javascript:void(0)" id="fengyulou-pwd">修改密码</a></li>
            <li><a href="/admin/user/logout">退出</a></li>
        </ul>
    </div>
</div>