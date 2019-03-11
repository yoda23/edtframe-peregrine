package cc.edt.frame.base.vo.condition;

import cc.edt.frame.common.vo.FindConditionVO;
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
public class UserConditionVO extends FindConditionVO {

    private static final long serialVersionUID = 3406966374739457215L;
    private String name;
}
