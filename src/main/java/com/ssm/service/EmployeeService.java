package com.ssm.service;

import com.ssm.bean.Employee;
import com.ssm.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务逻辑组件
 */
@Service
public class EmployeeService {

    final
    EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    /**
     * 查询所有员工带部门的信息
     * 员工ID没有正确的排列
     * 原因：左外连接查询会根据主表向从表一个一个的查，导致从表的dept_id等于1的先查出来。
     * 解决办法：左外连接的事情了语句下加上排序order by emp_id
     *
     * @return
     */
    public List<Employee> getAll() {
        return employeeMapper.selectAllWithDept();
    }

    public void saveEmp(Employee employee) {
        employeeMapper.insert(employee);
    }

    public Employee getEmp(Integer id) {
        Employee employee = employeeMapper.selectByPrimaryKeyWithDept(id);
        return employee;
    }

    public void editEnp(Employee employee) {
        employeeMapper.updateByPrimaryKey(employee);
    }

    public void delEmp(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
    }
}
