package cc.edt.frame.quartz.dao.schedulejob;

import cc.edt.frame.model.entity.schedulejob.ScheduleJob;

import java.util.List;

/**
 * 任务调度dao
 *
 * @author 姜宁
 * @date 2019-01-24 13:09:06
 */
public interface ScheduleJobDao {

    /**
     * 获取所有定时器配置
     *
     * @return java.util.List<cc.edt.frame.model.entity.schedulejob.ScheduleJob>
     * @author 刘钢
     * @date 2019/1/25 14:19
     */
    List<ScheduleJob> listScheduleJobByAll();

}
