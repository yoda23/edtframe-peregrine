package cc.edt.frame.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cc.edt.frame.common.constant.SystemConstant;

/**
 * 请求错误拦截器
 *
 * @author 刘钢
 * @date 2019/1/23 13:17
 */
@Component
public class ErrorRequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws IOException {
        if (SystemConstant.REQUEST_ERROR_CODE_LIST
                .contains(response.getStatus())) {
            response.sendRedirect(request.getContextPath()
                    + "/redirect?page=error/" + response.getStatus());
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex) {

    }
}
