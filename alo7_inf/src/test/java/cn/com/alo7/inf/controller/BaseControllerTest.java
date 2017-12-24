package cn.com.alo7.inf.controller;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import cn.com.alo7.inf.AloApplicationStarterTests;

/**
 * controller测试基类
 * @author mazan
 *
 */
@WebAppConfiguration
public class BaseControllerTest extends AloApplicationStarterTests {

	@Autowired
    WebApplicationContext context;
	
    MockMvc mvc;
    
    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    
}
