package cc.edt.frame.common.constant;

import com.google.common.collect.ImmutableList;

import java.io.Serializable;
import java.util.List;

/**
 * 系统通用常量
 *
 * @author 刘钢
 * @date 2018/10/28 13:04
 */
public class SystemConstant implements Serializable {
    private static final long serialVersionUID = -7158625757360485752L;
    /**
     * 成功标记-字符串
     */
    public final static String SUCCESS_STRING = "SUCCESS";
    /**
     * 成功标记-字符串-0
     */
    public final static String SUCCESS_STRING_0 = "0";
    /**
     * 成功标记-整形
     */
    public final static int SUCCESS_INT = 0;
    /**
     * 验证码有效期（秒）
     */
    public final static int VERIFICATION_CODE = 180;
    /**
     * http请求错误码
     */
    public final static List<Integer> REQUEST_ERROR_CODE_LIST = new ImmutableList.Builder<Integer>()
            .add(404).add(500).build();
}
