package cc.edt.frame.security.bean;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 * 小程序用户信息加密
 *
 * @author 刘钢
 * @date 2018/7/19 13:28
 */
@Data
public class UserEncrypt implements Serializable {
    private static final long serialVersionUID = -8281349586866810710L;
    @JSONField(name = "iv")
    private String iv;
    @JSONField(name = "encryptedData")
    private String encryptedData;
    @JSONField(name = "session_key")
    private String sessionKey;
}
