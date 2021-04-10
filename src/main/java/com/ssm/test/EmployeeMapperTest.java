package com.ssm.test;

import com.ssm.dao.DepartmentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * 注解：@ContextConfiguration指定spring配置文件的位置
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class EmployeeMapperTest {

    @Autowired
    DepartmentMapper departmentMapper;

    @Test
    public void test() {
        System.out.println(departmentMapper);
    }

}
