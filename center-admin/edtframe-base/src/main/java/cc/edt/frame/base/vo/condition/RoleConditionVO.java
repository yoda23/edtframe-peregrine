package cc.edt.frame.base.vo.condition;

import cc.edt.frame.common.vo.FindConditionVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色查询条件
 *
 * @author 刘钢
 * @date 2017/12/18 13:14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleConditionVO extends FindConditionVO {

    private static final long serialVersionUID = -8039715786963763691L;
    private String roleName;
}
