package com.tuyongkang.blog.service;

import com.tuyongkang.blog.model.entity.SysNavEntity;

import java.util.List;

/**
 * 系统导航项管理
 */
public interface SysNavService {

    void saveOrUpdate(SysNavEntity sysNavEntity);

    void deleteById(Integer id);

    List<SysNavEntity> findAll();


}
