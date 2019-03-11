package cc.edt.frame.wechat.vo.condition;

import cc.edt.frame.common.vo.FindConditionVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 微信粉丝条件类
 *
 * @author 刘艳柔
 * @date 2017/12/20 10:13
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WeChatFansConditionVO extends FindConditionVO {

    private static final long serialVersionUID = 7258004760888808216L;
    private String startTime;
    private String endTime;
    private String accountId;
    private String name;
    private String state;
    private String subscribe;

}
