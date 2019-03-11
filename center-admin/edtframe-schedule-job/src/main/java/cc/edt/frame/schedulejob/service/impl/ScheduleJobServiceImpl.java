package cc.edt.frame.schedulejob.service.impl;

import cc.edt.frame.common.constant.CommonDictionary;
import cc.edt.frame.common.result.ActionResult;
import cc.edt.frame.common.result.ActionResultService;
import cc.edt.frame.common.result.DatasResult;
import cc.edt.frame.admin.dao.schedulejob.ScheduleJobDao;
import cc.edt.frame.model.condition.FindCondition;
import cc.edt.frame.model.entity.schedulejob.ScheduleJob;
import cc.edt.frame.schedulejob.service.ScheduleJobService;
import cc.edt.iceutils3.json.IceFastJsonUtils;
import cc.edt.iceutils3.net.IceOkHttpUtils;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * 调度器service接口实现类
 *
 * @author 姜宁
 * @date 2019-01-24 9:49:18
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class ScheduleJobServiceImpl implements ScheduleJobService {

    @Resource
    private CommonDictionary commonDictionary;
    @Resource
    private ScheduleJobDao scheduleJobDao;
    @Resource
    private ActionResultService actionResultService;
    /**
     * 执行成功状态码
     */
    private final static Integer SUCCESS_CODE = 0;

    @Override
    public List<ScheduleJob> listScheduleJobByCondition(
            FindCondition condition) {
        PageHelper.startPage(condition.getPage(), condition.getLimit());
        List<ScheduleJob> listScheduleJob = scheduleJobDao
                .listScheduleJobByCondition(condition);
        PageInfo<ScheduleJob> pageInfo = new PageInfo<>(listScheduleJob);
        condition.setTotal(pageInfo.getTotal());
        return listScheduleJob;
    }

    @Override
    public ScheduleJob getScheduleJobById(String id) {
        return scheduleJobDao.getScheduleJobById(id);
    }

    @Override
    public ActionResult add(ScheduleJob scheduleJob) {
        // 检查任务名称和任务组是否重复
        ScheduleJob scheduleJobForCheckJobName = scheduleJobDao
                .getScheduleJobByJobName(scheduleJob.getJobName());
        ScheduleJob scheduleJobForCheckJobGroup = scheduleJobDao
                .getScheduleJobByJobGroup(scheduleJob.getJobGroup());
        if (scheduleJobForCheckJobName != null) {
            return actionResultService
                    .<DatasResult<ScheduleJob>, String>callBackResult(false, 0,
                            "任务添加失败,任务名称重复");
        }
        if (scheduleJobForCheckJobGroup != null) {
            return actionResultService
                    .<DatasResult<ScheduleJob>, String>callBackResult(false, 0,
                            "任务添加失败,任务组重复");
        }
        // 检查通过后的操作
        ActionResult actionResult = new ActionResult();
        String url, postValue;
        url = commonDictionary.getQuartzServer() + "/scheduleJob/add";
        postValue = IceFastJsonUtils.toJsonStringNoNull(scheduleJob);
        try {
            actionResult = sendRequest(url, postValue);
            if ("stop".equals(scheduleJob.getJobStatus())) {
                url = commonDictionary.getQuartzServer() + "/scheduleJob/pauseJob";
                sendRequest(url, postValue);
            }
        } catch (NoSuchAlgorithmException | IOException
                | KeyManagementException e) {
            e.printStackTrace();
        }
        if (actionResult.getStatus().getCode() == null) {
            actionResult = actionResultService
                    .<DatasResult<ScheduleJob>, String>callBackResult(true, 0,
                            "任务启动失败,可能原因:连接任务执行器超时");
        }
        if (SUCCESS_CODE.equals(actionResult.getStatus().getCode())) {
            scheduleJobDao.saveScheduleJob(scheduleJob);
        }
        return actionResult;
    }

    @Override
    public ActionResult update(ScheduleJob scheduleJob) {
        // 检查任务名称和任务组是否重复
        ScheduleJob scheduleJobForCheckJobName = scheduleJobDao
                .getScheduleJobByJobName(scheduleJob.getJobName());
        ScheduleJob scheduleJobForCheckJobGroup = scheduleJobDao
                .getScheduleJobByJobGroup(scheduleJob.getJobGroup());
        if (scheduleJobForCheckJobName != null && !scheduleJobForCheckJobName
                .getId().equals(scheduleJob.getId())) {
            return actionResultService
                    .<DatasResult<ScheduleJob>, String>callBackResult(false, 0,
                            "任务修改失败,任务名称重复");
        }
        if (scheduleJobForCheckJobGroup != null && !scheduleJobForCheckJobGroup
                .getId().equals(scheduleJob.getId())) {
            return actionResultService
                    .<DatasResult<ScheduleJob>, String>callBackResult(false, 0,
                            "任务修改失败,任务组重复");
        }
        String url = commonDictionary.getQuartzServer() + "/scheduleJob/update";
        String postValue = IceFastJsonUtils.toJsonStringNoNull(scheduleJob);
        ActionResult actionResult = new ActionResult();
        try {
            actionResult = sendRequest(url, postValue);
        } catch (NoSuchAlgorithmException | IOException
                | KeyManagementException e) {
            e.printStackTrace();
        }
        if (actionResult.getStatus().getCode() == null) {
            return actionResultService
                    .<DatasResult<ScheduleJob>, String>callBackResult(true, 0,
                            "任务修改失败,可能原因:连接任务执行器超时");
        }
        if (SUCCESS_CODE.equals(actionResult.getStatus().getCode())) {
            scheduleJobDao.updateScheduleJob(scheduleJob);
        }
        return actionResult;
    }

    @Override
    public ActionResult delete(String id) {
        ScheduleJob scheduleJob = scheduleJobDao.getScheduleJobById(id);
        String url = commonDictionary.getQuartzServer() + "/scheduleJob/delete";
        String postValue = IceFastJsonUtils.toJsonStringNoNull(scheduleJob);
        ActionResult actionResult = new ActionResult();
        try {
            actionResult = sendRequest(url, postValue);
        } catch (NoSuchAlgorithmException | IOException
                | KeyManagementException e) {
            e.printStackTrace();
        }
        if (actionResult.getStatus().getCode() == null) {
            actionResult = actionResultService
                    .<DatasResult<ScheduleJob>, String>callBackResult(true, 0,
                            "任务删除失败,可能原因:连接任务执行器超时");
        }
        if (SUCCESS_CODE.equals(actionResult.getStatus().getCode())) {
            scheduleJobDao.deleteScheduleJobById(id);
        }
        return actionResult;
    }

    @Override
    public ActionResult pauseJob(String id) {
        ScheduleJob scheduleJob = scheduleJobDao.getScheduleJobById(id);
        String url = commonDictionary.getQuartzServer()
                + "/scheduleJob/pauseJob";
        String postValue = IceFastJsonUtils.toJsonStringNoNull(scheduleJob);
        ActionResult actionResult = new ActionResult();
        try {
            actionResult = sendRequest(url, postValue);
        } catch (NoSuchAlgorithmException | IOException
                | KeyManagementException e) {
            e.printStackTrace();
        }
        if (actionResult.getStatus().getCode() == null) {
            return actionResultService
                    .<DatasResult<ScheduleJob>, String>callBackResult(true, 0,
                            "任务暂停失败,可能原因:连接任务执行器超时");
        }
        if (SUCCESS_CODE.equals(actionResult.getStatus().getCode())) {
            scheduleJob.setJobStatus("stop");
            scheduleJobDao.updateScheduleJobStatusById(scheduleJob);
        }
        return actionResult;
    }

    @Override
    public ActionResult resumeJob(String id) {
        ActionResult actionResult = new ActionResult();
        ScheduleJob scheduleJob = scheduleJobDao.getScheduleJobById(id);
        String url, postValue;
        url = commonDictionary.getQuartzServer() + "/scheduleJob/resumeJob";
        postValue = IceFastJsonUtils.toJsonStringNoNull(scheduleJob);
        try {
            actionResult = sendRequest(url, postValue);
        } catch (NoSuchAlgorithmException | IOException
                | KeyManagementException e) {
            e.printStackTrace();
        }
        if (actionResult.getStatus().getCode() == null) {
            actionResult = actionResultService
                    .<DatasResult<ScheduleJob>, String>callBackResult(true, 0,
                            "任务启动失败,可能原因:连接任务执行器超时");
        }
        if (SUCCESS_CODE.equals(actionResult.getStatus().getCode())) {
            scheduleJob.setJobStatus("running");
            scheduleJobDao.updateScheduleJobStatusById(scheduleJob);
        }
        return actionResult;
    }

    /**
     * 发送http请求
     *
     * @param url       url
     * @param postValue postValue
     * @return cc.edt.frame.common.result.ActionResult
     * @author 姜宁 2019-01-24 0024 9:58:19
     */
    private static ActionResult sendRequest(String url, String postValue)
            throws NoSuchAlgorithmException, IOException,
            KeyManagementException {
        String result = IceOkHttpUtils.doHttpPost(url, postValue, 30000,
                IceOkHttpUtils.JSON_PLAIN, null);
        return JSON.parseObject(result, ActionResult.class);
    }
}
