package com.ssm.controller;

import com.ssm.bean.Department;
import com.ssm.service.DepartmentService;
import com.ssm.utils.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 处理和部门有关的请求
 */
@Controller
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * 返回所有部门信息
     *
     * @return
     */

    @RequestMapping("/dept")
    @ResponseBody
    public Msg getDept() {
        List<Department> departments = departmentService.getDept();
        return Msg.success().put("dept", departments);
    }

}
