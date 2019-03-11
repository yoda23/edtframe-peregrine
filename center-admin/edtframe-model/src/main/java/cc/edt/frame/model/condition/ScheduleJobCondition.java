package cc.edt.frame.model.condition;

import cc.edt.frame.model.condition.params.ScheduleJobConditionParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 调度器条件类
 *
 * @author 姜宁
 * @date 2019-01-24 9:41:49
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ScheduleJobCondition extends FindCondition {
    private static final long serialVersionUID = 4753591465951170793L;
    private ScheduleJobConditionParams params;
}
