package com.tuyongkang.blog.service.impl;

import com.tuyongkang.blog.dao.repository.SysPermissionRepository;
import com.tuyongkang.blog.model.entity.SysPermissionEntity;
import com.tuyongkang.blog.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    @Autowired
    private SysPermissionRepository sysPermissionDao;

    @Override
    public List<SysPermissionEntity> findAll() {
        return sysPermissionDao.findAll();
    }

    @Override
    public List<SysPermissionEntity> findRolePermissions(Integer roleId) {

        return sysPermissionDao.findRolePermissions(roleId);

    }

}
