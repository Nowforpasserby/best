package com.logictech.config;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author JG.Hannibal
 * @since 2017/11/17 下午1:04
 */
@Controller
public class NotFoundPageConfig implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping(value = ERROR_PATH)
    public String handleError() {
        return "pages/404";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
    