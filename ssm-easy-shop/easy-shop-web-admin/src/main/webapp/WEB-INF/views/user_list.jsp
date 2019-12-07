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
    <link rel="stylesheet" href="/static/assets/css/select2.css"/>
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
        <div>
                        <select style="width: 200px;">
                            <option value="">--请选择--</option>
                            <option value="">阿里</option>
                            <option value="">阿姨</option>
                            <option value="">江南</option>
                            <option value="">杭州</option>
                            <option value="">无锡</option>
                            <option value="">上海</option>
                        </select>
            <input class="singleSelect"/>
        </div>
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
                                    <label for="username" class="col-sm-4 control-label">姓名</label>

                                    <div class="col-sm-8">
                                        <input id="username" class="form-control" placeholder="姓名" />
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-3">
                                <div class="form-group">
                                    <label for="email" class="col-sm-4 control-label">邮箱</label>

                                    <div class="col-sm-8">
                                        <input id="email" class="form-control" placeholder="邮箱" />
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-3">
                                <div class="form-group">
                                    <label for="phone" class="col-sm-4 control-label">手机</label>

                                    <div class="col-sm-8">
                                        <input id="phone" class="form-control" placeholder="手机" />

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
                            <h3 class="box-title">用户列表</h3>
                        </div>

                        <div class="box-body"><%--App.deleteMulti('/user/delete')--%>
                            <a href="/user/form" type="button" class="btn btn-sm btn-default"><i class="fa fa-plus"></i> 新增</a>&nbsp;&nbsp;&nbsp;
                            <button type="button" class="btn btn-sm btn-default" onclick="App.deleteData('/user/delete')"><i class="fa fa-trash-o"></i> 删除</button>&nbsp;&nbsp;&nbsp;
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
<script src="/static/assets/js/select2.js"></script>
<!-- treeTable -->
<%-- 自定义模态框标签--%>
<sys:modal />

</body>
</html>
<script>
    var _dataTable ;

$(function () {
    $('.singleSelect').select2({
        ajax: {
            type:'GET',
            url: "/user/testSelect2",
            dataType: 'json',
            delay: 250,
            data: function (term, page) {
                return {
                    username: term, // search term 请求参数 ， 请求框中输入的参数
                    start: page
                };
            },
            results: function (res, page) {

                var data = res.data;
                var cbData = [];
                var len = data.length;
                for(var i= 0; i<len; i++){
                    var option = {"id": data[i]["id"], "text": data[i]["username"]};
                    cbData.push(option);
                };

                return {
                    results: cbData,//itemList

                };
            },
            cache: true
        },

        placeholder:'请选择',//默认文字提示
        language: "zh-CN",
        tags: true,//允许手动添加
        allowClear: true,//允许清空
        escapeMarkup: function (markup) { return markup; }, // 自定义格式化防止xss注入
        minimumInputLength: 0,//最少输入多少个字符后开始查询
        templateResult: function formatRepo(repo){return repo.username;}, // 函数用来渲染结果
        templateSelection: function formatRepoSelection(repo){return repo.username;} // 函数用于呈现当前的选择
    });

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
            var detailUrl = "/user/detail?id=" + row.id;
            var deleteUrl = "/user/delete"
            return '<button type="button" class="btn btn-sm btn-default" onclick="App.detailInfo(\''+ detailUrl +'\');"><i class="fa fa-search"></i> 查看</button>&nbsp;&nbsp;&nbsp;' +
                '<a href="/user/form?id=' + row.id + '" type="button" class="btn btn-sm btn-primary"><i class="fa fa-edit"></i> 编辑</a>&nbsp;&nbsp;&nbsp;' +
                '<button  type="button" class="btn btn-sm btn-danger" onclick="App.deleteData(\''+ deleteUrl + '\',' + row.id +')"><i class="fa fa-trash-o"></i> 删除</button>'
        }
    }
];
    _dataTable = App.initDataTables("/user/page", columns);

});
   function search() {
        var username = $("#username").val();
        var phone = $("#phone").val();
        var email = $("#email").val();

        var param = {
            "username": username,
            "phone": phone,
            "email": email
        };
        _dataTable.settings()[0].ajax.data = param;
       _dataTable.ajax.reload();
    };


</script>