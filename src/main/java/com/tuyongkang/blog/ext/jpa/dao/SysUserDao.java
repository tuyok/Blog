package com.tuyongkang.blog.ext.jpa.dao;

import com.tuyongkang.blog.ext.jpa.entity.SysUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysUserDao extends JpaRepository<SysUserEntity,Integer> {

    /**
     * 根据用户名获取用户
     * @param userName
     * @return
     */
    SysUserEntity findByUserName(String userName);

}
