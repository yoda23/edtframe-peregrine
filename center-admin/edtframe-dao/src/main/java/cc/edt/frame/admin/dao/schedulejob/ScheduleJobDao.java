package cc.edt.frame.admin.dao.schedulejob;

import cc.edt.frame.model.condition.FindCondition;
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
     * 根据条件查询调度器信息
     *
     * @param condition condition
     * @return java.util.List<cc.edt.frame.model.entity.schedulejob.ScheduleJob>
     * @author 姜宁 2019-01-24 13:09:51
     */
    List<ScheduleJob> listScheduleJobByCondition(FindCondition condition);

    /**
     * 根据任务名称获取任务
     *
     * @param jobName jobName
     * @return cc.edt.frame.model.entity.schedulejob.ScheduleJob
     * @author 姜宁 2019-01-24 13:10:48
     */
    ScheduleJob getScheduleJobByJobName(String jobName);

    /**
     * 根据任务分组获取任务
     *
     * @param jobGroup jobGroup
     * @return cc.edt.frame.model.entity.schedulejob.ScheduleJob
     * @author 姜宁 2019-01-24 13:11:51
     */
    ScheduleJob getScheduleJobByJobGroup(String jobGroup);

    /**
     * 根据id获取任务信息
     *
     * @param id id
     * @return cc.edt.frame.model.entity.schedulejob.ScheduleJob
     * @author 姜宁 2019-01-24 13:13:30
     */
    ScheduleJob getScheduleJobById(String id);

    /**
     * 修改任务
     *
     * @param scheduleJob scheduleJob
     * @author 姜宁 2019-01-24 13:12:33
     */
    void updateScheduleJob(ScheduleJob scheduleJob);

    /**
     * 保存任务
     *
     * @param scheduleJob scheduleJob
     * @author 姜宁 2019-01-24 0024 13:23:17
     */
    void saveScheduleJob(ScheduleJob scheduleJob);

    /**
     * 根据ID删除任务
     *
     * @param id id
     * @author 姜宁 2019-01-24 0024 13:14:08
     */
    void deleteScheduleJobById(String id);

    /**
     * 修改任务状态
     *
     * @param scheduleJob scheduleJob
     * @author 姜宁 2019-01-24 0024 13:32:14
     */
    void updateScheduleJobStatusById(ScheduleJob scheduleJob);

}
