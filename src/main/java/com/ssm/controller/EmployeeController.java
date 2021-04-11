package com.ssm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.bean.Employee;
import com.ssm.service.EmployeeService;
import com.ssm.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 处理员工的crud请求
 */
@Controller
public class EmployeeController {

    final
    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * 在dispatcherServlet-servlet.xml中配置了去哪个目录下寻找页面文件
     * 查询员工数据（分页查询，引入PageHelper）
     *
     * @param pn 当前页数，默认为第一页
     * @return
     */
    @RequestMapping("/employees")
    public String getEmployees(@RequestParam(value = "pn", defaultValue = "1") Integer pn,
                               Model model) {
        // 使用分页插件PageHelper
        PageHelper.startPage(pn, 5);
        //  位置要在PageHelper下面，会自动拼接sql，进行物理分页
        List<Employee> employees = employeeService.getAll();
        PageInfo<Employee> pageInfo = new PageInfo<>(employees, 5);// 页码连续显示5页
        model.addAttribute("pageInfo", pageInfo);

        return "list";
    }

    /**
     * 以JSON的形式返回数据
     *
     * @param pn 当前页数，默认为第一页
     * @return
     */
    @RequestMapping("/employeesJSON")
    @ResponseBody
    public Msg getEmployeesJSON(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        PageInfo<Employee> pageInfo = null;// 页码连续显示5页
        try {
            // 使用分页插件PageHelper
            PageHelper.startPage(pn, 5);
            //  位置要在PageHelper下面，会自动拼接sql，进行物理分页
            List<Employee> employees = employeeService.getAll();
            pageInfo = new PageInfo<>(employees, 5);

            return Msg.success().put("pageInfo", pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.bust().put("pageInfo", pageInfo);
        }
    }

}
