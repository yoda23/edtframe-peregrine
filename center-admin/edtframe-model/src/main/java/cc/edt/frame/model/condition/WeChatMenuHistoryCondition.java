package cc.edt.frame.model.condition;

import java.io.Serializable;

import cc.edt.frame.model.condition.params.WeChatMenuHistoryConditionParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 历史菜单条件类
 *
 * @author 刘艳柔
 * @date 2017/12/20 10:13
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WeChatMenuHistoryCondition extends FindCondition
        implements Serializable {

    private static final long serialVersionUID = 7734357110167969739L;
    private WeChatMenuHistoryConditionParams params;
}
