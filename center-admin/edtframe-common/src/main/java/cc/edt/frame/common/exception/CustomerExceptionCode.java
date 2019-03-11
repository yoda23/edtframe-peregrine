package cc.edt.frame.common.exception;

import java.io.Serializable;

/**
 * 自定义异常编号
 *
 * @author 刘钢
 * @date 2018/9/19 21:51
 */
public class CustomerExceptionCode implements Serializable {

    private static final long serialVersionUID = -8817821081307716919L;
    // 9999-系统未知异常
    // 9500-9998-jdk系统异常信息
    /**
     * http请求超时
     */
    public final static Integer NETWORK_EXCEPTION_CODE = 9500;
    public final static String NETWORK_EXCEPTION_MESSAGE = "网络请求超时，请稍后重试";

}
