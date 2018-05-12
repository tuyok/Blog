package com.tuyongkang.blog.service.impl;

import com.tuyongkang.blog.ext.jpa.dao.SysRoleDao;
import com.tuyongkang.blog.ext.jpa.entity.SysRoleEntity;
import com.tuyongkang.blog.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleDao sysRoleDao;

    @Override
    public List<SysRoleEntity> findUserRoles(Integer userId) {

        return sysRoleDao.findUserRoles(userId);

    }

    @Override
    public List<SysRoleEntity> findPermissionRoles(Integer permissionId) {

        return sysRoleDao.findPermissionRoles(permissionId);

    }

}
