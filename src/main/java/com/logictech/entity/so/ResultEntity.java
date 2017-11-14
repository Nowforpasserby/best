package com.logictech.entity.so;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.io.Serializable;

/**
 * @author JG.Hannibal
 * @since 2017/11/10 上午11:10
 */
@JsonInclude(Include.NON_NULL)
public class ResultEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 成功: 0
     */
    public static final int SUCCESS = 0;
    /**
     * 失败: 1
     */
    public static final int FAIL = 1;
    /**
     * 无权限: 2
     */
    public static final int NO_PERMISSION = 2;
    /**
     * 信息
     */
    private String msg = "成功";
    /**
     * 编码
     */
    private int code = SUCCESS;
    /**
     * 数据
     */
    private T data;

    public ResultEntity(String msg, int code, T data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResultEntity() {
        super();
    }
    public ResultEntity(T data) {
        super();
        this.data = data;
    }
    public ResultEntity(Exception e) {
        super();
        this.msg = e.getMessage();
        this.code = FAIL ;
    }
}
    