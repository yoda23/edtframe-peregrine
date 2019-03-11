package cc.edt.frame.common.result;

import org.springframework.stereotype.Component;

/**
 * 前台页面结果
 *
 * @author 刘钢
 * @date 2017/12/18 11:19
 */
@Component
public class ActionResultService {

    /**
     * 封装统一前台页面返回
     *
     * @param success        success
     * @param result         result
     * @param code           code
     * @param message        message
     * @param attachedResult attachedResult
     * @return com.edt.common.bean.ActionResult
     * @author 刘钢
     * @date 2017-08-07 8:47
     */

    public <R, A> ActionResult<R, A> callBackResult(boolean success,
                                                    Integer code, String message, R result, A attachedResult) {
        ActionResult<R, A> actionResult = new ActionResult<>();
        // StatusResult
        actionResult.getStatus().setSuccess(success);
        actionResult.getStatus().setMessage(message);
        actionResult.getStatus().setCode(code);
        // Result
        actionResult.setResult(result);
        // AttachedResult
        actionResult.setAttached(attachedResult);
        return actionResult;
    }

    /**
     * 封装统一前台页面返回
     *
     * @param success success
     * @param code    code
     * @param message message
     * @return cc.edt.frame.common.result.ActionResult
     * @author 刘钢
     * @date 2018/12/11 14:24
     */
    public <R, A> ActionResult<R, A> callBackResult(boolean success, Integer code,
                                                    String message) {
        return callBackResult(success, code, message, null, null);
    }


    /**
     * 封装统一前台页面返回
     *
     * @return cc.edt.iceframe5.base.common.result.ActionResult
     * @author 刘钢
     * @date 2018/3/15 11:27
     */
    public <R, A> ActionResult<R, A> callBackResult() {
        return callBackResult(true, 0, "请求成功", null, null);
    }
}
