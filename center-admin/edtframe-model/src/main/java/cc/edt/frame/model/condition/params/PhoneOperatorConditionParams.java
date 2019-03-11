package cc.edt.frame.model.condition.params;

import java.io.Serializable;

import cc.edt.frame.model.condition.FindCondition;
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
public class PhoneOperatorConditionParams extends FindConditionParams
        implements Serializable {

    private static final long serialVersionUID = 1296628612240691021L;

    private String name;

}
