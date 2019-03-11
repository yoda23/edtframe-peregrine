package cc.edt.frame.model.entity.base;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色权限信息
 *
 * @author 刘钢
 * @date 2017/12/18 13:33
 */
@Data
public class RoleRightsLinked implements Serializable {

    private static final long serialVersionUID = -6656506076247277659L;
    private String rightsId;
    private String roleId;
}
