package com.logictech;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;


/**
 * @author JG.Hannibal
 * @since 2017/11/9 上午9:28
 */
@SpringBootApplication
@MapperScan("com.logictech.mapper")
public class App {

    public static final Logger logger = LoggerFactory.getLogger(App.class);
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        Environment environment = context.getBean(Environment.class);
        String active = environment.getProperty("spring.profiles.active");
        logger.info("项目启动成功,环境配置为:{}", active);
        String port = environment.getProperty("server.port");
        logger.info("启动主页为: http://127.0.0.1:{}/", port);
    }
}