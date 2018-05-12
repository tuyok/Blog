package com.tuyongkang.blog.ext.jpa.dao;

import com.tuyongkang.blog.ext.jpa.entity.SysRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SysRoleDao extends JpaRepository<SysRoleEntity,Integer> {

    /**
     * 根据用户的ID获取用户的角色列表
     * @param userId
     * @return
     */
    @Query("select r from SysRoleEntity r,SysUserRoleEntity u where r.id = u.sysRoleId and u.sysUserId = ?1")
    List<SysRoleEntity> findUserRoles(Integer userId);

    /**
     * 获取权限的角色
     * @param permissionId
     * @return
     */
    @Query("select r from SysRoleEntity r,SysRolePermissionEntity p where r.id = p.sysRoleId and p.sysPermissionId = ?1")
    List<SysRoleEntity> findPermissionRoles(Integer permissionId);

}
