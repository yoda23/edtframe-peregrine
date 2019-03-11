package cc.edt.frame.model.entity.schedulejob;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 调度器实体类
 *
 * @author 姜宁
 * @date 2018/12/20 10:42
 */
@Data
public class ScheduleJob implements Serializable {
    private static final long serialVersionUID = 1389940468188967790L;
    /**
     * 主键ID
     */
    private String id;
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 任务状态
     */
    private String jobStatus;
    /**
     * 任务分组
     */
    private String jobGroup;
    /**
     * 任务表达式
     */
    private String cronExpression;
    /**
     * 任务备注
     */
    private String description;
    /**
     * 类名称
     */
    private String beanName;
    /**
     * 方法名称
     */
    private String methodName;
    /**
     * 添加时间
     */
    private Date addTime;
}