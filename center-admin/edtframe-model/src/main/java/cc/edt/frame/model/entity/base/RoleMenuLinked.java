package cc.edt.frame.model.entity.base;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色菜单信息
 *
 * @author 刘钢
 * @date 2017/12/18 13:32
 */
@Data
public class RoleMenuLinked implements Serializable {
    private static final long serialVersionUID = -7882381883582590212L;
    private String roleId;
    private String menuId;
}
