package cc.edt.frame.model.condition.params;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 调度器条件参数类
 *
 * @author 姜宁
 * @date 2019-01-24 9:42:27
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ScheduleJobConditionParams extends FindConditionParams {
    private static final long serialVersionUID = -9114982594397345599L;
}
