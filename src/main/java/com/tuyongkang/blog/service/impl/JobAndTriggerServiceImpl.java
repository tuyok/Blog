package com.tuyongkang.blog.service.impl;

import com.tuyongkang.blog.dao.JobAndTriggerDao;
import com.tuyongkang.blog.model.vo.JobAndTriggerOutVo;
import com.tuyongkang.blog.service.JobAndTriggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobAndTriggerServiceImpl implements JobAndTriggerService {

    @Autowired
    private JobAndTriggerDao jobAndTriggerDao;

    @Override
    public List<JobAndTriggerOutVo> getJobAndTriggerDetails() {
        return jobAndTriggerDao.getJobAndTriggerDetails();
    }

}
