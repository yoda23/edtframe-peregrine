package cc.edt.frame.common.constant;

import java.io.Serializable;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

/**
 * 微信常量
 *
 * @author 奚艺轩
 * @date 2018/10/24 10:39
 */
public class WeChatDictionary implements Serializable {
    private static final long serialVersionUID = -6893411752179481681L;
    public static Map<String, String> WECHAT_DICTIONARY_MAP;
    /**
     * 微信公众号appid
     */
    public final static String WECHAT_APPID = "wxb3bd3e9118b993ef";
    /**
     * 小程序appid
     */
    public final static String WECHAT_MINI_APPID = "wxa207058baa4927cf";
    /**
     * 小程序Appsecret
     */
    public final static String WECHAT_MINI_APPSECRET = "c3d9bbb00242b08bc458607737e7a386";

    static {
        ImmutableMap.Builder<String, String> builder = new ImmutableMap.Builder<>();
        builder.put(WECHAT_MINI_APPID, WECHAT_MINI_APPSECRET);
        WECHAT_DICTIONARY_MAP = builder.build();
    }
}
