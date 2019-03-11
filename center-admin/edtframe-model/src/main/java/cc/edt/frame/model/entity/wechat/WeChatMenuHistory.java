package cc.edt.frame.model.entity.wechat;

import java.io.Serializable;

import lombok.Data;

/**
 * 微信自定义菜单历史表实体
 *
 * @author 刘艳柔
 * @date 2017/12/20 8:41
 */
@Data
public class WeChatMenuHistory implements Serializable {

    private static final long serialVersionUID = -5785191576306087382L;
    private String id;
    private String accountId;
    private String menuId;
    private String name;
    private String type;
    private String typeName;
    private String key;
    private Integer rank;
    private String rankName;
    private Double order;
    private String pid;
    private String bakname;
    private String rankDisplay;
    private String accountName;

}
