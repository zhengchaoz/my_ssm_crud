package com.ssm.service;

import com.ssm.bean.Department;
import com.ssm.dao.DepartmentMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentMapper departmentMapper;

    public DepartmentService(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    public List<Department> getDept() {
        List<Department> departments = departmentMapper.selectAll();
        return departments;
    }

}
