package cc.edt.frame.common.result;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * 前台页面返回结果类
 *
 * @author 刘钢
 * @date 2017/12/18 11:00
 */
@Data
public class ActionResult<R, A> implements Serializable {

    private static final long serialVersionUID = 7271810509315302832L;
    @JSONField(name = "status")
    private StatusResult status = new StatusResult();
    @JSONField(name = "result")
    private R result;
    @JSONField(name = "attached")
    private A attached;
}
