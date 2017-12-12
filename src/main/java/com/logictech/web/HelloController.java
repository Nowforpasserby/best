package com.logictech.web;

import com.logictech.config.BizPropConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author JG.Hannibal
 * @since 2017/11/9 下午10:02
 */
@Controller
public class HelloController {
    @Value("${profile.message}")
    private String message;

    @Autowired
    private BizPropConfig bizPropConfig;

    @RequestMapping("/")
    public String getUsers(Model model) {
        model.addAttribute("name", "JG.Hannibal");
        return "index";
    }

    @ResponseBody
    @RequestMapping("/profile")
    public String getUsersT() {
        return message.concat(bizPropConfig.toString());
    }
}
    