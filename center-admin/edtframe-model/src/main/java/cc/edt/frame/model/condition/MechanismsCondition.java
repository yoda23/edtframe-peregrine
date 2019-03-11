package cc.edt.frame.model.condition;


import cc.edt.frame.model.condition.params.MechanismsConditionParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 组织机构查询条件
 *
 * @author 刘钢
 * @date 2017/12/18 11:54
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MechanismsCondition extends FindCondition {
    private static final long serialVersionUID = 170730431738695628L;
    private MechanismsConditionParams params;

}
