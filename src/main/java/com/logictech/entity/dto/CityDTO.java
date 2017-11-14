package com.logictech.entity.dto;

import javax.persistence.*;

/**
 * @author JG.Hannibal
 * @since 2017/11/9 下午12:20
 */
@Table(name = "city")
public class CityDTO {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 城市名称
     */
    private String name;

    /**
     * 省
     */
    private String state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public CityDTO() {
    }

    public CityDTO(String name, String state) {
        this.name = name;
        this.state = state;
    }

    @Override
    public String toString() {
        return "CityDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
    