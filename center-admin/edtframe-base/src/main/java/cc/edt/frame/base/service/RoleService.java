package cc.edt.frame.base.service;

import java.util.List;

import cc.edt.frame.common.result.ActionResult;
import cc.edt.frame.model.condition.FindCondition;
import cc.edt.frame.model.entity.base.Role;

/**
 * 角色信息
 *
 * @author 刘钢
 * @date 2017/12/18 13:28
 */
public interface RoleService {

    /**
     * 按照条件查询所有角色
     *
     * @param findCondition findCondition
     * @return List<Role>
     * @author 刘钢
     * @date 2017/5/17 22:48
     */
    List<Role> listRoleByCondition(FindCondition findCondition);

    /**
     * 保存角色
     *
     * @param role role
     * @return cc.edt.frame.core.base.result.ActionResult
     * @author 刘钢
     * @date 2017/5/17 22:48
     */
    ActionResult saveRole(Role role);

    /**
     * 根据角色名称查询角色信息
     *
     * @param name name
     * @return com.edt.entity.Role
     * @author 刘钢
     * @date 2017/5/17 22:48
     */
    Role getRoleByName(String name);

    /**
     * 根据角色ID查询角色信息
     *
     * @param id id
     * @return com.edt.entity.Role
     * @author 刘钢
     * @date 2017/5/17 22:48
     */
    Role getRoleById(String id);

    /**
     * 修改角色
     *
     * @param role role
     * @return cc.edt.frame.core.base.result.ActionResult
     * @author 刘钢
     * @date 2017/5/17 22:48
     */
    ActionResult updateRole(Role role);

    /**
     * 删除角色
     *
     * @param id id
     * @return cc.edt.frame.core.base.result.ActionResult
     * @author 刘钢
     * @date 2017/5/17 22:49
     */
    ActionResult deleteRole(String id);

    /**
     * 根据角色id查询菜单集合
     *
     * @param id di
     * @return com.edt.entity.Role
     * @author 刘钢
     * @date 2017/5/17 22:49
     */
    Role listRoleByIdForMenu(String id);

    /**
     * 根据角色id查询权限集合
     *
     * @param id id
     * @return com.edt.entity.Role
     * @author 刘钢
     * @date 2017/5/17 22:49
     */
    Role listRoleByIdForRights(String id);

    /**
     * 修改角色对应的菜单信息
     *
     * @param role role
     * @return com.edt.common.bean.ActionResult
     * @author 刘钢 2017-05-18 11:47
     */

    ActionResult updateRoleByIdForMenu(Role role);

    /**
     * 修改角色对应的权限集合
     *
     * @param role role
     * @return com.edt.common.bean.ActionResult
     * @author 刘钢 2017-05-18 11:48
     */

    ActionResult updateRoleByIdForRights(Role role);
}
