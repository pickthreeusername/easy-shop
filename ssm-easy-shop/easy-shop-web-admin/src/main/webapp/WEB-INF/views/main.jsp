<%--
  Created by IntelliJ IDEA.
  User: yucheng.chen
  Date: 2019/7/18
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>easy shop  | main</title>
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
                    控制面板
                    <small>Control panel</small>
                </h1>
                <ol class="breadcrumb">
                    <li><a href="/main"><i class="fa fa-dashboard"></i> 首页</a></li>
                    <li class="active">控制面板</li>
                </ol>
            </section>

            <!-- Main content -->
            <section class="content">

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
