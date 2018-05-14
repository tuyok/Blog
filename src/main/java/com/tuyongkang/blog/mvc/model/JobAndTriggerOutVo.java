package com.tuyongkang.blog.mvc.model;

/**
 * 任务调度管理页面展示信息
 */
public class JobAndTriggerOutVo {

    // 任务名称
    private String jobName;
    // 任务所在组
    private String jobGroup;
    // 任务类名
    private String jobClassName;
    // 触发器名称
    private String triggerName;
    // 触发器所在组
    private String triggerGroup;
    // 触发间隔(毫秒)
    private Long repeatInterval;
    // 已触发次数
    private Long timesTriggered;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobClassName() {
        return jobClassName;
    }

    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public String getTriggerGroup() {
        return triggerGroup;
    }

    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
    }

    public Long getRepeatInterval() {
        return repeatInterval;
    }

    public void setRepeatInterval(Long repeatInterval) {
        this.repeatInterval = repeatInterval;
    }

    public Long getTimesTriggered() {
        return timesTriggered;
    }

    public void setTimesTriggered(Long timesTriggered) {
        this.timesTriggered = timesTriggered;
    }

}
