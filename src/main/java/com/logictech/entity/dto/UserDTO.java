package com.logictech.entity.dto;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author JG.Hannibal
 * @since 2017/11/9 上午9:31
 */
@Table(name = "user_info")
public class UserDTO extends CommonDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 用户名
     */
    @Length(min = 3, max = 8)
    private String username;
    /**
     * 密码
     */
    @NotNull
    private String password;
    /**
     * 用户类型
     */
    @Length(min = 1, max = 1)
    private String userType;
    /**
     * 是否有效
     */
    private Integer enabled;
    /**
     * 真名
     */
    @NotNull
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
     * 城市编号
     */
    private Integer cityId;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
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

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userType='" + userType + '\'' +
                ", enabled=" + enabled +
                ", realName='" + realName + '\'' +
                ", qq='" + qq + '\'' +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", cityId=" + cityId +
                '}';
    }

    public UserDTO(String username, String password, String userType, Integer enabled, String realName, String qq, String email, String tel, Integer cityId) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.enabled = enabled;
        this.realName = realName;
        this.qq = qq;
        this.email = email;
        this.tel = tel;
        this.cityId = cityId;
    }

    public Integer getCityId() {

        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public UserDTO() {

    }
}
    