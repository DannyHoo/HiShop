package com.danny.hishop.business.aggregation.controller;

import com.danny.hishop.business.aggregation.AggregationApplicationTests;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @date 2020/3/16下午10:12
 */
public class OrderBusinessControllerTest extends AggregationApplicationTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        //让每个测试用例启动之前都构建这样一个启动项
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void createOrderTest() {

    }

    @Test
    public void getTest() throws Exception {
        //MockMvcRequestBuilders构建GET请求
        mockMvc.perform(MockMvcRequestBuilders.get("/user")
                //请求参数
                .param("token", "asfsafqfEQFFA$@%%4")
                //请求编码和数据格式为json和UTF8
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                //期望的返回值 或者返回状态码
                .andExpect(MockMvcResultMatchers.status().isOk());
        //期望获取返回值的具体 什么参数 或者具体某个字段的值 具体在GitHub 搜jsonPath 什么参数的什么value
        //.andExpect(MockMvcResultMatchers.jsonPath("$.User.userName").value("zyy"));
    }

    @Test
    public void postTest() throws Exception {
        String content = "{\"userName\":\"名字\",\"passWord\":null,\"id\":88}";
        //MockMvcRequestBuilders构建GET请求
        String result = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                //请求编码和数据格式为json和UTF8
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                //请求的参数，为json的格式
                .content(content))
                //期望的返回值 或者返回状态码
                .andExpect(MockMvcResultMatchers.status().is5xxServerError())
                //返回请求的字符串信息
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
}
