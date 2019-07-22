
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <title>easy shop  | user_form</title>
    <jsp:include page="../includes/header.jsp"/>
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
                用户管理
                <small>Control panel</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="/main"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">用户管理</li>
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
                            <h3 class="box-title">${tbUser.id == null?"新增":"编辑"}用户</h3>
                        </div>
                        <!-- /.box-header -->
                        <!-- form start -->
                        <form:form id="inputForm" cssClass="form-horizontal" action="/user/save" method="post" modelAttribute="tbUser">
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="email" class="col-sm-2 control-label ">用户邮箱</label>

                                    <div class="col-sm-10">
                                        <form:input path="email" placeholder="email" cssClass="form-control email required"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="Password" class="col-sm-2 control-label">登陆密码</label>

                                    <div class="col-sm-10">
                                        <form:input path="password" placeholder="Password" type="password" cssClass="form-control required"/>

                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="username" class="col-sm-2 control-label">用户姓名</label>

                                    <div class="col-sm-10">
                                        <form:input path="username" placeholder="Username" type="text" cssClass="form-control required"/>

                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="phone" class="col-sm-2 control-label">用户手机</label>

                                    <div class="col-sm-10">
                                        <form:input path="phone" placeholder="Phone" type="text" cssClass="form-control required mobile"/>

                                    </div>
                                </div>
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="button" class="btn btn-default" onclick="history.go(-1);">返回</button>
                                <button type="submit" class="btn btn-info pull-right">提交</button>
                            </div>
                        </form:form>

                    </div>
                </div>
            </div>
        </section>
    </div>

    <!-- /.content-wrapper -->

    <%-- copyright--%>
    <jsp:include page="../includes/copyright.jsp"/>
</div>

<%--footer--%>
<jsp:include page="../includes/footer.jsp"/>

</body>
</html>