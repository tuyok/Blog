package com.tuyongkang.blog.service;

import com.tuyongkang.blog.model.entity.SysRoleEntity;

import java.util.List;

public interface SysRoleService {

    /**
     * 获取用户的角色列表
     * @param userId
     * @return
     */
    List<SysRoleEntity> findUserRoles(Integer userId);

    /**
     * 获取权限的角色
     * @param permissionId
     * @return
     */
    List<SysRoleEntity> findPermissionRoles(Integer permissionId);

}
