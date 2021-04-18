package com.ssm.bean;

import javax.validation.constraints.Pattern;
import java.util.Objects;

public class Employee {
    private Integer empId;

    @Pattern(regexp = "(^[\\u4e00-\\u9fa5]+●?[\\u4e00-\\u9fa5]+$|^[a-zA-Z0-9]+\\s?[\\.·\\-()a-zA-Z]*[a-zA-Z]+\\)?$)",
            message = "输入数据非法！")
    private String empName;

    private String gender;

    @Pattern(regexp = "(^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$)", message = "输入数据非法！")
    private String email;

    private Integer dId;

    private Department department;

    public Employee() {
    }

    public Employee(Integer empId, String empName, String gender, String email, Integer dId) {
        this.empId = empId;
        this.empName = empName;
        this.gender = gender;
        this.email = email;
        this.dId = dId;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(empId, employee.empId) && Objects.equals(empName, employee.empName) && Objects.equals(gender, employee.gender) && Objects.equals(email, employee.email) && Objects.equals(dId, employee.dId) && Objects.equals(department, employee.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empId, empName, gender, email, dId, department);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", dId=" + dId +
                ", department=" + department.toString() +
                '}';
    }
}