package cc.edt.frame.sensitivewords.vo.condition;

import cc.edt.frame.common.vo.FindConditionVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author 姜宁
 * @date 2018-11-16 0016 14:36:29
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SensitiveWordsConditionVO extends FindConditionVO
        implements Serializable {
    private static final long serialVersionUID = 4101464792219796280L;
    /**
     * 敏感词内容
     */
    private String content;
}
