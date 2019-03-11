package cc.edt.frame.model.entity.wechat;

import java.io.Serializable;

import lombok.Data;

/**
 * 图文素材的素材实体类
 *
 * @author 姜宁
 * @date 2018-8-30 11:48:27
 */
@Data
public class WeChatMaterialMp implements Serializable {
    private static final long serialVersionUID = -2687192997897059592L;
    private String id;
    private String materialId;
    private String mediaId;
    private String title;
    private String thumbMediaId;
    private Integer showCoverPic;
    private String author;
    private String digest;
    private String content;
    private String url;
    private String contentSourceUrl;
    private String materialName;

}
