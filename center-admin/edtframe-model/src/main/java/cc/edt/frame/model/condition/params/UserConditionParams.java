package cc.edt.frame.model.condition.params;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 刘钢
 * @date 2018/8/10 9:21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserConditionParams extends FindConditionParams {
    private static final long serialVersionUID = 3337361926271324726L;
    private String name;
}
