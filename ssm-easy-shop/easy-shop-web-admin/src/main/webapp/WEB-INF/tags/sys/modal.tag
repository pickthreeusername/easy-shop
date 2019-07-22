<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="title" type="java.lang.String" required="false"  description="模态框标题" %>
<%@ attribute name="message" type="java.lang.String" required="false"  description="模态框内容" %>
<%@ attribute name="opts" type="java.lang.String" required="false"  description="操作：info：提示，confirm：确认删除" %>
<%@ attribute name="url" type="java.lang.String" required="false"  description="确认删除时跳转地址" %>

<div class="modal fade" id="modal-default">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">${title == null? "温馨提示":title}</h4>
            </div>
            <div class="modal-body">
                <p>${message}&hellip;</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="btn_modal_confirm">确定</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<script>
    $(function () {
        $("#btn_modal_confirm").bind("click",function () {
            <c:if test='${opts == null or opts == "info"}'>
                $("#modal-default").modal("hide");
            </c:if>
            <c:if test='${opts == null or opts == "confirm"}'>
                console.log("${url}");
            </c:if>
        })
    })
</script>