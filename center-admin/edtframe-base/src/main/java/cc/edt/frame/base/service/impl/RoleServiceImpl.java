package cc.edt.frame.base.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cc.edt.frame.common.result.ActionResult;
import cc.edt.frame.common.result.ActionResultService;
import cc.edt.frame.admin.dao.base.RoleDao;
import cc.edt.frame.base.service.RoleService;
import cc.edt.frame.model.condition.FindCondition;
import cc.edt.frame.model.entity.base.*;

/**
 * 角色管理
 *
 * @author 刘钢
 * @date 2018/8/12 10:53
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {
    @Resource
    private ActionResultService actionResultService;
    @Resource
    private RoleDao roleDao;

    @Override
    public List<Role> listRoleByCondition(FindCondition findCondition) {
        PageHelper.startPage(findCondition.getPage(), findCondition.getLimit());
        List<Role> listRole = roleDao.listRoleByCondition(findCondition);
        PageInfo<Role> pageInfo = new PageInfo<>(listRole);
        findCondition.setTotal(pageInfo.getTotal());
        if (findCondition.getPage() == 0 && findCondition.getLimit() == 0) {
            findCondition.setTotal((long) listRole.size());
        }
        return listRole;
    }

    @Override
    public ActionResult saveRole(Role role) {
        ActionResult actionResult = checkSame(role);
        if (actionResult.getStatus().isSuccess()) {
            roleDao.saveRole(role);
            actionResult = actionResultService.callBackResult(true, 0, "角色添加成功");
        }
        return actionResult;
    }

    @Override
    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }

    @Override
    public Role getRoleById(String id) {
        return roleDao.getRoleById(id);
    }

    @Override
    public ActionResult updateRole(Role role) {
        ActionResult actionResult = checkSame(role);
        if (actionResult.getStatus().isSuccess()) {
            roleDao.updateRole(role);
            actionResult = actionResultService.callBackResult(true, 0, "角色修改成功");
        }
        return actionResult;
    }

    @Override
    public ActionResult deleteRole(String id) {
        ActionResult actionResult = actionResultService.callBackResult(true, 0,
                "角色删除成功");
        roleDao.deleteRoleMenuLinked(id);
        roleDao.deleteRoleRightsLinked(id);
        roleDao.deleteRole(id);
        return actionResult;
    }

    @Override
    public Role listRoleByIdForMenu(String id) {
        return roleDao.listRoleByIdForMenu(id);
    }

    @Override
    public Role listRoleByIdForRights(String id) {
        return roleDao.listRoleByIdForRights(id);
    }

    @Override
    public ActionResult updateRoleByIdForMenu(Role role) {
        ActionResult actionResult;
        String[] menuId = role.getMenuId().split(",");
        if (StringUtils.isBlank(menuId[0])) {
            actionResult = actionResultService.callBackResult(false, -1,
                    "请选择菜单");
        } else {
            // 删除角色菜单对应信息
            roleDao.deleteRoleMenuLinked(role.getId());
            // 保存角色菜单对应关系表
            for (String aMenuId : menuId) {
                RoleMenuLinked roleMenuLinkedTemp = new RoleMenuLinked();
                Menu menu = new Menu();
                menu.setId(aMenuId);
                roleMenuLinkedTemp.setMenuId(menu.getId());
                roleMenuLinkedTemp.setRoleId(role.getId());
                roleDao.saveRoleMenuLinked(roleMenuLinkedTemp);
            }
            actionResult = actionResultService.callBackResult(true, 0,
                    "角色菜单修改成功");
        }
        return actionResult;
    }

    @Override
    public ActionResult updateRoleByIdForRights(Role role) {
        ActionResult actionResult;
        String[] rightsId = role.getRightsId().split(",");
        if (StringUtils.isBlank(rightsId[0])) {
            actionResult = actionResultService.callBackResult(false, -1,
                    "请选择权限");
        } else {
            // 删除角色权限对应表
            roleDao.deleteRoleRightsLinked(role.getId());
            // 保存权限对应关系表
            for (String aRightsId : rightsId) {
                RoleRightsLinked roleRightsLinkedTemp = new RoleRightsLinked();
                Rights rights = new Rights();
                rights.setId(aRightsId);
                roleRightsLinkedTemp.setRightsId(rights.getId());
                roleRightsLinkedTemp.setRoleId(role.getId());
                roleDao.saveRoleRightsLinked(roleRightsLinkedTemp);
            }
            actionResult = actionResultService.callBackResult(true, 0,
                    "角色相关权限修改成功");
        }
        return actionResult;
    }

    /**
     * 重复验证
     *
     * @param role role
     * @return cc.edt.frame.core.base.result.ActionResult
     * @author 刘钢
     * @date 2018/8/12 10:57
     */
    private ActionResult checkSame(Role role) {
        ActionResult actionResult = actionResultService.callBackResult();
        Role roleName = getRoleByName(role.getName());
        if (roleName != null && !roleName.getId().equals(role.getId())) {
            actionResult = actionResultService.callBackResult(false, -1,
                    "权限名称重复");
        }
        return actionResult;
    }
}
