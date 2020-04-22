$(function () {
    // 左侧导航选中
    $("[href='" + location.pathname + "']").addClass('active');
    // 取消文本输入框自动补全功能
    $("input").attr("autocomplete", "off");
    // 关闭页面，并刷新
    $("#fengyulou-close-refresh").on("click", function () {
        closePageRefresh();
    })
    // 关闭页面
    $("#fengyulou-close").on("click", function () {
        closePage();
    })
    // 后退页面
    $("#fengyulou-retreat").on("click", function () {
        history.back()
    })
    // 修改用户密码
    $("#fengyulou-pwd").on("click", function () {
        updatePwd()
    })
})

/**
 * 删除方法，带参数
 * @param url
 * @param param
 */
function delFun(url, param, callBack) {
    layer.confirm('是否删除？', {
        btn: ['是', '否'] //按钮
    }, function () {
        ajaxFunParam(url, param, callBack)
    });
}

$('#fengyulou-search').on('click', function () {
    $('#pageNum').val($(this).data("page"))
    $('.search-from').submit()
})

$('.radio').iCheck({
    radioClass: 'iradio_square-blue',
})

$('.checkall,.checkbox').iCheck({
    checkboxClass: 'icheckbox_square-blue',
})

// 全选
$('.checkall').on('ifClicked', function (event) {
    if (!event.target.checked) {
        $(".checkbox").iCheck('check');
    } else {
        $(".checkbox").iCheck('uncheck');
    }
});
$('.checkbox').on('ifChecked', function (event) {
    var len = $('.checkbox').length;
    var checkLen = $('.checkbox:checked').length;
    if (len == checkLen) {
        $(".checkall").iCheck('check');
    }
    var val = $(this).val();
    var input = '<input type="hidden" id="cb' + val + '" value="' + val + '" name="ids">';
    $('#dataForm').append(input);
});

// 取消全选
$('.checkbox').on('ifUnchecked', function (event) {
    $(".checkall").iCheck('uncheck');
    var val = $(this).val();
    $('#cb' + val).remove();
});

/**
 * 打开页面
 * @param url
 */
function openPage(url) {
    layer.open({
        type: 2,
        area: ['100%', '100%'],
        fixed: false, //不固定
        scrollbar: false,
        title: false,
        closeBtn: false,
        shadeClose: true,
        content: url,
    })
}

/**
 * 打开页面，带关闭按钮
 * @param url
 */
function openPageClose(url) {
    layer.open({
        type: 2,
        area: ['100%', '100%'],
        fixed: false, //不固定
        scrollbar: false,
        title: false,
        closeBtn: false,
        shadeClose: true,
        content: url,
        btn: ['关闭'], //可以无限个按钮
        yes: function () {
            layer.closeAll()
        }
    })
}

/**
 * 修改密码
 */
function updatePwd() {
    layer.prompt({title: "修改密码", formType: 1, maxlength: 255}, function (pass, index) {
        layer.close(index);
        ajaxFunParam("/fyl/user/ajaxUpdatePwd", {"pwd": pass}, function (data) {
            msgFunCallBack(data.msg, function () {
                if (data.status == 0) {
                    location.href = "/"
                }
            });
        })
    });
}

/**
 * 输入框
 * @param title
 * @param url
 * @param callBack
 */
function openPrompt(title, callBack) {
    layer.prompt({title: title, maxlength: 255}, function (pass, index) {
        layer.close(index)
        callBack(pass);
    })
}

/**
 * 输入框，带值
 * @param title
 * @param url
 * @param callBack
 */
function openPromptValue(title, value, callBack) {
    layer.prompt({title: title, maxlength: 255, value: value}, function (pass, index) {
        layer.close(index)
        callBack(pass);
    })
}

/**
 * 关闭页面，并刷新
 */
function closePageRefresh() {
    parent.location.reload()
}

/**
 * 关闭页面
 */
function closePage() {
    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    console.log(index)
    parent.layer.close(index); //再执行关闭
}

/**
 * ajax，有参
 * @param url
 * @param data
 * @param callBack
 */
function ajaxFunParam(url, data, callBack) {
    $.ajax({
        url: url,
        type: "post",
        data: data,
        dataType: "json",
        success: callBack,
        error: function () {
            console.log('操作失败')
        }
    });
}

/**
 * ajax，无参
 * @param url
 * @param data
 * @param callBack
 */
function ajaxFun(url, callBack) {
    $.ajax({
        url: url,
        type: "post",
        dataType: "json",
        success: callBack,
        error: function () {
            console.log('操作失败')
        }
    });
}

/**
 * ajax，有参
 * @param url
 * @param data
 * @param callBack
 */
function ajaxFunParamText(url, data, callBack) {
    $.ajax({
        url: url,
        type: "post",
        data: data,
        dataType: "text",
        success: callBack,
        error: function () {
            console.log('操作失败')
        }
    });
}

/**
 * ajax，无参
 * @param url
 * @param data
 * @param callBack
 */
function ajaxFunText(url, callBack) {
    $.ajax({
        url: url,
        type: "post",
        dataType: "text",
        success: callBack,
        error: function () {
            console.log('操作失败')
        }
    });
}

/**
 * 提示,有回调
 * @param msg
 * @param callBack
 */
function msgFunCallBack(msg, callBack) {
    layer.msg(msg, {
        time: 1000
    }, callBack)
}

/**
 * 提示，无回调
 * @param msg
 */
function msgFun(msg) {
    layer.msg(msg, {
        time: 1000
    })
}

/**
 * 检查是否选择数据
 * @param msg
 */
function checkSelect(msg) {
    var length = $(".checkbox:checked").length;
    if (length == 0) {
        msgFun(msg);
        return false;
    }
    return true;
}