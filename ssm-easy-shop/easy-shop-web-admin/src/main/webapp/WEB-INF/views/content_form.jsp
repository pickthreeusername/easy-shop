
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<!DOCTYPE html>
<html>
<head>
    <title>easy shop  | content_form</title>
    <jsp:include page="../includes/header.jsp"/>
    <link rel="stylesheet" href="/static/assets/plugins/jquery-ztree/css/zTreeStyle/zTreeStyle.min.css">
    <link rel="stylesheet" href="/static/assets/plugins/dropzone/min/dropzone.min.css">
    <link rel="stylesheet" href="/static/assets/plugins/dropzone/min/basic.min.css">
    <link rel="stylesheet" href="/static/assets/plugins/wangEditor/wangEditor.min.css">


</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <%-- head nav--%>
    <jsp:include page="../includes/nav.jsp"/>


    <!-- Left side column. contains the logo and sidebar -->
    <jsp:include page="../includes/menu.jsp"/>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                内容管理
                <small>Control panel</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="/main"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">内容管理</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-10">
                    <%--用户提示框--%>
                    <c:if test="${message != null}">
                        <div class="alert alert-warning alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <i class="icon fa fa-warning"></i>${message}
                        </div>
                    </c:if>


                    <div class="box box-info">
                        <div class="box-header with-border">
                            <h3 class="box-title">${tbContent.id == null?"新增":"编辑"}内容</h3>
                        </div>
                        <!-- /.box-header -->
                        <!-- form start -->
                        <form:form id="inputForm" cssClass="form-horizontal" action="/content/save" method="post" modelAttribute="tbContent">
                            <form:hidden path="id" ></form:hidden>
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="categoryName" class="col-sm-2 control-label ">所属类别</label>

                                    <div class="col-sm-10">
                                        <input  id="categoryName" placeholder="请选择" class="form-control  required" readonly="true" data-toggle="modal" data-target="#modal-default" />
                                        <form:hidden path="categoryId" />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="title" class="col-sm-2 control-label ">标题</label>

                                    <div class="col-sm-10">
                                        <form:input path="title"  placeholder="标题" class="form-control  required" />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="subTitle" class="col-sm-2 control-label ">副标题</label>

                                    <div class="col-sm-10">
                                        <form:input path="subTitle"  placeholder="副标题" class="form-control  required" />

                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="titleDesc" class="col-sm-2 control-label ">标题描述</label>

                                    <div class="col-sm-10">
                                        <form:input path="titleDesc"  placeholder="标题描述" class="form-control  required" />

                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="url" class="col-sm-2 control-label ">链接</label>

                                    <div class="col-sm-10">
                                        <form:input path="url"  placeholder="链接" class="form-control  required" />

                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="pic" class="col-sm-2 control-label ">图片1</label>

                                    <div class="col-sm-10">
                                        <form:input path="pic"  placeholder="图片1" class="form-control  required" />
                                        <div id="dropz" class="dropzone">

                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="pic2" class="col-sm-2 control-label ">图片2</label>

                                    <div class="col-sm-10">
                                        <form:input path="pic2"  placeholder="图片2" class="form-control  required" />
                                        <div id="dropz2" class="dropzone">

                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label  class="col-sm-2 control-label ">详情</label>

                                    <div class="col-sm-10">
                                        <form:hidden path="content"  class="form-control  required" />
                                        <div id="editor">${tbContent.content}</div>
                                    </div>
                                </div>

                            </div>


                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="button" class="btn btn-default" onclick="history.go(-1);">返回</button>
                                <button type="submit" class="btn btn-info pull-right" id="btn_formSubmit">提交</button>
                            </div>
                        </form:form>

                    </div>
                </div>
            </div>
        </section>
    </div>
        <sys:modal title="请选择"
                   message="<div class='zTreeDemoBackground left'>
		                        <ul id='myTree' class='ztree'></ul>
		                   </div>"/>

    <!-- /.content-wrapper -->

    <%-- copyright--%>
    <jsp:include page="../includes/copyright.jsp"/>
</div>

<%--footer--%>
<jsp:include page="../includes/footer.jsp"/>
<script src="/static/assets/plugins/jquery-ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script src="/static/assets/plugins/dropzone/dropzone.js"></script>
<script src="/static/assets/plugins/wangEditor/wangEditor.min.js"></script>

</body>
</html>
<script>

    $(function () {


        App.initzTree("/content/category/tree/data", ["id"], function (nodes) {
            var node = nodes[0];
            $("#categoryId").val(node.id);
            $("#categoryName").val(node.name);
            $("#modal-default").modal("hide");
        });

        var E = window.wangEditor;
        var editor = new E('#editor');
        //一些配制：图片上传路径， 文件名
        editor.customConfig.uploadImgServer="/upload/fileUpload";
        editor.customConfig.uploadFileName="editorFile";
        editor.create();

        $("#btn_formSubmit").bind("click", function () {
            var content = editor.txt.html()
            $("#content").val(content);

        });

    });
    App.initDropzone("#dropz",{
        url: "/upload/fileUpload",
        init: function () {
            this.on("success", function (file, data) {
                // 上传成功触发的事件
                $("#pic").val(data.fileName);
            });
        }
    });
    App.initDropzone("#dropz2",{
        url: "/upload/fileUpload",
        init: function () {
            this.on("success", function (file, data) {
                // 上传成功触发的事件
                $("#pic2").val(data.fileName);
            });
        }
    });
</script>
