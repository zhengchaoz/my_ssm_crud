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
         pageEncoding="UTF-8" isELIgnored="false" isErrorPage="true" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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

<!-- 新增Modal -->
<div class="modal fade" id="empAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">员工添加</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="empName_input" class="col-sm-2 control-label">empName</label>
                        <div class="col-sm-10">
                            <input type="text" name="empName" class="form-control" id="empName_input"
                                   placeholder="empName">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email_input" class="col-sm-2 control-label">email</label>
                        <div class="col-sm-10">
                            <input type="text" name="email" class="form-control" id="email_input"
                                   placeholder="email@qq.com">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">gender</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender_radio_add_M" value="M" checked="checked"> 男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender_radio_add_F" value="F"> 女
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">deptName</label>
                        <div class="col-sm-10">
                            <select class="form-control" name="dId"></select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="emp_save_btn">保存</button>
            </div>
        </div>
    </div>
</div>

<!-- 编辑Modal -->
<div class="modal fade" id="empEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">员工编辑</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="empName_input" class="col-sm-2 control-label">empName</label>
                        <div class="col-sm-10">
                            <p class="form-control-static" name="empName" id="empName_input_edit"
                               placeholder="empName"></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email_input" class="col-sm-2 control-label">email</label>
                        <div class="col-sm-10">
                            <input type="text" name="email" class="form-control" id="email_input_edit"
                                   placeholder="email@qq.com">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">gender</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender_radio_edit_M" value="M" checked="checked">
                                男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender_radio_edit_F" value="F"> 女
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">deptName</label>
                        <div class="col-sm-10">
                            <select class="form-control" name="dId"></select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="emp_edit_btn">编辑</button>
            </div>
        </div>
    </div>
