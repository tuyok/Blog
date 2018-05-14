package com.tuyongkang.blog.ext.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class NewJob implements Job {

    private static Logger _log = LoggerFactory.getLogger(NewJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        _log.error("New Job执行时间: " + new Date());
    }

}
