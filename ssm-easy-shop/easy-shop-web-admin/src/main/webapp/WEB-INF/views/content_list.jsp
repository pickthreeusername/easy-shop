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
    <title>easy shop  | content</title>
    <jsp:include page="../includes/header.jsp"/>
    <style type="text/css">
        /*.colWidth{
            width:40px;
            border:1px solid red;
            overflow:hidden;
        }*/
    </style>
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

                        <div class="box-body form-horizontal row">
                            <div class="col-xs-12 col-sm-3">
                                <div class="form-group">
                                    <label for="title" class="col-sm-4 control-label">标题</label>

                                    <div class="col-sm-8">
                                        <input id="title" class="form-control" placeholder="标题" />
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-3">
                                <div class="form-group">
                                    <label for="subTitle" class="col-sm-4 control-label">副标题</label>

                                    <div class="col-sm-8">
                                        <input id="subTitle" class="form-control" placeholder="副标题" />
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-3">
                                <div class="form-group">
                                    <label for="titleDesc" class="col-sm-4 control-label">标题描述</label>

                                    <div class="col-sm-8">
                                        <input id="titleDesc" class="form-control" placeholder="标题描述" />

                                    </div>
                                </div>
                            </div>


                            <div class="col-xs-10 col-sm-2">
                                <div class="form-group">
                                    <button type="button" class="btn btn-info pull-right" onclick="search();">搜索</button>
                                </div>
                            </div>



                        </div>

                        <%--<div class="box-footer">
                            <button type="button" class="btn btn-info pull-right" onclick="search();">搜索</button>
                        </div>--%>
                    </div>

                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">内容列表</h3>
                        </div>

                        <div class="box-body"><%--App.deleteMulti('/user/delete')--%>
                            <a href="/content/form" type="button" class="btn btn-sm btn-default"><i class="fa fa-plus"></i> 新增</a>&nbsp;&nbsp;&nbsp;
                            <button type="button" class="btn btn-sm btn-default" onclick="App.deleteData('/content/delete')"><i class="fa fa-trash-o"></i> 删除</button>&nbsp;&nbsp;&nbsp;
                            <a href="#" type="button" class="btn btn-sm btn-default"><i class="fa fa-download"></i> 导入</a>&nbsp;&nbsp;&nbsp;
                            <a href="#" type="button" class="btn btn-sm btn-default"><i class="fa fa-upload"></i> 导出</a>&nbsp;&nbsp;&nbsp;
                            <button type="button" class="btn btn-sm btn-primary" onclick="$('.box-info-search').css('display') == 'none' ? $('.box-info-search').show('fast') : $('.box-info-search').hide('fast')"><i class="fa fa-search"></i> 搜索</button>
                        </div>

                        <!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <table id="dataTable" class="table table-hover">
                                <thead>
                                <tr>

                                    <th>ID</th>
                                    <th>所属类别</th>
                                    <th>标题</th>
                                    <th>副标题</th>
                                    <th>标题描述</th>
                                    <th>链接</th>
                                    <th>图片1</th>
                                    <th>图片2</th>
                                    <th>更新时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>

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
<%-- 自定义模态框标签--%>
<sys:modal />
</body>
</html>
<script>
    var _dataTable ;

    $(function () {

        var columns = [

            {"data": "id"},
            {"data": "category.name"},
            {"data": "title"},
            {"data": "subTitle"},
            {"data": "titleDesc"},
            {
                "data": function (row, type, val, meta) {
                    return "<a href='" + row.url + "' target='_blank'>查看</a>"
                }
            },
            {
                "data": function (row, type, val, meta) {
                    if (row.pic == null) {
                        return "";
                    } else {
                        return "<a href='" + row.pic + "' target='_blank'>查看</a>"
                    }
                },
                "className": "colWidth"
            },
            {
                "data": function (row, type, val, meta) {
                    if (row.pic2 == null) {
                        return "";
                    } else {
                        return "<a href='" + row.pic2 + "' target='_blank'>查看</a>"
                    }
                }
            },
            {
                "data": function (row, type, val, meta) {
                    return DateTime.format(row.updated, "yyyy-MM-dd HH:mm:ss")
                }
            },
            {
                "data": function (row, type, val, meta) {
                    var detailUrl = "/content/detail?id=" + row.id;
                    var deleteUrl = "/content/delete"
                    return '<button type="button" class="btn btn-sm btn-default" onclick="App.detailInfo(\''+ detailUrl +'\');"><i class="fa fa-search"></i> 查看</button>&nbsp;&nbsp;&nbsp;' +
                        '<a href="/content/form?id=' + row.id + '" type="button" class="btn btn-sm btn-primary"><i class="fa fa-edit"></i> 编辑</a>&nbsp;&nbsp;&nbsp;' +
                        '<button  type="button" class="btn btn-sm btn-danger" onclick="App.deleteData(\''+ deleteUrl + '\',' + row.id +')"><i class="fa fa-trash-o"></i> 删除</button>'
                }
            }
        ];
        _dataTable = App.initDataTables("/content/page", columns);

    });
    function search() {
        var title = $("#title").val();
        var subTitle = $("#subTitle").val();
        var titleDesc = $("#titleDesc").val();

        var param = {
            "title": title,
            "subTitle": subTitle,
            "titleDesc": titleDesc
        };
        _dataTable.settings()[0].ajax.data = param;
        _dataTable.ajax.reload();
    };


</script>