package com.logictech.service;

import com.logictech.entity.dto.UserDTO;

import java.util.List;

/**
 * @author JG.Hannibal
 * @since 2017/11/11 下午12:00
 */
public interface UserService {

    List<UserDTO> listUser() throws Exception;

    Integer addUser(UserDTO userDTO) throws Exception;

    Integer updateUser(UserDTO userDTO) throws Exception;

    Integer removeUser(Integer id) throws Exception;

    void transactionalUser () throws Exception;
}
