package com.tuyongkang.blog.service;

import com.tuyongkang.blog.entity.SysPermissionEntity;

import java.util.List;

public interface SysPermissionService {

    List<SysPermissionEntity> findAll();

    /**
     * 获取角色的权限列表
     * @param roleId
     * @return
     */
    List<SysPermissionEntity> findRolePermissions(Integer roleId);

}
