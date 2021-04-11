package com.ssm.test;

import com.github.pagehelper.PageInfo;
import com.ssm.bean.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/**
 * 测试controller层的方法
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration// 使得WebApplicationContext可以被自动注入
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml"})
public class MvcTest {

    /**
     * 传入springMVC的ioc
     */
    @Autowired
    WebApplicationContext context;

    /**
     * 虚拟的mvc请求，获取到处理结果
     */
    MockMvc mockMvc;

    @Before
    public void initMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testPage() throws Exception {
        // 模拟请求，拿到返回值
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/employees").
                param("pn", "3")).andReturn();
        // 请求成功后，去除请求域中的pageInfo
        MockHttpServletRequest request = result.getRequest();
        PageInfo<Employee> pageInfo = (PageInfo<Employee>) request.getAttribute("pageInfo");
        // 分页信息
        System.out.println("当前页码：" + pageInfo.getPageNum());
        System.out.println("总页码：" + pageInfo.getPages());
        System.out.println("总记录数：" + pageInfo.getTotal());
        System.out.print("在页面连续显示的页码：");
        for (int i : pageInfo.getNavigatepageNums()) {
            System.out.print(i + " ");
        }
        // 员工数据
        List<Employee> employees = pageInfo.getList();
        for (Employee e : employees) {
            System.out.println(e.toString());
        }
    }
}
