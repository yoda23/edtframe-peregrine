package cc.edt.frame.schedulejob.service;

import cc.edt.frame.common.result.ActionResult;
import cc.edt.frame.model.condition.FindCondition;
import cc.edt.frame.model.entity.schedulejob.ScheduleJob;

import java.util.List;

/**
 * 任务信息service接口
 *
 * @author 姜宁
 * @date 2019-01-24 9:43:49
 */
public interface ScheduleJobService {

    /**
     * 根据条件查询任务
     *
     * @param condition condition
     * @return java.util.List<cc.edt.frame.model.entity.schedulejob.ScheduleJob>
     * @author 姜宁 2019-01-24 13:29:53
     */
    List<ScheduleJob> listScheduleJobByCondition(FindCondition condition);

    /**
     * 根据ID获取任务
     *
     * @param id id
     * @return cc.edt.frame.model.entity.schedulejob.ScheduleJob
     * @author 姜宁 2019-01-24 0024 13:30:41
     */
    ScheduleJob getScheduleJobById(String id);

    /**
     * 添加任务
     *
     * @param scheduleJob scheduleJob
     * @return cc.edt.frame.common.result.ActionResult
     * @author 姜宁 2019-01-24 9:44:37
     */
    ActionResult add(ScheduleJob scheduleJob);

    /**
     * 修改任务
     *
     * @param scheduleJob scheduleJob
     * @return cc.edt.frame.common.result.ActionResult
     * @author 姜宁 2019-01-24 0024 11:54:43
     */
    ActionResult update(ScheduleJob scheduleJob);

    /**
     * 删除任务
     *
     * @param id id
     * @return cc.edt.frame.common.result.ActionResult
     * @author 姜宁 2019-01-24 0024 11:54:43
     */
    ActionResult delete(String id);

    /**
     * 停止任务
     *
     * @param id id
     * @return cc.edt.frame.common.result.ActionResult
     * @author 姜宁 2019-01-24 0024 11:54:43
     */
    ActionResult pauseJob(String id);

    /**
     * 启动任务
     *
     * @param id id
     * @return cc.edt.frame.common.result.ActionResult
     * @author 姜宁 2019-01-24 0024 11:54:43
     */
    ActionResult resumeJob(String id);
}
