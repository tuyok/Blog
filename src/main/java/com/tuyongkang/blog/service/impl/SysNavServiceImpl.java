package com.tuyongkang.blog.service.impl;

import com.tuyongkang.blog.dao.repository.SysNavRepository;
import com.tuyongkang.blog.entity.SysNavEntity;
import com.tuyongkang.blog.service.SysNavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SysNavServiceImpl implements SysNavService {


    @Autowired
    private SysNavRepository sysNavRepository;

    @Override
    public void saveOrUpdate(SysNavEntity sysNavEntity) {
        if(sysNavEntity.getId() == null){
            if(sysNavEntity.getState() == null) sysNavEntity.setState((byte)0);
            if(sysNavEntity.getSort() == null) sysNavEntity.setState((byte)0);
            sysNavEntity.setCreateTime(new Date());
            sysNavEntity.setUpdateTime(sysNavEntity.getCreateTime());
        }else{
            sysNavEntity.setUpdateTime(new Date());
        }
        sysNavRepository.save(sysNavEntity);
    }

    @Override
    public void deleteById(Integer id) {
        sysNavRepository.deleteById(id);
    }

    @Override
    public List<SysNavEntity> findAll() {
        return sysNavRepository.findAll();
    }

}
