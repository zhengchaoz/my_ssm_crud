<%--
改用JSON和Ajax进行前后的交互，这里先注释掉
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<jsp:forward page="/employees"></jsp:forward>

<%--<html lang="zh-CN">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
        <title>JSP - Hello World</title>
        <!-- Bootstrap -->
        <link href="static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <h1><%= "Hello World!" %></h1>
        <br/>
        <a href="hello-servlet">Hello Servlet</a>

        <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
        <script type="text/javascript" src="static/js/jquery.min.js"></script>
        <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
        <script type="text/javascript" src="static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    </body>
</html>--%
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>员工列表</title>
    <%-- 相对路径获取资源，加上项目名 --%>
    <%
        pageContext.setAttribute("APP_PATH", request.getContextPath());
    %>
    <!-- Bootstrap -->
    <link href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <%-- 标题 --%>
    <div class="row">
        <div class="col-md-12">
            <h1>SSM-CRUD</h1>
        </div>
    </div>
    <%-- 按钮 --%>
    <div class="row">
        <div class="col-md-4 col-md-offset-8">
            <button type="button" class="btn btn-primary">新增</button>
            <button type="button" class="btn btn-danger">删除</button>
        </div>
    </div>
    <%-- 表格 --%>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>#</th>
                    <th>empName</th>
                    <th>gender</th>
                    <th>email</th>
                    <th>deptName</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody></tbody>
            </table>
        </div>
    </div>
    <%-- 分页 --%>
    <div class="row">
        <div class="col-md-6" id="build_page_info"></div>
        <div class="col-md-6" id="build_page_nav"></div>
    </div>
</div>
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script type="text/javascript" src="${APP_PATH}/static/js/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script type="text/javascript" src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

<script type="text/javascript">
    $(function () {
        to_page(1);
    });

    function to_page(pn) {
        $.ajax({
            url: "${APP_PATH}/employeesJSON",
            data: "pn=" + pn,
            type: "get",
            success: function (result) {
                build_employees_table(result);
                build_page_info(result);
                build_page_nav(result.extend.pageInfo);
            }
        });
    }

    function build_employees_table(result) {
        $("tbody").empty();

        let employees = result.extend.pageInfo.list;
        $.each(employees, function (i, item) {
            let empIdTd = $("<td></td>").append(item.empId);
            let empNameTd = $("<td></td>").append(item.empName);
            let genderTd = $("<td></td>").append(item.gender == "M" ? "男" : "女");
            let emailTd = $("<td></td>").append(item.email);
            let deptNameTd = $("<td></td>").append(item.department.deptName);
            let editBtn = $("<button></button>").addClass("btn btn-primary btn-sm")
                .append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
            let delBtn = $("<button></button>").addClass("btn btn-danger btn-sm")
                .append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
            let btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn);
            $("<tr></tr>").append(empIdTd).append(empNameTd)
                .append(genderTd).append(emailTd).append(deptNameTd)
                .append(btnTd).appendTo("tbody");
        })
    }

    function build_page_info(result) {
        $("#build_page_info").empty();

        $("#build_page_info").append("当前 "
            + result.extend.pageInfo.pageNum + " 页，总 "
            + result.extend.pageInfo.pages + " 页，总 "
            + result.extend.pageInfo.total + " 条记录");
    }

    function build_page_nav(pageInfo) {
        $("#build_page_nav").empty();
        let page = '<nav aria-label="Page navigation"><ul class="pagination">';

        if (pageInfo.isFirstPage)
            page += '<li class="disabled"><a onclick="to_page(1);">首页</a></li>';
        else
            page += '<li><a onclick="to_page(1);">首页</a></li>';

        if (pageInfo.hasPreviousPage)
            page += '<li><a onclick="to_page(' + (pageInfo.pageNum - 1) + ')" aria-label="Previous">' +
                '<span aria-hidden="true">&laquo;</span></a></li>';

        $.each(pageInfo.navigatepageNums, function (i, item) {
            if (item == pageInfo.pageNum)
                page += '<li class="active"><a href="#">' + item + '</a></li>';
            else
                page += '<li><a onclick="to_page(' + item + ')">' + item + '</a></li>';
        })

        if (pageInfo.hasNextPage)
            page += '<li><a onclick="to_page(' + (pageInfo.pageNum + 1) + ')" aria-label="Next">' +
                '<span aria-hidden="true">&raquo;</span></a></li>';

        if (pageInfo.isLastPage)
            page += '<li class="disabled"><a onclick="to_page(' + pageInfo.pages + ');">末页</a></li>';
        else
            page += '<li><a onclick="to_page(' + pageInfo.pages + ');">末页</a></li>';

        page += '</ul></nav>';
        $("#build_page_nav").append(page);
    }
</script>
</body>
</html>
