package cc.edt.frame.model.condition;

import cc.edt.frame.model.condition.params.SensitiveWordsConditionParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 敏感+词条件类
 *
 * @author 姜宁
 * @date 2018-11-16 14:29:00
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SensitiveWordsCondition extends FindCondition
        implements Serializable {
    private static final long serialVersionUID = -3592290210361627970L;
    private SensitiveWordsConditionParams params;
}
