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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<!DOCTYPE html>
<html>
<head>
    <title>easy shop  | user</title>
    <jsp:include page="../includes/header.jsp"/>
    <link rel="stylesheet" href="/static/assets/plugins/treeTable/themes/vsStyle/treeTable.min.css"/>
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
                内容分类管理
                <small>Control panel</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="/main"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">内容分类管理</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <%--用户提示框--%>
                    <c:if test="${message != null}">
                        <div class="alert alert-success alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <i class="icon fa fa-check"></i> ${message}
                        </div>
                    </c:if>



                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">分类列表</h3>
                        </div>

                        <div class="box-body">
                            <a href="/content/category/form" type="button" class="btn btn-sm btn-default"><i class="fa fa-plus"></i> 新增</a>&nbsp;&nbsp;&nbsp;
                            <button type="button" class="btn btn-sm btn-default"><i class="fa fa-trash-o"></i> 删除</button>&nbsp;&nbsp;&nbsp;
                            <a href="#" type="button" class="btn btn-sm btn-default"><i class="fa fa-download"></i> 导入</a>&nbsp;&nbsp;&nbsp;
                            <a href="#" type="button" class="btn btn-sm btn-default"><i class="fa fa-upload"></i> 导出</a>&nbsp;&nbsp;&nbsp;
                            <button type="button" class="btn btn-sm btn-primary" onclick="$('.box-info-search').css('display') == 'none' ? $('.box-info-search').show('fast') : $('.box-info-search').hide('fast')"><i class="fa fa-search"></i> 搜索</button>                        </div>

                        <!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <table id="treeTable" class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>类别</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:if test="${categoryList != null}">
                                        <c:forEach items="${categoryList}" var="category">
                                            <tr id="${category.id}" pId="${category.parent.id}">
                                                <td>${category.id}</td>
                                                <td>${category.name}</td>
                                                <td>
                                                    <a href="/content/category/form?id=${category.id}" type="button" class="btn btn-sm btn-primary"><i class="fa fa-edit"></i> 编辑</a>&nbsp;&nbsp;&nbsp;
                                                    <button onclick="App.deleteData('/content/category/delete',${category.id}, '警告：该删除操作会将包括选中类目在内的全部子类目及属于类目的内容一并删除，请谨慎操作！您还确定继续吗？');" type="button" class="btn btn-sm btn-danger" ><i class="fa fa-trash-o"></i> 删除</button>
                                                    <a href='/content/category/form?parent.id=${category.id}&parent.name=${category.name}' type="button" class="btn btn-sm btn-default" ><i class="fa fa fa-plus"></i> 新增下级分类</a>
                                                </td>
                                            </tr>
                                        </c:forEach>

                                    </c:if>


                                </tbody>


                            </table>


                        </div>
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
<!-- treeTable -->
<script src="/static/assets/plugins/treeTable/jquery.treeTable.min.js"></script>
<%-- 自定义模态框标签--%>
<sys:modal />
</body>
</html>
<script>
    $(function () {
        $('#treeTable').treeTable({
            expandLevel:4,
            column:1
        })
    })
</script>

