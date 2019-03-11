package cc.edt.frame.model.entity.base;

import lombok.Data;

import java.io.Serializable;

/**
 * 菜单信息
 *
 * @author 刘钢
 * @date 2017/12/18 13:30
 */
@Data
public class Menu implements Serializable {
    private static final long serialVersionUID = -6320499576453492701L;
    private String id;
    private String name;
    private String openUrl;
    private String parentId;
    private String icon;

}
