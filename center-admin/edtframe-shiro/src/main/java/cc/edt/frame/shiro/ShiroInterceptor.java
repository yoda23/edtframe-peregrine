package cc.edt.frame.shiro;

import cc.edt.frame.model.entity.base.User;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * shiro拦截器
 *
 * @author 刘钢
 * @date 2017/12/18 13:35
 */
public class ShiroInterceptor extends AuthorizationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request,
                                      ServletResponse response, Object obj) throws Exception {
        User user = (User) getSubject(request, response).getSession()
                .getAttribute("USER");
        if (user == null) {
            // 如果是ajax请求响应头会有，x-requested-with
            if (((HttpServletRequest) request)
                    .getHeader("x-requested-with") != null
                    && "XMLHttpRequest"
                    .equalsIgnoreCase(((HttpServletRequest) request)
                            .getHeader("x-requested-with"))) {
                // 在响应头设置session状态
                ((HttpServletResponse) response).addHeader("sessionstatus",
                        "timeout");
                ((ServletResponse) response)
                        .setContentType("text/html;charset=utf-8");
                String str = "{\"success\":false,\"message\":\"用户登陆超时,请重新登录\"}";
                PrintWriter writer = ((ServletResponse) response).getWriter();
                writer.write(str);
                writer.flush();
                writer.close();
            } else {
                ((HttpServletResponse) response).sendRedirect(
                        ((HttpServletRequest) request).getContextPath()
                                + "/timeout");
            }
            return false;
        }
        return true;
    }

}
