package cc.edt.frame.base.bo;

import java.util.List;

import lombok.Data;

/**
 * 主页菜单权限对应菜单
 *
 * @author 刘钢
 * @date 2017/12/18 11:53
 */
@Data
public class IndexRoleMenu {

    private String id;
    private String pid;
    private String name;
    private String openUrl;
    private String icon;
    private List<IndexRoleMenu> listMenuChild;
}
