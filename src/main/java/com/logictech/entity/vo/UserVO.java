package com.logictech.entity.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author JG.Hannibal
 * @since 2017/11/9 上午9:31
 */
public class UserVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户类型
     */
    private String userType;
    /**
     * 真名
     */
    private String realName;
    /**
     * QQ
     */
    private String qq;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 电话
     */
    private String tel;
    /**
     *  城市
     */
    private String city;
    /**
     * 省
     */
    private String province;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", userType='" + userType + '\'' +
                ", realName='" + realName + '\'' +
                ", qq='" + qq + '\'' +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                '}';
    }

    public UserVO(Integer id, String username, String userType, String realName, String qq, String email, String tel, String city, String province) {
        this.id = id;
        this.username = username;
        this.userType = userType;
        this.realName = realName;
        this.qq = qq;
        this.email = email;
        this.tel = tel;
        this.city = city;
        this.province = province;
    }

    public UserVO() {

    }
}
    