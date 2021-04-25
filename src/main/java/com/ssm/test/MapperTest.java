package com.ssm.test;

import com.ssm.bean.Department;
import com.ssm.bean.Employee;
import com.ssm.bean.User;
import com.ssm.dao.DepartmentMapper;
import com.ssm.dao.EmployeeMapper;
import com.ssm.dao.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 测试dao层的mapper方法
 * 注解：@ContextConfiguration指定spring配置文件的位置
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    UserMapper userMapper;

    // 已配置的可以执行批量操作的sqlSession
    @Autowired
    SqlSession sqlSession;

    @Test
    public void testDepartment() {
//        System.out.println(departmentMapper);

        System.out.println(departmentMapper.insert(new Department(null, "开发部")));
        System.out.println(departmentMapper.insert(new Department(null, "测试部")));
    }

    @Test
    public void testEmployee() {
//        System.out.println(employeeMapper);
        // 单条插入
//        System.out.println(employeeMapper.insert(new Employee(null, "杰瑞", "M", "jr@qq.com", 1)));
        // 批量插入
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for (int i = 0; i < 1000; i++) {
            String uid = UUID.randomUUID().toString().substring(0, 5) + i;
            if (i % 2 == 0)
                employeeMapper.insert(new Employee(null, uid, "M", uid + "@163.com", 1));
            else
                employeeMapper.insert(new Employee(null, uid, "G", uid + "@qq.com", 2));
        }
        System.out.println("批量完成！");
    }

    @Test
    public void testUser() {
//        User user = userMapper.getUser(18711352577L, "Zheng2577");
//        System.out.println(user.toString());

//        User user = new User(18711352577L, "Zheng2577", LocalDateTime.now().toString());
//        int i = userMapper.updateUserById(user);
//        if (i == 1)
//            System.out.println(user.toString());

        User user = userMapper.getUserByLoginToken("2021-04-25T11:47:42.008");
        System.out.println(user.toString());
    }

}
