package cc.edt.frame.model.condition;

import java.io.Serializable;

import cc.edt.frame.model.condition.params.FindConditionParams;
import cc.edt.frame.model.condition.params.PhoneOperatorConditionParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 手机运营商
 *
 * @author 刘钢
 * @date 2017/12/18 11:58
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PhoneOperatorCondition extends FindCondition
        implements Serializable {
    private static final long serialVersionUID = -3218593847373712545L;
    private PhoneOperatorConditionParams params;
}
