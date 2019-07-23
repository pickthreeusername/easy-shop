<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- /.row -->
<div class="row">
    <div class="col-xs-12">
        <div class="box">
            <div class="box-header">
                <h3 class="box-title">详细信息</h3>


            </div>
            <!-- /.box-header -->
            <div class="box-body table-responsive no-padding">
                <table class="table">
                    <tbody>
                        <tr>
                            <td>ID</td>
                            <td>${user.id}</td>
                        </tr>
                        <tr>
                            <td>邮箱</td>
                            <td>${user.email}</td>
                        </tr>
                        <tr>
                            <td>手机号</td>
                            <td>${user.phone}</td>
                        </tr>
                        <tr>
                            <td>创建时间</td>
                            <td>
                                <fmt:formatDate value="${user.created}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                            </td>
                        </tr>
                        <tr>
                            <td>更新时间</td>
                            <td>
                                <fmt:formatDate value="${user.updated}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>

                            </td>
                        </tr>
                    </tbody>


                </table>

            </div>
            <!-- /.box-body -->
        </div>
        <!-- /.box -->
    </div>
</div>