package com.tuyongkang.blog.service.impl;

import com.tuyongkang.blog.ext.jpa.dao.SysUserDao;
import com.tuyongkang.blog.ext.jpa.entity.SysRoleEntity;
import com.tuyongkang.blog.ext.jpa.entity.SysUserEntity;
import com.tuyongkang.blog.service.SysRoleService;
import com.tuyongkang.blog.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public SysUserEntity addSysUser(SysUserEntity sysUserEntity) {
        SysUserEntity sysUser = sysUserDao.save(sysUserEntity);
        return sysUser;
    }

    @Override
    public SysUserEntity findByUserName(String userName) {
        return sysUserDao.findByUserName(userName);
    }

}
