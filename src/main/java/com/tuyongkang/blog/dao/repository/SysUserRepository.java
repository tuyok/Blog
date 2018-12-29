package com.tuyongkang.blog.dao.repository;

import com.tuyongkang.blog.model.entity.SysUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysUserRepository extends JpaRepository<SysUserEntity,Integer> {

    /**
     * 根据用户名获取用户
     * @param userName
     * @return
     */
    SysUserEntity findByUserName(String userName);

}
