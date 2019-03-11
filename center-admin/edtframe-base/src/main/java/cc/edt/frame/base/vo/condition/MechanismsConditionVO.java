package cc.edt.frame.base.vo.condition;

import cc.edt.frame.common.vo.FindConditionVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 机构查询条件
 *
 * @author 刘钢
 * @date 2017/12/18 13:14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MechanismsConditionVO extends FindConditionVO {

    private static final long serialVersionUID = 8590709132583907122L;
    private String name;
    private String organizationId;
}
