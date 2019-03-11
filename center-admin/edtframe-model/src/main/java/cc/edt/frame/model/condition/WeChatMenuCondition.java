package cc.edt.frame.model.condition;

import java.io.Serializable;

import cc.edt.frame.model.condition.params.WeChatMenuConditionParams;
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
public class WeChatMenuCondition extends FindCondition implements Serializable {

    private static final long serialVersionUID = 6300041593206548011L;
    private WeChatMenuConditionParams params;

}
