package cc.edt.frame.quartz.service;

import cc.edt.frame.quartz.dao.schedulejob.ScheduleJobDao;
import cc.edt.frame.model.entity.schedulejob.ScheduleJob;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 刘钢
 * @date 2018/12/20 15:37
 */
@Component
public class QuartzInitlizeService {
    @Resource
    private ScheduleJobDao scheduleJobDao;
    @Resource
    private SchedulerJobService schedulerJobService;

    public void initlize() {
        List<ScheduleJob> listScheduleJob = scheduleJobDao
                .listScheduleJobByAll();
        for (ScheduleJob scheduleJob : listScheduleJob) {
            schedulerJobService.delete(scheduleJob);
            schedulerJobService.add(scheduleJob, true);
        }
    }
}
