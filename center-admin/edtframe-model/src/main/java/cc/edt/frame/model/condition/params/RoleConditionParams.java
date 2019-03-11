package cc.edt.frame.model.condition.params;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 角色条件参数
 *
 * @author 姜宁
 * @date 2018/8/21 16:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleConditionParams extends FindConditionParams
        implements Serializable {
    private static final long serialVersionUID = 6941190451736141169L;
    private String roleName;
}
