package com.tuyongkang.blog.dao.impl;

import com.tuyongkang.blog.dao.JobAndTriggerDao;
import com.tuyongkang.blog.model.vo.JobAndTriggerOutVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JobAndTriggerDaoImpl implements JobAndTriggerDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<JobAndTriggerOutVo> getJobAndTriggerDetails() {

        StringBuilder sql = new StringBuilder("select ");

        sql.append("qrtz_job_details.JOB_NAME, qrtz_job_details.JOB_GROUP, qrtz_job_details.JOB_CLASS_NAME, ");
        sql.append("qrtz_triggers.TRIGGER_NAME, qrtz_triggers.TRIGGER_GROUP, ");
        sql.append("qrtz_cron_triggers.CRON_EXPRESSION, qrtz_cron_triggers.TIME_ZONE_ID ");
//        sql.append("qrtz_simple_triggers.REPEAT_INTERVAL, qrtz_simple_triggers.TIMES_TRIGGERED ");

        sql.append("from qrtz_job_details ");
        sql.append("JOIN qrtz_triggers ");
        sql.append("JOIN qrtz_cron_triggers ON qrtz_job_details.JOB_NAME = qrtz_triggers.JOB_NAME ");
        sql.append("AND qrtz_cron_triggers.TRIGGER_NAME = qrtz_triggers.TRIGGER_NAME ");
        sql.append("AND qrtz_cron_triggers.TRIGGER_GROUP = qrtz_triggers.TRIGGER_GROUP ");
//        sql.append("JOIN qrtz_simple_triggers ON qrtz_job_details.JOB_NAME = qrtz_triggers.JOB_NAME ");
//        sql.append("AND qrtz_triggers.TRIGGER_NAME = qrtz_simple_triggers.TRIGGER_NAME ");
//        sql.append("AND qrtz_triggers.TRIGGER_GROUP = qrtz_simple_triggers.TRIGGER_GROUP ");


        List<JobAndTriggerOutVo> jobAndTriggerOutVoList = jdbcTemplate.query(sql.toString(), new RowMapper<JobAndTriggerOutVo>() {
            @Nullable
            @Override
            public JobAndTriggerOutVo mapRow(ResultSet rs, int rowNum) throws SQLException {

                JobAndTriggerOutVo jobAndTriggerOutVo = new JobAndTriggerOutVo();
                jobAndTriggerOutVo.setJobName(rs.getString(1));
                jobAndTriggerOutVo.setJobGroup(rs.getString(2));
                jobAndTriggerOutVo.setJobClassName(rs.getString(3));
                jobAndTriggerOutVo.setTriggerName(rs.getString(4));
                jobAndTriggerOutVo.setTriggerGroup(rs.getString(5));
//                jobAndTriggerOutVo.setRepeatInterval(rs.getLong(6));
//                jobAndTriggerOutVo.setTimesTriggered(rs.getLong(7));
                jobAndTriggerOutVo.setCronExpression(rs.getString(6));
                jobAndTriggerOutVo.setTimeZoneId(rs.getString(7));

                return jobAndTriggerOutVo;
            }
        });

        return jobAndTriggerOutVoList;

    }

}
