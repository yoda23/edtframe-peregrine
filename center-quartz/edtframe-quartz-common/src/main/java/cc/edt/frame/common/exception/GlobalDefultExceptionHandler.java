package cc.edt.frame.common.exception;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cc.edt.frame.common.result.ActionResult;
import cc.edt.frame.common.result.ActionResultService;

/**
 * 全局异常处理
 *
 * @author 刘钢
 * @date 2018/8/23 11:28
 */
@ControllerAdvice
public class GlobalDefultExceptionHandler {
    @Resource
    private ActionResultService actionResultService;

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ActionResult defultExcepitonHandler(HttpServletRequest request,
                                               Exception e) {
        Integer code = 0;
        String message = "";
        e.printStackTrace();
        // 自定义异常信息
        if (e instanceof CustomerException) {
            return actionResultService.callBackResult(false,
                    ((CustomerException) e).getErrorCode(),
                    ((CustomerException) e).getErrorMsg());
        }
        // 没有匹配到系统default异常
        if (DefaultExceptionInfo.EXCEPTION_MAP
                .get(e.getClass().toString()) == null) {
            return actionResultService.callBackResult(false, 9999,
                    "系统出现未知异常，请联系管理员");
        }
        for (Map.Entry<Integer, String> entry : DefaultExceptionInfo.EXCEPTION_MAP
                .get(e.getClass().toString()).entrySet()) {
            code = entry.getKey();
            message = entry.getValue();
        }
        return actionResultService.callBackResult(false, code, message);
    }
}
