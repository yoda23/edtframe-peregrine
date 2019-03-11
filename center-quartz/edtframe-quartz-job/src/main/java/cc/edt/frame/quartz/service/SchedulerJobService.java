package cc.edt.frame.quartz.service;

import javax.annotation.Resource;

import cc.edt.frame.model.entity.schedulejob.ScheduleJob;
import org.quartz.*;
import org.springframework.stereotype.Component;

/**
 * @author 刘钢
 * @date 2018/12/20 10:55
 */
@Component
public class SchedulerJobService {
    @Resource
    private Scheduler scheduler;

    /**
     * 添加任务
     *
     * @param scheduleJob scheduleJob
     * @param isPause     isPause
     * @author 刘钢
     * @date 2018/12/20 16:19
     */
    public void add(ScheduleJob scheduleJob, boolean isPause) {
        JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class)
                .withIdentity(scheduleJob.getJobName(),
                        scheduleJob.getJobGroup())
                .build();
        jobDetail.getJobDataMap().put("scheduleJob", scheduleJob);
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder
                .cronSchedule(scheduleJob.getCronExpression());
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity(scheduleJob.getJobName(),
                        scheduleJob.getJobGroup())
                .withSchedule(cronScheduleBuilder).build();
        try {
            scheduler.scheduleJob(jobDetail, cronTrigger);
            if (isPause) {
                JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(),
                        scheduleJob.getJobGroup());
                scheduler.pauseJob(jobKey);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改任务
     *
     * @param scheduleJob scheduleJob
     * @author 刘钢
     * @date 2018/12/20 16:19
     */
    public void update(ScheduleJob scheduleJob) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(),
                scheduleJob.getJobGroup());
        CronTrigger cronTrigger = (CronTrigger) scheduler
                .getTrigger(triggerKey);
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder
                .cronSchedule(scheduleJob.getCronExpression());
        cronTrigger = cronTrigger.getTriggerBuilder().withIdentity(triggerKey)
                .withSchedule(cronScheduleBuilder).build();
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(),
                scheduleJob.getJobGroup());
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        jobDetail.getJobDataMap().put("scheduleJob", scheduleJob);
        scheduler.rescheduleJob(triggerKey, cronTrigger);
    }

    /**
     * 删除任务
     *
     * @param scheduleJob scheduleJob
     * @author 刘钢
     * @date 2018/12/20 16:21
     */
    public void delete(ScheduleJob scheduleJob) {
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(),
                scheduleJob.getJobGroup());
        try {
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 暂停任务
     *
     * @param jobGroup jobGroup
     * @param jobName  jobName
     * @author 刘钢
     * @date 2018/12/20 16:23
     */
    public void pauseJob(String jobName, String jobGroup) {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        try {
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 重启任务
     *
     * @param jobGroup jobGroup
     * @param jobName  jobName
     * @author 刘钢
     * @date 2018/12/20 16:24
     */
    public void resumeJob(String jobName, String jobGroup)
            throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        scheduler.resumeJob(jobKey);
    }
}
