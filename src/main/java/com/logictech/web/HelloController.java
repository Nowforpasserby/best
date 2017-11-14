package com.logictech.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author JG.Hannibal
 * @since 2017/11/9 下午10:02
 */
@Controller
public class HelloController {

    @RequestMapping("/")
    public String getUsers(Model model) {
        model.addAttribute("name","JG.Hannibal");
        return "index";
    }

    @ResponseBody
    @RequestMapping("/a")
    public String getUsersT() {
        return "index";
    }
}
    