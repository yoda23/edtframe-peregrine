package cc.edt.frame.common.result;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 * 前台页面返回结果类
 *
 * @author 刘钢
 * @date 2017/12/18 11:00
 */
@Data
public class ActionResult<R, A> implements Serializable {
    private static final long serialVersionUID = 8485695442997656564L;
    @JSONField(name = "status")
    private StatusResult status;
    @JSONField(name = "result")
    private R result;
    @JSONField(name = "attached")
    private A attached;
}
