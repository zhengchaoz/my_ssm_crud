package com.ssm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.bean.Employee;
import com.ssm.service.EmployeeService;
import com.ssm.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    /**
     * 员工保存
     *
     * @return
     */
    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    @ResponseBody
    public Msg saveEmp(@Valid Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            return Msg.bust().put("error", result.getFieldErrors().toString());
        } else {
            employeeService.saveEmp(employee);
            return Msg.success();
        }
    }

    /**
     * 根据id查询员工
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Msg getEmp(@PathVariable("id") Integer id) {
        Employee employee = employeeService.getEmp(id);
        return Msg.success().put("emp", employee);
    }

    /**
     * 员工更新
     *
     * @param employee
     * @return
     */
    @RequestMapping(value = "/emp/{empId}", method = RequestMethod.PUT)
    @ResponseBody
    public Msg saveEmp(Employee employee) {
        employeeService.editEnp(employee);
        return Msg.success();
    }

    /**
     * 单个删除，批量删除逻辑可以二合一，没写
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Msg delEmp(@PathVariable Integer id) {
        employeeService.delEmp(id);
        return Msg.success();
    }

}
