package com.bogewang.springmvc4.service;

import com.bogewang.springmvc4.MyMvcConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by bogewang on 2017/7/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MyMvcConfig.class})
@WebAppConfiguration("src/main/resources")      //1 用来声明加载的applicationContext 是一个webApplicationContext. 他的属性指定的是web资源的位置,默认为src/main/webapp,本例修改为src/main/resources
public class DemoServiceTest {
    private MockMvc mockMvc;        //2 模拟mvc对象,通过mockMvcBuilders.webappcontextstep(this.wac).build()初始化;

    @Autowired
    private DemoService demoService;        //3

    @Autowired
    WebApplicationContext wac;      //4

    @Autowired
    MockHttpSession session;        //5 演示,没有使用

    @Autowired
    MockHttpServletRequest request;     //6 演示,没有使用

    @Before     //7
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();        //2
    }

    @Test
    public void testNormalController() throws Exception{
        mockMvc.perform(get("/normal"))     //8 模拟向normal进行get请求
                .andExpect(status().isOk())     //9 预期返回装填为200
                .andExpect(view().name("page"))     //10 预期view的名车恩为page
                .andExpect(forwardedUrl("/WEB-INF/classes/views/page.jsp"))     //11
                .andExpect(model().attribute("msg",demoService.saySomething()));     //12
    }


    @Test
    public void testRestController() throws Exception {
        mockMvc.perform(get("/testRest"))  // 进行get请求
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=utf-8"))       //返回媒体类型
                .andExpect(content().string(demoService.saySomething()));       //15 hello
    }

}