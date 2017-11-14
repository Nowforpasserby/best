package com.logictech;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author JG.Hannibal
 * @since 2017/11/9 上午9:28
 */
@SpringBootApplication
@MapperScan("com.logictech.mapper")
public class App {

    public static final Logger logger = LoggerFactory.getLogger(App.class);
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        logger.info("项目启动成功");
    }
}