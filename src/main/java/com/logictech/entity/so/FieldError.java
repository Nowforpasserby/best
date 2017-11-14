package com.logictech.entity.so;

import java.io.Serializable;

/**
 * @author JG.Hannibal
 * @since 2017/11/10 上午11:32
 */
public class FieldError implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 字段
     */
    private String name;
    /**
     * 消息
     */
    private String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FieldError() {

    }

    public FieldError(String name, String message) {

        this.name = name;
        this.message = message;
    }

    @Override
    public String toString() {
        return name + ":" + message;
    }
}