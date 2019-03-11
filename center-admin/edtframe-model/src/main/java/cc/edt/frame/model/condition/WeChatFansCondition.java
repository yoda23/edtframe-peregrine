package cc.edt.frame.model.condition;


import cc.edt.frame.model.condition.params.WeChatFansConditionParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 微信粉丝信息条件类
 *
 * @author 刘艳柔
 * @date 2017/12/20 10:13
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WeChatFansCondition extends FindCondition {
    private static final long serialVersionUID = 8915621144267396782L;
    private WeChatFansConditionParams params;
}
