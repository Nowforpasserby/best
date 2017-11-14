package com.logictech.service.impl;

import com.logictech.BaseTest;
import com.logictech.entity.dto.UserDTO;
import com.logictech.entity.so.AppException;
import com.logictech.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

public class UserServiceImplTest extends BaseTest {
    @Autowired
    @InjectMocks
    private UserService userService;

    @Test
    public void listUser() throws Exception {
        int count = userService.listUser().size();
        Assert.assertTrue(count <= 20);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void addUserError() throws Exception {
        UserDTO userFailed = new UserDTO();
        userFailed.setQq("shibaishibaishibaishibaishibaishibaishibaishibaishibai");
        userService.addUser(userFailed);
    }

    @Test
    public void addUser() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("ceshi");
        userDTO.setPassword("ceshi");
        userDTO.setRealName("ceshi");
        int count = userService.addUser(userDTO);
        Assert.assertEquals(1, count);
    }

    @Test(expected = AppException.class)
    public void updateUser() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);
        userDTO.setUsername("ceshi1");
        userDTO.setPassword("ceshi1");
        userDTO.setRealName("ceshi1");
        int count = userService.updateUser(userDTO);
        Assert.assertEquals(1, count);
        UserDTO userDTOFailed = new UserDTO();
        userDTOFailed.setId(9999999);
        userDTOFailed.setUsername("ceshi1");
        userDTOFailed.setPassword("ceshi1");
        userDTOFailed.setRealName("ceshi1");
        int countFailed = userService.updateUser(userDTOFailed);
        Assert.assertEquals(0, countFailed);
    }

    @Test(expected = AppException.class)
    public void removeUser() throws Exception {
        int count = userService.removeUser(1);
        Assert.assertEquals(1, count);
        int countFailed = userService.removeUser(99999);
        Assert.assertEquals(0, countFailed);
    }

    @Test(expected = AppException.class)
    public void transactionalUser() throws Exception {
        userService.transactionalUser();
    }

}