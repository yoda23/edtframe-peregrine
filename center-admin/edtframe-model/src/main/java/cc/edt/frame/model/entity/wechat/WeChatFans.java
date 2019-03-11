package cc.edt.frame.model.entity.wechat;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 微信粉丝信息表实体
 *
 * @author 刘艳柔
 * @date 2017/12/20 8:41
 */
@Data
public class WeChatFans implements Serializable {

    private static final long serialVersionUID = 7622447302583482608L;
    private String id;
    private String openId;
    private String subscribe;
    private String nickName;
    private String sex;
    private String city;
    private String country;
    private String province;
    private String language;
    private String headImgUrl;
    private Date subscribeTime;
    private Date unsubscribeTime;
    private String unionId;
    private String remark;
    private String accountId;
    private String accountName;
}
