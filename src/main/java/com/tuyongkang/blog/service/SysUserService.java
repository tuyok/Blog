package com.tuyongkang.blog.service;

import com.tuyongkang.blog.model.entity.SysUserEntity;

/**
 *  系统用户管理服务
 */
public interface SysUserService {

    /**
     * 添加一个系统用户
     * @return
     */
    SysUserEntity addSysUser(SysUserEntity sysUserEntity);

    /**
     * 根据用户名获取用户
     * @param userName
     * @return
     */
    SysUserEntity findByUserName(String userName);

}
