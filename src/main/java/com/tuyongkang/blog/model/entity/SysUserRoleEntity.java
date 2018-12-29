package com.tuyongkang.blog.model.entity;

import javax.persistence.*;

/**
 * 系统用户角色中间表
 */
@Entity
@Table(name = "T_SYS_USER_ROLE")
public class SysUserRoleEntity {

    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户ID
     */
    @Column(name = "sys_user_id")
    private Integer sysUserId;

    /**
     * 角色ID
     */
    @Column(name = "sys_role_id")
    private Integer sysRoleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Integer sysUserId) {
        this.sysUserId = sysUserId;
    }

    public Integer getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(Integer sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

}
