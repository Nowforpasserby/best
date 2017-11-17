package com.logictech.service.impl;

import com.logictech.entity.dto.UserDTO;
import com.logictech.entity.so.AppException;
import com.logictech.mapper.UserMapper;
import com.logictech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.logictech.config.MessageConfig.get;

/**
 * @author JG.Hannibal
 * @since 2017/11/11 下午12:14
 */
@Service
@Transactional(rollbackFor = AppException.class)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserDTO> listUser() throws Exception {
        return userMapper.selectAll();
    }

    @Override
    public Integer addUser(UserDTO userDTO) throws Exception {
        return userMapper.mInsert(userDTO);
    }

    @Override
    public Integer updateUser(UserDTO userDTO) throws Exception {
        Integer update = userMapper.updateByPrimaryKey(userDTO);
        if (update > 0) {
            return update;
        } else {
            throw new AppException(get("EM0002"));
        }
    }

    @Override
    public Integer removeUser(Integer id) throws Exception {
        if (userMapper.deleteByPrimaryKey(id) > 0) {
            return 1;
        } else {
            throw new AppException(get("EM0003"));
        }
    }

    @Override
    public void transactionalUser() throws Exception {
        UserDTO user = new UserDTO();
        user.setUsername("J.G.Hannibal");
        user.setPassword("123456");
        user.setUserType("1");
        user.setEnabled(1);
        user.setRealName("jinweibin");
        user.setQq("10000");
        user.setEmail("132456@qq.com");
        user.setTel("110");
        user.setCityId(1);
        userMapper.insert(user);
        throw new AppException(get("EM0001"));
    }
}
    