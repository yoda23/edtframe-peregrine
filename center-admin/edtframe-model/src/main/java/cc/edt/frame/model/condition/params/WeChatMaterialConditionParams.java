package cc.edt.frame.model.condition.params;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 微信素材条件参数类
 *
 * @author 姜宁
 * @date 2018/9/12 14:23:56
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WeChatMaterialConditionParams extends FindConditionParams {

    private static final long serialVersionUID = 7887509167174900293L;
    private String accountId;
}
