package cc.edt.frame.model.condition.params;

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
public class WeChatFansConditionParams extends FindConditionParams {

    private static final long serialVersionUID = -6512605412853417544L;
    private String accountId;
    private String startTime;
    private String endTime;
    private String name;
    private String state;
    private String subscribe;

}
