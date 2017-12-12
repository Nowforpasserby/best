package com.logictech.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author JG.Hannibal
 * @since 2017/12/12 下午11:20
 */
@Component
@ConfigurationProperties(prefix = "biz")
@PropertySource("classpath:biz1-${env}.properties")
public class BizPropConfig {
    private String message1;
    private String message2;

    @Override
    public String toString() {
        return "BizPropConfig{" +
                "message1='" + message1 + '\'' +
                ", message2='" + message2 + '\'' +
                '}';
    }

    public BizPropConfig() {
    }

    public BizPropConfig(String message1, String message2) {

        this.message1 = message1;
        this.message2 = message2;
    }

    public String getMessage1() {

        return message1;
    }

    public void setMessage1(String message1) {
        this.message1 = message1;
    }

    public String getMessage2() {
        return message2;
    }

    public void setMessage2(String message2) {
        this.message2 = message2;
    }
}
    