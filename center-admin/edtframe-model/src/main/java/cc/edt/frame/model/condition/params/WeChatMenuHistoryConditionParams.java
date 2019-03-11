package cc.edt.frame.model.condition.params;

import java.io.Serializable;

import cc.edt.frame.model.condition.FindCondition;
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
public class WeChatMenuHistoryConditionParams extends FindConditionParams
        implements Serializable {

    private static final long serialVersionUID = 7734357110167969739L;
    private String title;
    private String pid;
    private String accountId;
}
