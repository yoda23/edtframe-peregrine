package cc.edt.frame.model.condition.params;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 微信图文素材条件参数类
 *
 * @author 姜宁
 * @date 2018/9/12 14:23:56
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WeChatMaterialMpConditionParams extends FindConditionParams {

    private static final long serialVersionUID = -5379399646551595905L;
    /**
     * 素材ID
     */
    private String materialId;
}
