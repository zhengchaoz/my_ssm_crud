package dao;

import com.ssm.dao.DepartmentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 *
 * 注解：@ContextConfiguration指定spring配置文件的位置
 *
 */
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class EmployeeMapperTest {

//    @Autowired
//    DepartmentMapper departmentMapper;

    @Test
    public void test() {

    }

}
