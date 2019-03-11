package cc.edt.frame.model.condition;


import cc.edt.frame.model.condition.params.UserConditionParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户查询条件
 *
 * @author 刘钢
 * @date 2017/12/18 13:14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserCondition extends FindCondition {

    private static final long serialVersionUID = -8441535925204681321L;
    private UserConditionParams params;

}
