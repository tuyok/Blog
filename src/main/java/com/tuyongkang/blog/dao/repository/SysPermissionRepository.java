package com.tuyongkang.blog.dao.repository;

import com.tuyongkang.blog.entity.SysPermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SysPermissionRepository extends JpaRepository<SysPermissionEntity,Integer> {

    /**
     * 获取某个角色的所有权限
     * @param roleId
     * @return
     */
    @Query("select p from SysPermissionEntity p,SysRolePermissionEntity r where p.id = r.sysPermissionId and r.sysRoleId = ?1")
    List<SysPermissionEntity> findRolePermissions(Integer roleId);

}
