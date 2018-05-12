package com.tuyongkang.blog.ext.jpa.entity;

import javax.persistence.*;

/**
 * 系统角色权限中间表
 */
@Entity
@Table(name = "T_SYS_ROLE_PERMISSION")
public class SysRolePermissionEntity {

    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 系统角色ID
     */
    @Column(name = "sys_role_id")
    private Integer sysRoleId;

    /**
     * 系统权限ID
     */
    @Column(name = "sys_permission_id")
    private Integer sysPermissionId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(Integer sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    public Integer getSysPermissionId() {
        return sysPermissionId;
    }

    public void setSysPermissionId(Integer sysPermissionId) {
        this.sysPermissionId = sysPermissionId;
    }

}
