package cc.edt.frame.base.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cc.edt.frame.base.vo.UserVO;
import cc.edt.frame.common.result.TableResultService;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cc.edt.frame.common.controller.BaseController;
import cc.edt.frame.common.result.ActionResult;
import cc.edt.frame.common.result.ActionResultService;
import cc.edt.frame.base.service.UserService;
import cc.edt.frame.base.vo.condition.UserConditionVO;
import cc.edt.frame.model.condition.UserCondition;
import cc.edt.frame.model.condition.params.UserConditionParams;
import cc.edt.frame.model.entity.base.User;
import cc.edt.frame.model.entity.base.UserMechanismsLinked;
import cn.hutool.core.util.IdUtil;

/**
 * 用户管理
 *
 * @author 刘钢
 * @date 2018/8/12 11:24
 */
@Controller
@CrossOrigin
@RequestMapping("/user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;
    @Resource
    private TableResultService tableResultService;

    /**
     * 添加用户
     *
     * @param user user
     * @author 刘钢
     * @date 2018/8/15 11:36
     */
    @PostMapping("/save")
    @ResponseBody
    public void saveUser(User user) {
        User sessionUser = (User) httpSession.getAttribute("USER");
        user.setId(IdUtil.simpleUUID());
        user.setLoginPassword(DigestUtils.md5Hex(user.getLoginPassword()));
        user.setActive(2);
        user.setAddTime(new Date());
        user.setAddUser(sessionUser.getLoginId());
        ActionResult actionResult = userService.saveUser(user);
        writerToPageByJsonNoNull(actionResult);
    }

    /**
     * 修改用户
     *
     * @param user user
     * @author 刘钢
     * @date 2018/8/15 13:20
     */
    @PostMapping("/update")
    @ResponseBody
    public void updateUser(User user) {
        ActionResult actionResult = userService.updateUser(user);
        writerToPageByJsonNoNull(actionResult);
    }

    /**
     * 按查询条件获取用户信息
     *
     * @param conditionVO conditionVO
     * @author 刘钢
     * @date 2018/8/12 12:53
     */
    @PostMapping("/condition")
    @ResponseBody
    public void listUserByCondition(UserConditionVO conditionVO) {
        User user = (User) httpSession.getAttribute("USER");
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder()
                .build();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        UserCondition userCondition = mapper.map(conditionVO,
                UserCondition.class);
        UserConditionParams userConditionParams = mapper.map(conditionVO,
                UserConditionParams.class);
        userConditionParams.setUserId(user.getId());
        userCondition.setParams(userConditionParams);
        List<User> listUser = userService.listUserByCondition(userCondition);
        List<UserVO> listUserVO = Lists.newArrayList();
        for (User userList : listUser) {
            UserVO userVO = mapper.map(userList, UserVO.class);
            userVO.setMechanismsName(userList.getMechanisms().getName());
            userVO.setRoleName(userList.getRole().getName());
            listUserVO.add(userVO);
        }
        writerToPageByJsonNoNull(tableResultService
                .tableResult(userCondition.getTotal(), listUserVO));
    }

    /**
     * 根据ID获取用户信息
     *
     * @param id 主键ID
     * @author 刘钢
     * @date 2018/8/15 12:00
     */
    @GetMapping("/toUpdate/{id}")
    public String getUserById(@PathVariable("id") String id, Model model) {
        User user = userService.getUserById(id);
        if (user != null) {
            List<String> listStr = Lists.newArrayList();
            List<UserMechanismsLinked> listUserMechanismsLinked = userService
                    .listUserMechanismsLinkedByUserId(user.getId());
            for (UserMechanismsLinked userMechanismsLinked : listUserMechanismsLinked) {
                listStr.add(userMechanismsLinked.getMechanismsId());
            }
            model.addAttribute("userMechanismsRights",
                    StringUtils.join(listStr, ","));
            model.addAttribute(user);
            return "user/userUpdate";
        }
        return "none";
    }

    /**
     * 修改用户状态
     *
     * @param id     用户ID
     * @param active 登陆状态（1=启用，2=禁用）
     * @author 刘钢
     * @date 2018/8/15 13:39
     */
    @PostMapping("/update/state/{id}/{active}")
    public void updateState(@PathVariable("id") String id,
                            @PathVariable("active") Integer active) {
        User user = new User();
        user.setId(id);
        user.setActive(active);
        ActionResult actionResult = userService.updateState(user);
        writerToPageByJsonNoNull(actionResult);
    }

    /**
     * 重置用户密码
     *
     * @param id 用户ID
     * @author 刘钢
     * @date 2018/8/15 13:41
     */
    @PostMapping("/rest/password/{id}")
    public void restLoginPassword(@PathVariable("id") String id) {
        User user = new User();
        user.setId(id);
        ActionResult actionResult = userService.restUserPassword(user);
        writerToPageByJsonNoNull(actionResult);
    }

    /**
     * 进入个人信息-修改基础信息
     *
     * @return java.lang.String
     * @author 刘钢
     * @date 2017/6/11 22:05
     */
    @RequestMapping("/info")
    public String toUserInfo(Model model) {
        User user = (User) httpSession.getAttribute("USER");
        User userId = userService.getUserById(user.getId());
        if (userId != null) {
            model.addAttribute(userId);
            return "user/userInfo";
        }
        return "none";
    }

    /**
     * 进入个人信息-修改密码
     *
     * @return java.lang.String
     * @author 刘钢
     * @date 2017/6/11 22:05
     */
    @RequestMapping("/info/password")
    public String toUserInfoPassword(Model model) {
        User user = (User) httpSession.getAttribute("USER");
        User userId = userService.getUserById(user.getId());
        if (userId != null) {
            model.addAttribute(userId);
            return "user/userInfoPassword";
        }
        return "none";
    }

    /**
     * 修改个人信息
     *
     * @param user user
     * @author 刘钢
     * @date 2017/6/11 22:05
     */
    @RequestMapping("/info/update")
    @ResponseBody
    public void updateUserInfo(User user) {
        User userSession = (User) httpSession.getAttribute("USER");
        user.setId(userSession.getId());
        ActionResult actionResult = userService.updateUserInfo(user);
        writerToPageByJsonNoNull(actionResult);
    }

    /**
     * 修改用户密码
     *
     * @param user user
     * @author 刘钢
     * @date 2018/8/15 13:41
     */
    @PostMapping("/info/update/password")
    public void updateLoginPassword(User user) {
        User userSession = (User) httpSession.getAttribute("USER");
        user.setId(userSession.getId());
        ActionResult actionResult = userService.updateUserPassword(user);
        writerToPageByJsonNoNull(actionResult);
    }
}
