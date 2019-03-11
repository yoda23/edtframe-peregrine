package cc.edt.frame.base.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cc.edt.frame.common.vo.SelectVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.google.common.collect.Lists;

import cc.edt.frame.base.service.RoleService;
import cc.edt.frame.base.vo.condition.RoleConditionVO;
import cc.edt.frame.common.controller.BaseController;
import cc.edt.frame.common.result.ActionResult;
import cc.edt.frame.common.result.ActionResultService;
import cc.edt.frame.common.result.TableResultService;
import cc.edt.frame.model.condition.RoleCondition;
import cc.edt.frame.model.condition.params.RoleConditionParams;
import cc.edt.frame.model.entity.base.Role;
import cc.edt.frame.model.entity.base.User;
import cn.hutool.core.util.IdUtil;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * 角色管理
 *
 * @author 刘钢
 * @date 2018/8/12 12:15
 */
@Controller
@CrossOrigin
@RequestMapping("/role")
public class RoleController extends BaseController {
    @Resource
    private ActionResultService actionResultService;
    @Resource
    private RoleService roleService;
    @Resource
    private TableResultService tableResultService;

    /**
     * 添加
     *
     * @param role role
     * @author 刘钢
     * @date 2018/8/14 16:19
     */
    @PostMapping("/save")
    @ResponseBody
    public void saveRole(Role role) {
        User user = (User) httpSession.getAttribute("USER");
        role.setId(IdUtil.simpleUUID());
        role.setAddTime(new Date());
        role.setAddUser(user.getId());
        writerToPageByJsonNoNull(roleService.saveRole(role));
    }

    /**
     * 修改
     *
     * @param role role
     * @author 刘钢
     * @date 2018/8/14 16:20
     */
    @PostMapping("/update")
    @ResponseBody
    public void updateMechanisms(Role role) {
        writerToPageByJsonNoNull(roleService.updateRole(role));
    }

    /**
     * 删除
     *
     * @param id id
     * @author 刘钢
     * @date 2018/8/14 16:22
     */
    @PostMapping("/delete/{id}")
    @ResponseBody
    public void deleteMechanisms(@PathVariable("id") String id) {
        writerToPageByJsonNoNull(roleService.deleteRole(id));
    }

    /**
     * 根据ID获取信息
     *
     * @param id id
     * @author 刘钢
     * @date 2018/8/14 16:37
     */
    @GetMapping("/toUpdate/{id}")
    public String toUpdateRole(@PathVariable("id") String id, Model model) {
        Role role = roleService.getRoleById(id);
        if (role != null) {
            model.addAttribute(role);
            return "role/roleUpdate";
        } else {
            return "none";
        }
    }

    /**
     * 跳转到给角色配置菜单页面
     *
     * @param id id
     * @author 刘钢
     * @date 2018/8/12 12:22
     */
    @GetMapping("/menu/{id}/update")
    public String toUpdateMenu(@PathVariable("id") String id, Model model) {
        Role role = roleService.listRoleByIdForMenu(id);
        if (role != null) {
            StringBuilder menuId = new StringBuilder();
            for (int i = 0; i < role.getListMenu().size(); i++) {
                if (i + 1 == role.getListMenu().size()) {
                    menuId.append(role.getListMenu().get(i).getId());
                } else {
                    menuId.append(role.getListMenu().get(i).getId())
                            .append(",");
                }
            }
            role.setMenuId(menuId.toString());
            model.addAttribute(role);
            return "role/roleMenu";
        } else {
            return "none";
        }
    }

    /**
     * 跳转到给角色配置权限页面
     *
     * @param id id
     * @author 刘钢
     * @date 2018/8/12 12:22
     */
    @GetMapping("/rights/{id}/update")
    public String listRoleByIdForRights(@PathVariable("id") String id,
                                        Model model) {
        Role role = roleService.listRoleByIdForRights(id);
        if (role != null) {
            StringBuilder rightsId = new StringBuilder();
            for (int i = 0; i < role.getListRights().size(); i++) {
                if (i + 1 == role.getListRights().size()) {
                    rightsId.append(role.getListRights().get(i).getId());
                } else {
                    rightsId.append(role.getListRights().get(i).getId())
                            .append(",");
                }
            }
            role.setRightsId(rightsId.toString());
            model.addAttribute(role);
            return "role/roleRights";
        } else {
            return "none";
        }
    }

    /**
     * 根据查询条件获取角色信息
     *
     * @param conditionVO conditionVO
     * @author 刘钢
     * @date 2018/8/14 8:57
     */
    @PostMapping("/condition")
    @ResponseBody
    public void listRoleByCondition(RoleConditionVO conditionVO) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder()
                .build();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        User user = (User) httpSession.getAttribute("USER");
        RoleCondition roleCondition = mapper.map(conditionVO,
                RoleCondition.class);
        RoleConditionParams roleConditionParams = mapper.map(conditionVO,
                RoleConditionParams.class);
        roleConditionParams.setUserId(user.getId());
        roleCondition.setParams(roleConditionParams);
        List<Role> listRole = roleService.listRoleByCondition(roleCondition);
        writerToPageByJsonNoNull(tableResultService
                .tableResult(roleCondition.getTotal(), listRole));
    }

    /**
     * 给角色添加菜单
     *
     * @param role role
     * @author 奚艺轩
     * @date 2017/12/18 12:01
     */
    @RequestMapping("/menu/update")
    @ResponseBody
    public void updateRoleByIdForMenu(Role role) {
        ActionResult actionResult = roleService.updateRoleByIdForMenu(role);
        writerToPageByJson(actionResult);
    }

    /**
     * 给角色添加权限
     *
     * @param role role
     * @author 刘钢
     * @date 2018/8/15 10:39
     */
    @PostMapping("/rights/update")
    @ResponseBody
    public void updateRoleByIdForRights(Role role) {
        ActionResult actionResult = roleService.updateRoleByIdForRights(role);
        writerToPageByJsonNoNull(actionResult);
    }

    /**
     * 获取当前用户可查看的角色信息
     *
     * @author 奚艺轩
     * @date 2017/12/18 12:00
     */
    @PostMapping("/info")
    @ResponseBody
    public void getRoleByUserId() {
        List<SelectVO> listSelectVO = Lists.newArrayList();
        User user = (User) httpSession.getAttribute("USER");
        RoleCondition condition = new RoleCondition();
        RoleConditionParams roleConditionParams = new RoleConditionParams();
        roleConditionParams.setUserId(user.getId());
        condition.setParams(roleConditionParams);
        condition.setLimit(0);
        condition.setPage(0);
        List<Role> listRole = roleService.listRoleByCondition(condition);
        for (Role role : listRole) {
            SelectVO selectVO = new SelectVO();
            selectVO.setName(role.getName());
            selectVO.setValue(role.getId());
            listSelectVO.add(selectVO);
        }
        writerToPageByJsonNoNull(listSelectVO);
    }
}
