package cc.edt.frame.model.entity.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 角色信息
 *
 * @author 刘钢
 * @date 2017/12/18 13:31
 */
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = -2505372373474014298L;
    private String id;
    private String name;
    private String reMark;
    private String addUser;
    private String addUserName;
    private Date addTime;
    private String menuId;
    private String rightsId;
    private List<Menu> listMenu;
    private List<Rights> listRights;
}
