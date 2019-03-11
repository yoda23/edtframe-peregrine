package cc.edt.frame.base.controller;

import cc.edt.frame.BaseTest;
import cc.edt.iceutils3.json.IceFastJsonUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author 刘钢
 * @date 2019/1/14 15:44
 */
public class UserControllerTest extends BaseTest {
    private MockMvc mockMvc;

    @Resource
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void getUserById() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("address", "合肥");
        MvcResult result = mockMvc.perform(post("/user/id/123").content(""))
                .andExpect(status().isOk())// 模拟向testRest发送get请求
                .andExpect(
                        content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果
        System.out.println(result.getResponse().getContentAsString());
    }
}