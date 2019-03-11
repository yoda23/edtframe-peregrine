package cc.edt.frame.model.entity.wechat;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 微信永久素材实体类
 *
 * @author 姜宁
 * @date 2018-8-30 11:42:50
 */
@Data
public class WeChatMaterial implements Serializable {
    private static final long serialVersionUID = 3345865337330553540L;
    private String id;
    private String accountId;
    private String name;
    private String type;
    private String mediaId;
    private Date updateTime;
    private String url;
    private String accountName;
    private String typeName;
}
