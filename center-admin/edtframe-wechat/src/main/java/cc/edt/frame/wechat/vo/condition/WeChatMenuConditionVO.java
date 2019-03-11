package cc.edt.frame.wechat.vo.condition;

import java.io.Serializable;

import cc.edt.frame.common.vo.FindConditionVO;
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
public class WeChatMenuConditionVO extends FindConditionVO
        implements Serializable {

    private static final long serialVersionUID = -1978671805235595015L;
    private String accountId;
}
