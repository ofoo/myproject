<div class="layui-card" xmlns:th="http://www.w3.org/1999/xhtml">
    <div class="layui-card-body">
        <form class="layui-form">
            <input type="hidden" name="id" th:value="$${"{"}${entity?lower_case}==null}?'':$${"{"}${entity?lower_case}.id}">
            <#list attrList as attr>
            <div class="layui-form-item">
                <label class="layui-form-label">${attr.desc}</label>
                <div class="layui-input-block">
                    <input type="text" name="${attr.name}" lay-verify="required" autocomplete="off" placeholder="请输入${attr.desc}" th:value="$${"{"}${entity?lower_case}==null}?'':$${"{"}${entity?lower_case}.${attr.name}}"
                           class="layui-input">
                </div>
            </div>
            </#list>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button type="button" class="layui-btn" lay-submit lay-filter="*">提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    layui.form.on('submit(*)', function(data){
        layui.$.ajax({
            url: '${requestRootUrl}/ajax/save',
            type: 'post',
            data: JSON.stringify(data.field),
            dataType: 'json',
            contentType:"application/json",
            success: function (result) {
                if (result.success) {
                    layer.msg(result.msg,{time:1000},function () {
                        layer.closeAll();
                        tableReload();
                    })
                }else{
                    layer.msg(result.msg)
                }
            }
        });
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });
</script>