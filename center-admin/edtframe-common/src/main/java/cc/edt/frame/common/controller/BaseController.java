package cc.edt.frame.common.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

import cc.edt.iceutils3.json.IceFastJsonUtils;

/**
 * 基础controller
 *
 * @author 刘钢
 * @date 2017/12/18 15:15
 */
@Controller
public class BaseController {
    @Resource
    protected HttpSession httpSession;
    @Resource
    protected HttpServletResponse httpServletResponse;
    @Resource
    protected HttpServletRequest httpServletRequest;

    /**
     * 转换成JSON字符串回写到页面
     *
     * @param object object
     * @author 刘钢
     * @date 2017-05-18 11:28
     */
    protected void writerToPageByJson(Object object) {
        writerToPageByJsonString(toJsonString(object));
    }

    /**
     * 转换成JSON字符串回写到页面
     *
     * @param object      object
     * @param dateformate dateformate
     * @author 刘钢
     * @date 2017-05-18 11:29
     */

    protected void writerToPageByJson(Object object, String dateformate) {
        writerToPageByJsonString(toJsonString(object, dateformate));
    }

    /**
     * 转换成JSON字符串回写到页面,不打印null属性
     *
     * @param object object
     * @author 刘钢
     * @date 2017-05-18 11:29
     */

    protected void writerToPageByJsonNoNull(Object object) {
        writerToPageByJsonString(toJsonStringNoNull(object));
    }

    /**
     * 转换成JSON字符串回写到页面,不打印null属性
     *
     * @param object      object
     * @param dateformate dateformate
     * @author 刘钢
     * @date 2017-05-18 11:29
     */

    protected void writerToPageByJsonNoNull(Object object, String dateformate) {
        writerToPageByJsonString(toJsonStringNoNull(object, dateformate));
    }

    /**
     * 根据属性过滤器，转换成JSON字符串回写到页面
     *
     * @param object      object
     * @param filter      filter
     * @param dateformate dateformate
     * @author 刘钢
     * @date 2017-05-18 11:29
     */

    protected void writerToPageByJsonByFilter(Object object,
                                              SimplePropertyPreFilter filter, String dateformate) {
        writerToPageByJsonString(toJsonStringByFilter(object, filter, dateformate));
    }

    /**
     * 根据属性过滤器，转换成JSON字符串回写到页面
     *
     * @param object object
     * @param filter filter
     * @author 刘钢
     * @date 2017-05-18 11:30
     */

    protected void writerToPageByJsonByFilter(Object object,
                                              SimplePropertyPreFilter filter) {
        writerToPageByJsonString(toJsonStringByFilter(object, filter));
    }

    /**
     * 根据属性过滤器，转换成JSON字符串回写到页面
     *
     * @param object object
     * @author 刘钢
     * @date 2017-05-18 11:30
     */

    protected void writerToPageByJsonByFilter(Object object) {
        writerToPageByJsonString(toJsonStringNoNull(object));
    }

    /**
     * 根据属性过滤器，转换成JSON字符串回写到页面,不显示null属性
     *
     * @param object object
     * @param filter filter
     * @author 刘钢
     * @date 2017-05-18 11:30
     */

    protected void writerToPageByJsonByFilterNoNull(Object object,
                                                    SimplePropertyPreFilter filter) {
        writerToPageByJsonString(toJsonStringByFilter(object, filter));
    }

    /**
     * 将字符串回写到页面
     *
     * @param str str
     * @author 刘钢
     * @date 2017-05-18 11:31
     */

    protected void writerToPageByString(String str) {
        httpServletResponse.setContentType("text/html;charset=UTF-8");
        try (PrintWriter writer = httpServletResponse.getWriter()) {
            writer.write(str);
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将字符串回写到页面
     *
     * @param str str
     * @author 刘钢
     * @date 2017-05-18 11:31
     */

    protected void writerToPageByJsonString(String str) {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        try (PrintWriter writer = httpServletResponse.getWriter()) {
            writer.write(str);
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * JSON字符串处理
     */
    private String toJsonString(Object object) {
        return IceFastJsonUtils.toJsonString(object);
    }

    private String toJsonString(Object object, String dateFormate) {
        return IceFastJsonUtils.toJsonString(object, dateFormate);
    }

    private String toJsonStringNoNull(Object object) {
        return IceFastJsonUtils.toJsonStringNoNull(object);
    }

    private String toJsonStringNoNull(Object object, String dateFormate) {
        return IceFastJsonUtils.toJsonStringNoNull(object, dateFormate);
    }

    private String toJsonStringByFilter(Object object,
                                        SimplePropertyPreFilter filter) {
        return IceFastJsonUtils.toJsonStringByFilter(object, filter);
    }

    private String toJsonStringByFilter(Object object,
                                        SimplePropertyPreFilter filter, String dateFormate) {
        return IceFastJsonUtils.toJsonStringByFilter(object, filter,
                dateFormate);
    }
}
