package cc.edt.frame.schedulejob.controller;

import cc.edt.frame.common.controller.BaseController;
import cc.edt.frame.common.result.TableResultService;
import cc.edt.frame.model.condition.ScheduleJobCondition;
import cc.edt.frame.model.condition.params.ScheduleJobConditionParams;
import cc.edt.frame.model.entity.schedulejob.ScheduleJob;
import cc.edt.frame.schedulejob.service.ScheduleJobService;
import cc.edt.frame.schedulejob.vo.condition.ScheduleJobConditionVO;
import cc.edt.iceutils3.json.IceFastJsonUtils;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author 姜宁
 * @date 2019-01-24 0024 9:59:31
 */
@Controller
@RequestMapping("/scheduleJob")
@Slf4j
public class ScheduleJobController extends BaseController {

    @Resource
    private ScheduleJobService scheduleJobService;
    @Resource
    private TableResultService tableResultService;

    /**
     * 添加任务
     *
     * @param scheduleJob scheduleJob
     * @author 姜宁 2019-01-24 10:00:58
     */
    @PostMapping("/add")
    @ResponseBody
    public void add(ScheduleJob scheduleJob) {
        if ("ON".equals(scheduleJob.getJobStatus())) {
            scheduleJob.setJobStatus("running");
        } else {
            scheduleJob.setJobStatus("stop");
        }
        scheduleJob.setId(IdUtil.simpleUUID());
        scheduleJob.setAddTime(new Date());
        writerToPageByJsonNoNull(scheduleJobService.add(scheduleJob));
    }

    /**
     * 根据条件查询任务信息
     *
     * @param conditionVO conditionVO
     * @author 姜宁 2019-01-24 0024 14:19:44
     */
    @PostMapping("/condition")
    @ResponseBody
    public void listScheduleJobByCondition(ScheduleJobConditionVO conditionVO) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder()
                .build();
        ScheduleJobCondition scheduleJobCondition = mapperFactory
                .getMapperFacade().map(conditionVO, ScheduleJobCondition.class);
        ScheduleJobConditionParams scheduleJobConditionParams = mapperFactory
                .getMapperFacade()
                .map(conditionVO, ScheduleJobConditionParams.class);
        scheduleJobCondition.setParams(scheduleJobConditionParams);
        List<ScheduleJob> listScheduleJob = scheduleJobService
                .listScheduleJobByCondition(scheduleJobCondition);
        writerToPageByJsonNoNull(tableResultService
                .tableResult(scheduleJobCondition.getTotal(), listScheduleJob));
    }

    /**
     * 根据ID删除任务
     *
     * @param id id
     * @author 姜宁 2019-01-24 0024 14:22:45
     */
    @PostMapping("/delete/{id}")
    @ResponseBody
    public void delete(@PathVariable("id") String id) {
        writerToPageByJsonNoNull(scheduleJobService.delete(id));
    }

    /**
     * 修改任务
     *
     * @param scheduleJob scheduleJob
     * @author 姜宁 2019-01-24 0024 14:22:45
     */
    @PostMapping("/update")
    @ResponseBody
    public void delete(ScheduleJob scheduleJob) {
        writerToPageByJsonNoNull(scheduleJobService.update(scheduleJob));
    }

    /**
     * 根据ID跳转到修改页面
     *
     * @param id    id
     * @param model model
     * @return java.lang.String
     * @author 姜宁 2019-01-24 0024 14:38:57
     */
    @GetMapping("/toUpdate/{id}")
    public String toUpdate(@PathVariable("id") String id, Model model) {
        ScheduleJob scheduleJob = scheduleJobService.getScheduleJobById(id);
        if (scheduleJob != null) {
            model.addAttribute("scheduleJob", scheduleJob);
            return "scheduleJob/scheduleJobUpdate";
        }
        return "none";
    }

    /**
     * 根据ID停止任务
     *
     * @param id id
     * @author 姜宁 2019-01-24 14:40:15
     */
    @PostMapping("/pauseJob/{id}")
    @ResponseBody
    public void pauseJob(@PathVariable("id") String id) {
        writerToPageByJsonNoNull(scheduleJobService.pauseJob(id));
    }

    /**
     * 根据ID启动任务
     *
     * @param id id
     * @author 姜宁 2019-01-24 14:40:15
     */
    @PostMapping("/resumeJob/{id}")
    @ResponseBody
    public void resumeJob(@PathVariable("id") String id) {
        writerToPageByJsonNoNull(scheduleJobService.resumeJob(id));
    }

}
