package com.logictech.entity.dto;

import javax.persistence.Column;
import java.util.Date;

/**
 * @author JG.Hannibal
 * @since 2017/11/17 下午2:13
 */
public class CommonDTO {
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    @Column(name = "create_time", updatable = false)
    private Date createTime = new Date();
    /**
     * 更新时间
     */
    @Column(name = "update_time", insertable = false)
    private Date updateTime = new Date();
    /**
     * 更新者
     */
    private String updateBy;

    public CommonDTO() {

    }

    public String getRemark() {

        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public CommonDTO(String remark, String createBy, Date createTime, Date updateTime, String updateBy) {
        this.remark = remark;
        this.createBy = createBy;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.updateBy = updateBy;
    }

    @Override
    public String toString() {
        return "CommonDTO{" +
                "remark='" + remark + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", updateBy='" + updateBy + '\'' +
                '}';
    }
}
    