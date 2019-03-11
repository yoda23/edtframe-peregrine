package cc.edt.frame.model.entity.wechat;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 微信账号配置表实体
 *
 * @author 刘艳柔
 * @date 2017/12/20 8:41
 */
@Data
public class WeChatAccount implements Serializable {

    private static final long serialVersionUID = 6653539726265488198L;
    private String id;
    private String mechanismsId;
    private String mechanismsName;
    private String originalId;
    private String appId;
    private String appSecret;
    private String shopId;
    private String shopKey;
    private String certificatePath;
    private String token;
    private String tokenFlag;
    private String name;
    private Integer type;
    private String typeName;
    private String addUser;
    private String addUserName;
    private Date addTime;
    private String picPath;
}
