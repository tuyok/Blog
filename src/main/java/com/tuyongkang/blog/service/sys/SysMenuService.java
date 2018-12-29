package com.tuyongkang.blog.service.sys;

import com.tuyongkang.blog.model.entity.SysMenuEntity;

import java.util.List;

public interface SysMenuService {

    void add(SysMenuEntity entity);

    void edit(SysMenuEntity entity);

    List<SysMenuEntity> findAll();

}
