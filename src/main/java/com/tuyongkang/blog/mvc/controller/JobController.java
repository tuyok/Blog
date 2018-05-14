package com.tuyongkang.blog.mvc.controller;

import com.tuyongkang.blog.mvc.model.JobAndTriggerOutVo;
import com.tuyongkang.blog.mvc.model.ResponseVo;
import com.tuyongkang.blog.service.JobAndTriggerService;
import com.tuyongkang.blog.util.ResponseUtil;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/job")
public class JobController {

    private static Logger log = LoggerFactory.getLogger(JobController.class);

    @Autowired
    @Qualifier("Scheduler")
    private Scheduler scheduler;

    @Autowired
    private JobAndTriggerService jobAndTriggerService;

    /**
     * 添加任务
     * @param jobClassName
     * @param jobGroupName
     * @param cronExpression
     * @throws Exception
     */
    @PostMapping(value="/addjob")
    public ResponseVo addjob(@RequestParam(value="jobClassName")String jobClassName,
                             @RequestParam(value="jobGroupName")String jobGroupName,
                             @RequestParam(value="cronExpression")String cronExpression)
    {
        try {
            // 启动调度器
            scheduler.start();

            //构建job信息
            JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass()).withIdentity(jobClassName, jobGroupName).build();

            //表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

            //按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName)
                    .withSchedule(scheduleBuilder).build();

            scheduler.scheduleJob(jobDetail, trigger);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.renderError();

        }
        return ResponseUtil.renderSuccess();

    }

    /**
     * 暂停任务
     * @param jobClassName
     * @param jobGroupName
     * @throws Exception
     */
    @PostMapping(value="/pausejob")
    public ResponseVo pausejob(@RequestParam(value="jobClassName")String jobClassName, @RequestParam(value="jobGroupName")String jobGroupName)
    {
        try{
            scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));

        }catch(Exception e){
            e.printStackTrace();
            return ResponseUtil.renderError();

        }
        return ResponseUtil.renderSuccess();

    }

    /**
     * 恢复任务
     * @param jobClassName
     * @param jobGroupName
     * @throws Exception
     */
    @PostMapping(value="/resumejob")
    public ResponseVo resumejob(@RequestParam(value="jobClassName")String jobClassName, @RequestParam(value="jobGroupName")String jobGroupName)
    {
        try{
            scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));

        }catch(Exception e){
            e.printStackTrace();
            return ResponseUtil.renderError();

        }
        return ResponseUtil.renderSuccess();

    }

    /**
     * 重新设置任务调度的时间
     * @param jobClassName
     * @param jobGroupName
     * @param cronExpression
     * @throws Exception
     */
    @PostMapping(value="/reschedulejob")
    public ResponseVo rescheduleJob(@RequestParam(value="jobClassName")String jobClassName,
                              @RequestParam(value="jobGroupName")String jobGroupName,
                              @RequestParam(value="cronExpression")String cronExpression)
    {
        try {

            TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);

        } catch (SchedulerException e) {
            e.printStackTrace();
            return ResponseUtil.renderError();

        }
        return ResponseUtil.renderSuccess();

    }

    /**
     * 删除任务
     * @param jobClassName
     * @param jobGroupName
     * @throws Exception
     */
    @PostMapping(value="/deletejob")
    public ResponseVo deletejob(@RequestParam(value="jobClassName")String jobClassName, @RequestParam(value="jobGroupName")String jobGroupName) throws Exception
    {
        try{
            scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
            scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
            scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));

        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.renderError();
        }
        return ResponseUtil.renderSuccess();

    }

    /**
     * 查询任务
     * @return
     */
    @GetMapping(value="/queryjob")
    public Map<String, Object> queryjob()
    {
        List<JobAndTriggerOutVo> jobAndTrigger = jobAndTriggerService.getJobAndTriggerDetails();
        Map<String, Object> map = new HashMap<>();
        map.put("JobAndTrigger", jobAndTrigger);
        map.put("number", jobAndTrigger.size());
        return map;
    }

    private static Job getClass(String classname) throws Exception
    {
        Class<?> class1 = Class.forName(classname);
        return (Job)class1.newInstance();

    }

}