</div>

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
            <button type="button" class="btn btn-primary" id="emp_add_modal_btn">新增</button>
            <button type="button" class="btn btn-danger" id="emp_del_modal_btn">删除</button>
        </div>
    </div>
    <%-- 表格 --%>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>
                        <input type="checkbox" id="check_all"/>
                    </th>
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
    let pageInfo_total, pageInfo_pageNum;

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
                build_page_info(result.extend.pageInfo);
                build_page_nav(result.extend.pageInfo);
                pageInfo_total = result.extend.pageInfo.total;
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
            let editBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn").attr("edit_id", item.empId)
                .append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
            let delBtn = $("<button></button>").addClass("btn btn-danger btn-sm del_btn").attr("del_id", item.empId)
                .append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
            let btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn);
            $("<tr></tr>").append('<td><input type="checkbox" class="check_item"/></td>').append(empIdTd).append(empNameTd)
                .append(genderTd).append(emailTd).append(deptNameTd)
                .append(btnTd).appendTo("tbody");
        })
    }

    function build_page_info(pageInfo) {
        $("#build_page_info").empty();

        $("#build_page_info").append("当前 "
            + pageInfo.pageNum + " 页，总 "
            + pageInfo.pages + " 页，总 "
            + pageInfo.total + " 条记录");
        pageInfo_pageNum = pageInfo.pageNum;
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

    $("#emp_add_modal_btn").click(function () {
        clearModal();
        getDept();

        $('#empAddModal').modal({
            backdrop: "static"
        });
    });

    function getDept() {
        $.ajax({
            url: "${APP_PATH}/dept",
            type: "GET",
            async: false,
            success: function (result) {
                let options = "";
                $.each(result.extend.dept, function (i, e) {
                    if (i == 0)
                        options += '<option selected value="' + e.deptId + '">' + e.deptName + ' </option>';
                    else
                        options += '<option value="' + e.deptId + '">' + e.deptName + ' </option>';
                })
                $("form select").append(options);
            }
        });
    }

    function clearModal() {
        $("form select").empty();
        $("#empName_input").parent().removeClass("has-error");
        $("#empName_input").next("span").text("");
        $("#empName_input").val("");
        $("#email_input").parent().removeClass("has-error");
        $("#email_input").next("span").text("");
        $("#email_input").val("");
    }

    $("#emp_save_btn").click(function () {
        if (!validate_add_form()) return false;

        $.ajax({
            url: "${APP_PATH}/emp",
            type: "POST",
            data: $("#empAddModal form").serialize(),
            success: function (result) {
                if (result.code == 100) {
                    $('#empAddModal').modal('hide');
                    to_page(pageInfo_total);
                } else {
                    alert("保存失败：" + result.error);
                }
            }
        });
    });

    function validate_add_form() {
        let empName = $("#empName_input").val();
        let regName = /^[\u4e00-\u9fa5]+●?[\u4e00-\u9fa5]+$|^[a-zA-Z0-9]+\s?[\.·\-()a-zA-Z]*[a-zA-Z]+\)?$/;

        if (!regName.test(empName)) {
            $("#empName_input").next("span").text("输入数据非法！");
            $("#empName_input").parent().addClass("has-error");
            return false;
        }

        let email = $("#email_input").val();
        let regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;

        if (!regEmail.test(email)) {
            $("#email_input").next("span").text("输入数据非法！");
            $("#email_input").parent().addClass("has-error");
            return false;
        }

        return true;
    }

    $(document).on("click", ".edit_btn", function () {
        clearModal();
        getDept();
        getEmp($(this).attr("edit_id"));

        $('#empEditModal').modal({
            backdrop: "static"
        });
        $("#emp_edit_btn").attr("edit_id", $(this).attr("edit_id"));
    });

    function getEmp(id) {
        $.ajax({
            url: "${APP_PATH}/emp/" + id,
            type: "GET",
            success: function (result) {
                let emp = result.extend.emp;
                $("#empName_input_edit").text(emp.empName);
                $("#email_input_edit").val(emp.email);
                $("#empEditModal input[name=gender]").val([emp.gender]);
                $("#empEditModal select").val([emp.dId]);
            }
        });
    }

    $("#emp_edit_btn").click(function () {
        let email = $("#email_input_edit").val();
        let regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;

        if (!regEmail.test(email)) {
            $("#email_input_edit").next("span").text("输入数据非法！");
            $("#email_input_edit").parent().addClass("has-error");
            return false;
        }

        $.ajax({
            url: "${APP_PATH}/emp/" + $(this).attr("edit_id"),
            type: "PUT",
            data: $("#empEditModal form").serialize() + "&empName=" + $("#empName_input_edit").text()/* + "&_method=PUT"*/,
            success: function (result) {
                $('#empEditModal').modal('hide');
                to_page(pageInfo_pageNum);
            }
        });
    });

    $(document).on("click", ".del_btn", function () {
        let empName = $(this).parents("tr").find("td:eq(2)").text();
        let id = $(this).attr(`del_id`);

        if (confirm("确认删除【" + empName + "】吗？")) {
            $.ajax({
                url: "${APP_PATH}/emp/" + id,
                type: "DELETE",
                success: function (result) {
                    alert(result.msg);
                    to_page(pageInfo_pageNum);
                }
            });
        }
    });

    $("#check_all").click(function () {
        $(".check_item").prop("checked", $(this).prop("checked"));
    });

    $(document).on("click", ".check_item", function () {
        $("#check_all").prop("checked", $(".check_item:checked").length == $(".check_item").length);
    })

    /* 批量删除，后台逻辑没有写 */
    $("#emp_del_modal_btn").click(function () {
        let empName = [];
        $.each($(".check_item:checked"), function () {
            empName.push($(this).parents("tr").find("td:eq(2)").text());
        })

        if (confirm("确认删除【" + empName.toString() + "】吗？")) {
            $.ajax({
                url: "${APP_PATH}/emp/" + empName.toString(),
                type: "DELETE",
                success: function (result) {
                    alert(result.msg);
                    to_page(pageInfo_pageNum);
                }
            });
        }
    });
</script>
</body>
</html>
