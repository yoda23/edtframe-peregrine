package cc.edt.frame.admin.dao.base;


import java.util.List;

import cc.edt.frame.model.condition.FindCondition;
import cc.edt.frame.model.entity.base.Role;
import cc.edt.frame.model.entity.base.RoleMenuLinked;
import cc.edt.frame.model.entity.base.RoleRightsLinked;

/**
 * 角色信息
 *
 * @author 刘钢
 * @date 2017/12/18 13:28
 */
public interface RoleDao {

    /**
     * 按照条件查询所有角色
     *
     * @param condition condition
     * @return List<Role>
     * @author 刘钢
     * @date 2017/5/17 22:48
     */
    List<Role> listRoleByCondition(FindCondition condition);

    /**
     * 保存角色
     *
     * @param role role
     * @author 刘钢
     * @date 2017/5/17 22:48
     */
    void saveRole(Role role);

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
     * @author 刘钢
     * @date 2017/5/17 22:48
     */
    void updateRole(Role role);

    /**
     * 删除角色
     *
     * @param id id
     * @author 刘钢
     * @date 2017/5/17 22:49
     */
    void deleteRole(String id);

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
     * 保存角色和菜单之间关系
     *
     * @param roleMenuLinked roleMenuLinked
     * @author 刘钢
     * @date 2017/5/17 22:49
     */
    void saveRoleMenuLinked(RoleMenuLinked roleMenuLinked);

    /**
     * 删除角色和菜单之间关系
     *
     * @param roleId roleId
     * @author 刘钢
     * @date 2017/5/17 22:50
     */
    void deleteRoleMenuLinked(String roleId);

    /**
     * 保存角色和权限之间关系
     *
     * @param roleRightsLinked roleRightsLinked
     * @author 刘钢
     * @date 2017/5/17 22:49
     */
    void saveRoleRightsLinked(RoleRightsLinked roleRightsLinked);

    /**
     * 删除角色和权限之间关系
     *
     * @param roleId roleId
     * @author 刘钢
     * @date 2017/5/17 22:50
     */
    void deleteRoleRightsLinked(String roleId);
}
