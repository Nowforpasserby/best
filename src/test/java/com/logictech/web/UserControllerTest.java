package com.logictech.web;

import com.alibaba.fastjson.JSONObject;
import com.logictech.BaseTest;
import com.logictech.entity.dto.UserDTO;
import com.logictech.entity.so.AppException;
import com.logictech.mapper.UserMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends BaseTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserMapper userMapper;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void getUser() throws Exception {
        mockMvc.perform(get("/user/{id}", 1)
                .param("siteId", "webtrn"))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.code").value(1))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        mockMvc.perform(get("/user/{id}", 1)
                .param("username", "webtrn")
                .param("password", "webtrn"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    @Transactional
    public void save() throws Exception {
        Integer countBeforeSave = userMapper.selectCountByExample(null);
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("ceshi");
        userDTO.setPassword("ceshi");
        userDTO.setRealName("ceshi");
        String userJson = JSONObject.toJSON(userDTO).toString();
        mockMvc.perform(post("/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value(1))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        Integer countAfterSave = userMapper.selectCountByExample(null);

        Assert.assertEquals("没有插入记录", 1, countAfterSave - countBeforeSave);
    }

    @Test
    @Transactional
    public void update() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("ceshi1");
        userDTO.setPassword("ceshi1");
        userDTO.setRealName("ceshi1");
        String userJson = JSONObject.toJSON(userDTO).toString();
        mockMvc.perform(post("/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.code").value(1))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        UserDTO userDTOSuccess = new UserDTO();
        userDTOSuccess.setId(1);
        userDTOSuccess.setUsername("ceshi1");
        userDTOSuccess.setPassword("ceshi1");
        userDTOSuccess.setRealName("ceshi1");
        String userJsonSuccess = JSONObject.toJSON(userDTOSuccess).toString();
        System.out.println(userJsonSuccess);
        mockMvc.perform(post("/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJsonSuccess))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.data").value(1))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    @Transactional
    public void delete() throws Exception {
        mockMvc.perform(get("/delete/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.data").value(1))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test(expected = AppException.class)
    public void test1() throws Exception {
        mockMvc.perform(get("/test")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.code").value(1))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

}