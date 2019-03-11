package cc.edt.frame.interceptor;

import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;

import cc.edt.frame.common.constant.AppIdDictionary;
import cc.edt.frame.common.controller.BaseController;
import cc.edt.frame.common.controller.requestbody.RequestBodyHelper;
import cc.edt.frame.common.exception.CustomerException;
import cc.edt.frame.common.exception.CustomerExceptionCode;
import cc.edt.frame.common.result.ActionResultService;
import cn.hutool.core.util.URLUtil;

/**
 * API签名拦截器
 *
 * @author 刘钢
 * @date 2018/9/20 10:37
 */
@Component
public class ApiSignInterceptor extends BaseController
        implements HandlerInterceptor {
    @Resource
    private ActionResultService actionResultService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) {
        String body = RequestBodyHelper.getBodyString(request);
        Map<String, String> params;
        try {
            params = JSONObject.parseObject(body,
                    new TypeReference<TreeMap<String, String>>() {
                    }, Feature.OrderedField);
        } catch (JSONException jsonException) {
            throw new CustomerException(
                    CustomerExceptionCode.API_SIGN_EXCEPTION_CODE,
                    CustomerExceptionCode.API_SIGN_EXCEPTION_MESSAGE);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (!"sign".equals(entry.getKey())) {
                stringBuilder.append(entry.getKey()).append("=")
                        .append(entry.getValue()).append("&");
            }
        }
        stringBuilder.append("key=")
                .append(AppIdDictionary.APPID_MAP.get(params.get("appid")));
        String urlEncode = URLUtil.encode(stringBuilder.toString());
        String sign = DigestUtils.md5Hex(urlEncode).toUpperCase();
        if (params.get("sign").equals(sign)) {
            return true;
        }
        writerToPageByJsonNoNull(
                actionResultService.callBackResult(false, -1, "签名错误"));
        return false;
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
