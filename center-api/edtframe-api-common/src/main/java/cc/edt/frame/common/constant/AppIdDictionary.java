package cc.edt.frame.common.constant;

import java.io.Serializable;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

/**
 * 接口签名AppId字典
 *
 * @author 刘钢
 * @date 2018/11/26 15:20
 */
public class AppIdDictionary implements Serializable {
    private static final long serialVersionUID = -4218381363464217518L;
    public static Map<String, String> APPID_MAP;

    static {
        ImmutableMap.Builder<String, String> appIdMap = new ImmutableMap.Builder<>();
        appIdMap.put("123", "1234567890");
        APPID_MAP = appIdMap.build();
    }
}
