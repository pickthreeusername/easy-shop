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
                <div class="col-xs-12">
                    <%--用户提示框--%>
                    <c:if test="${message != null}">
                        <div class="alert alert-success alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <i class="icon fa fa-check"></i> ${message}
                        </div>
                    </c:if>

                    <!-- 搜索框 -->
                    <div class="box box-info box-info-search" style="display: none;">
                        <div class="box-header">
                            <h3 class="box-title">高级搜索</h3>
                        </div>

                        <div class="box-body">
                            <form:form class="row form-horizontal" modelAttribute="tbUser" action="/user/search" method="get">
                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <label for="username" class="col-sm-4 control-label">姓名</label>

                                        <div class="col-sm-8">
                                            <form:input path="username" class="form-control" placeholder="姓名" />
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <label for="email" class="col-sm-4 control-label">邮箱</label>

                                        <div class="col-sm-8">
                                            <form:input path="email" class="form-control" placeholder="邮箱" />
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <label for="phone" class="col-sm-4 control-label">手机</label>

                                        <div class="col-sm-8">
                                            <form:input path="phone" class="form-control" placeholder="手机" />
                                        </div>
                                    </div>
                                </div>


                                <div class="col-xs-10 col-sm-2">
                                    <div class="form-group">
                                        <button type="submit" class="btn btn-info pull-right" onclick="search();">搜索</button>
                                    </div>
                                </div>


                            </form:form>
                        </div>

                        <%--<div class="box-footer">
                            <button type="button" class="btn btn-info pull-right" onclick="search();">搜索</button>
                        </div>--%>
                    </div>

                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">用户列表</h3>
                        </div>

                        <div class="box-body"><%--App.deleteMulti('/user/delete')--%>
                            <a href="/user/form" type="button" class="btn btn-sm btn-default"><i class="fa fa-plus"></i> 新增</a>&nbsp;&nbsp;&nbsp;
                            <button type="button" class="btn btn-sm btn-default" onclick="App.deleteMulti('/user/delete')"><i class="fa fa-trash-o"></i> 删除</button>&nbsp;&nbsp;&nbsp;
                            <a href="#" type="button" class="btn btn-sm btn-default"><i class="fa fa-download"></i> 导入</a>&nbsp;&nbsp;&nbsp;
                            <a href="#" type="button" class="btn btn-sm btn-default"><i class="fa fa-upload"></i> 导出</a>&nbsp;&nbsp;&nbsp;
                            <button type="button" class="btn btn-sm btn-primary" onclick="$('.box-info-search').css('display') == 'none' ? $('.box-info-search').show('fast') : $('.box-info-search').hide('fast')"><i class="fa fa-search"></i> 搜索</button>
                        </div>

                        <!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <table id="dataTable" class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>
                                            <label>
                                                <input type="checkbox" class="minimal icheck_master" />
                                            </label>
                                        </th>
                                        <th>ID</th>
                                        <th>用户名</th>
                                        <th>手机号</th>
                                        <th>邮箱</th>
                                        <th>更新时间</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                            <%--        <c:forEach items="${tbUsers}" var="user">
                                        <tr>
                                            <td>
                                                <label>
                                                    <input type="checkbox" class="minimal" id="${user.id}"/>
                                                </label>
                                            </td>
                                            <td>${user.id}</td>
                                            <td>${user.username}</td>
                                            <td>${user.phone}</td>
                                            <td>${user.email}</td>
                                            <td><fmt:formatDate value="${user.updated}" pattern="yyyy-MM-dd" /></td>
                                            <td>
                                                <button type="button" class="btn btn-sm btn-default" ><i class="fa fa-search"></i> 查看</button>&nbsp;&nbsp;&nbsp;
                                                <a href="/user/form?id=' + row.id + '" type="button" class="btn btn-sm btn-primary"><i class="fa fa-edit"></i> 编辑</a>&nbsp;&nbsp;&nbsp;
                                                <button type="button" class="btn btn-sm btn-danger" data-toggle="modal" data-target="#modal-danger"><i class="fa fa-trash-o"></i> 删除</button>
                                            </td>
                                        </tr>
                                    </c:forEach>--%>

                                </tbody>


                            </table>

                            <div class="modal modal-danger fade" id="modal-danger">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span></button>
                                            <h4 class="modal-title">Danger Modal</h4>
                                        </div>
                                        <div class="modal-body">
                                            <p>One fine body&hellip;</p>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-outline pull-left" data-dismiss="modal">Close</button>
                                            <button type="button" class="btn btn-outline">Save changes</button>
                                        </div>
                                    </div>
                                    <!-- /.modal-content -->
                                </div>
                                <!-- /.modal-dialog -->
                            </div>
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
<%-- 自定义模态框标签--%>
<sys:modal />
</body>
</html>
<script>
$(function () {
var columns = [
    {
        "data": function (row, type, val, meta) {
            return '<input id="' + row.id + '" type="checkbox" class="minimal" />';
        }
    },
    {"data": "id"},
    {"data": "username"},
    {"data": "phone"},
    {"data": "email"},
    {"data": "updated"},
    {
        "data": function (row, type, val, meta) {
            return '<a href="#" type="button" class="btn btn-sm btn-default"><i class="fa fa-search"></i> 查看</a>&nbsp;&nbsp;&nbsp;' +
                '<a href="/user/form?id=' + row.id + '" type="button" class="btn btn-sm btn-primary"><i class="fa fa-edit"></i> 编辑</a>&nbsp;&nbsp;&nbsp;' +
                '<a href="#" type="button" class="btn btn-sm btn-danger"><i class="fa fa-trash-o"></i> 删除</a>'
        }
    }
];
    App.initDataTables("/user/page", columns);
})


</script>