package cc.edt.frame.model.condition.params;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 敏感词条件参数类
 *
 * @author 姜宁
 * @date 2018-11-16 14:30:16
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SensitiveWordsConditionParams extends FindConditionParams
        implements Serializable {
    private static final long serialVersionUID = -738779161986942711L;
    /**
     * 敏感词内容
     */
    private String content;
}
