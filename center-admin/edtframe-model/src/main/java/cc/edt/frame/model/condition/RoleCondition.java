package cc.edt.frame.model.condition;


import cc.edt.frame.model.condition.params.RoleConditionParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 角色条件类
 *
 * @author 姜宁
 * @date 2018/8/21 16:34
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleCondition extends FindCondition implements Serializable {

    private static final long serialVersionUID = -8792071072053634605L;
    private RoleConditionParams params;

}
