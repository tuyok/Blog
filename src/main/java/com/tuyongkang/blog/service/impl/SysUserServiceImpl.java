package com.tuyongkang.blog.service.impl;

import com.tuyongkang.blog.dao.repository.SysUserRepository;
import com.tuyongkang.blog.model.entity.SysUserEntity;
import com.tuyongkang.blog.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserRepository sysUserDao;

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
