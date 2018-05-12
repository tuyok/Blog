package com.tuyongkang.blog.ext.jpa.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 系统权限表
 */
@Entity
@Table(name="T_SYS_PERMISSION")
public class SysPermissionEntity {

    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 权限名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 权限描述
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 权限对应url
     */
    @Column(name = "url")
    private String url;

    /**
     * 权限创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
