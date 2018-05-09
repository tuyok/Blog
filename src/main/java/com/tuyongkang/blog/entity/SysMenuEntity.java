package com.tuyongkang.blog.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 系统菜单对应的实体类
 */
@Entity
@Table(name = "T_SYS_MENU")
public class SysMenuEntity {

    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 菜单名称
     */
    @Column(name = "menu_name")
    private String menuName;

    /**
     * 菜单对应url地址
     */
    @Column(name = "menu_url")
    private String menuUrl;

    /**
     * 父级菜单
     */
    @Column(name = "father_id")
    private Integer fatherId;

    /**
     * 所属导航项
     */
    @Column(name = "sys_nav_id")
    private Integer sysNavId;

    /**
     * 菜单说明
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 排序值
     */
    @Column(name = "sort")
    private Integer sort;

    /**
     * 状态
     * 0 无效
     * 1 有效
     */
    @Column(name = "state")
    private Byte state;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 最后一次更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 创建用户id
     */
    @Column(name = "create_user_id")
    private Integer createUserId;

    /**
     * 最后一次更新用户id
     */
    @Column(name = "update_user_id")
    private Integer updateUserId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public Integer getFatherId() {
        return fatherId;
    }

    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
    }

    public Integer getSysNavId() {
        return sysNavId;
    }

    public void setSysNavId(Integer sysNavId) {
        this.sysNavId = sysNavId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
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

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }
}
