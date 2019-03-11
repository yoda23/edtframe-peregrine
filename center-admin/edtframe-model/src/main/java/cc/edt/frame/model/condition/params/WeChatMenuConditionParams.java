package cc.edt.frame.model.condition.params;

import java.io.Serializable;

import cc.edt.frame.model.condition.FindCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 微信菜单条件类
 *
 * @author 刘艳柔
 * @date 2017/12/20 10:13
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WeChatMenuConditionParams extends FindConditionParams
        implements Serializable {

    private static final long serialVersionUID = -2168933526008038277L;
    private String accountId;

}
