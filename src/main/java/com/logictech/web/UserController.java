package com.logictech.web;

import com.logictech.entity.dto.UserDTO;
import com.logictech.entity.vo.UserVO;
import com.logictech.mapper.UserMapper;
import com.logictech.service.UserService;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static com.logictech.App.logger;

/**
 * @author JG.Hannibal
 * @since 2017/11/9 上午9:33
 */
@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Resource
    private UserService userService;

    @RequestMapping("/users")
    public List<UserDTO> users() {
        List<UserDTO> users = userMapper.selectAll();
        return users;
    }

    @RequestMapping("/user/{id}")
    public UserVO getUser(@PathVariable("id") Integer id, @NotBlank String username, @NotBlank String password) {
        UserVO user = userMapper.selectUserVO(id);
        logger.info("username: {}, password: {}", username, password);
        return user;
    }

    @PostMapping("/add")
    public Integer save(@Valid @RequestBody UserDTO user) throws Exception {
        return userService.addUser(user);
    }

    @PostMapping(value = "update")
    public Integer update(@Valid @RequestBody UserDTO user) throws Exception {
        return userService.updateUser(user);
    }

    @RequestMapping(value = "/delete/{id}")
    public Integer delete(@PathVariable("id") Integer id) throws Exception {
        return userService.removeUser(id);
    }

    @RequestMapping(value = "/test")
    public void test() throws Exception {
        userService.transactionalUser();
    }
}
    