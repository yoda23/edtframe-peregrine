package cc.edt.frame.model.condition.params;

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
public class WeChatAccountConditionParams extends FindConditionParams {

    private static final long serialVersionUID = 5293114589723711536L;
    private String name;

}
