package cc.edt.frame.quartz.service;

import java.lang.reflect.Method;

import org.quartz.JobExecutionContext;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import cc.edt.frame.model.entity.schedulejob.ScheduleJob;


/**
 * 任务工厂
 *
 * @author 刘钢
 * @date 2018/12/20 9:40
 */
@Service("quartzJobFactory")
public class QuartzJobFactory extends QuartzJobBean {
    @Override
    protected void executeInternal(
            @NonNull JobExecutionContext jobExecutionContext) {
        ScheduleJob scheduleJob = (ScheduleJob) jobExecutionContext
                .getMergedJobDataMap().get("scheduleJob");
        Object object = ApplicationContextUtil
                .getBean(scheduleJob.getBeanName());
        try {
            if (scheduleJob.getBeanName().contains("\\.")) {
                Class clazz = Class.forName(scheduleJob.getBeanName());
                Object cronJob = ApplicationContextUtil.getBean(clazz);
                Method method = clazz.getMethod(scheduleJob.getMethodName());
                method.invoke(cronJob);
            } else {
                Method method = object.getClass()
                        .getMethod(scheduleJob.getMethodName());
                method.invoke(object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
