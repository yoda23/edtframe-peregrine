package cc.edt.frame.model.condition;


import cc.edt.frame.model.condition.params.WeChatAccountConditionParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 微信帐号信息条件类
 *
 * @author 刘艳柔
 * @date 2017/12/20 10:13
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WeChatAccountCondition extends FindCondition {

    private static final long serialVersionUID = 1609875536177382991L;
    private WeChatAccountConditionParams params;
}
