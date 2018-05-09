package com.tuyongkang.blog.service;

import com.tuyongkang.blog.entity.SysUserEntity;

/**
 *
 */
public interface SysUserService {

    /**
     * 添加一个系统用户
     * @return
     */
    boolean addSysUser(SysUserEntity sysUserEntity);
}
