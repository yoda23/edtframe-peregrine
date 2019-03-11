package cc.edt.frame.model.entity.wechat;

import java.io.Serializable;

import lombok.Data;

/**
 * 微信自定义菜单表实体
 *
 * @author 刘艳柔
 * @date 2017/12/20 8:41
 */
@Data
public class WeChatMenu implements Serializable {

    private static final long serialVersionUID = 2700212360582194792L;
    private String id;
    private String accountId;
    private String accountName;
    private String name;
    private String type;
    private String key;
    private Integer rank;
    private Double order;
    private String pid;
    private String rankName;
    private String typeName;
    private String nameDisplay;
}
