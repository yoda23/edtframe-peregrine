package cc.edt.frame.base.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cc.edt.frame.common.result.ActionResult;
import cc.edt.frame.common.result.ActionResultService;
import cc.edt.frame.admin.dao.base.UserDao;
import cc.edt.frame.base.service.UserService;
import cc.edt.frame.model.condition.FindCondition;
import cc.edt.frame.model.entity.base.User;
import cc.edt.frame.model.entity.base.UserMechanismsLinked;

/**
 * 用户管理
 *
 * @author 刘钢
 * @date 2018/8/12 11:13
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Resource
    private ActionResultService actionResultService;

    @Override
    public List<User> listUserByCondition(FindCondition findCondition) {
        PageHelper.startPage(findCondition.getPage(), findCondition.getLimit());
        List<User> listUser = userDao.listUserByCondition(findCondition);
        PageInfo<User> pageInfo = new PageInfo<>(listUser);
        findCondition.setTotal(pageInfo.getTotal());
        if (findCondition.getPage() == 0 && findCondition.getLimit() == 0) {
            findCondition.setTotal((long) listUser.size());
        }
        return listUser;
    }

    @Override
    public ActionResult saveUser(User user) {
        ActionResult actionResult = checkSame(user);
        if (actionResult.getStatus().isSuccess()) {
            // 保存用户表
            userDao.saveUser(user);
            // 保存机构关联表
            if (StringUtils.isNotBlank(user.getUserMechanismsRights())) {
                String[] userMechanismsRights = user.getUserMechanismsRights()
                        .split(",");
                for (String mechanismsId : userMechanismsRights) {
                    if (StringUtils.isNotEmpty(mechanismsId)) {
                        UserMechanismsLinked userMechanismsLinked = new UserMechanismsLinked();
                        userMechanismsLinked.setUserId(user.getId());
                        userMechanismsLinked.setMechanismsId(mechanismsId);
                        userDao.saveUserMechanismsLinked(userMechanismsLinked);
                    }
                }
            }
            actionResult = actionResultService.callBackResult(true, 0,
                    "用户保存成功");
        }
        return actionResult;
    }

    @Override
    public User getUserById(String id) {
        return userDao.getUserById(id);
    }

    @Override
    public ActionResult updateUser(User user) {
        ActionResult actionResult = checkSame(user);
        if (actionResult.getStatus().isSuccess()) {
            userDao.updateUser(user);
            // 保存用户机构查询关联表
            userDao.deleteUserMechanismsLinked(user.getId());
            // 保存机构关联表
            if (StringUtils.isNotBlank(user.getUserMechanismsRights())) {
                String[] userMechanismsRights = user.getUserMechanismsRights()
                        .split(",");
                for (String mechanismsId : userMechanismsRights) {
                    if (StringUtils.isNotEmpty(mechanismsId)) {
                        UserMechanismsLinked userMechanismsLinked = new UserMechanismsLinked();
                        userMechanismsLinked.setUserId(user.getId());
                        userMechanismsLinked.setMechanismsId(mechanismsId);
                        userDao.saveUserMechanismsLinked(userMechanismsLinked);
                    }
                }
            }
            actionResult = actionResultService.callBackResult(true, 0,
                    "用户修改成功");
        }
        return actionResult;
    }

    @Override
    public User getUserByLoginId(String loginId) {
        return userDao.getUserByLoginId(loginId);
    }

    @Override
    public List<UserMechanismsLinked> listUserMechanismsLinkedByUserId(
            String userId) {
        return userDao.listUserMechanismsLinkedByUserId(userId);
    }

    @Override
    public ActionResult updateState(User user) {
        ActionResult actionResult = actionResultService.callBackResult(true, 0,
                "用户状态修改成功");
        userDao.updateState(user);
        return actionResult;
    }

    @Override
    public ActionResult restUserPassword(User user) {
        user = userDao.getUserById(user.getId());
        if (user != null) {
            user.setLoginPassword(DigestUtils.md5Hex("123456"));
            userDao.updateLoginPassword(user);
            return actionResultService.callBackResult(true, 0,
                    "密码已成功重置为123456,请提示用户尽快修改");
        }
        return actionResultService.callBackResult(false, -1, "没有找到此用户信息");
    }

    @Override
    public ActionResult updateUserPassword(User user) {
        ActionResult actionResult;
        User userId = userDao.getUserById(user.getId());
        if (StringUtils.equals(DigestUtils.md5Hex(user.getOldPassword()),
                userId.getLoginPassword())) {
            user.setLoginPassword(DigestUtils.md5Hex(user.getLoginPassword()));
            userDao.updateLoginPassword(user);
            actionResult = actionResultService.callBackResult(true, 0,
                    "修改密码成功");
        } else {
            actionResult = actionResultService.callBackResult(false, -1,
                    "原始密码错误!");
        }
        return actionResult;
    }

    @Override
    public List<String> listUserByMechanismsId(String mechanismsId) {
        return userDao.listUserByMechanismsId(mechanismsId);
    }

    @Override
    public ActionResult updateUserInfo(User user) {
        userDao.updateUserInfo(user);
        return actionResultService.callBackResult(true, 0, "个人信息修改成功");
    }

    /**
     * 重复项检查
     *
     * @param user base
     * @return cc.edt.frame.core.base.result.ActionResult
     * @author 刘钢
     * @date 2018/8/12 11:17
     */
    private ActionResult checkSame(User user) {
        ActionResult actionResult = actionResultService.callBackResult();
        User userLoginId = getUserByLoginId(user.getLoginId());
        if (userLoginId != null && !userLoginId.getId().equals(user.getId())) {
            actionResult = actionResultService.callBackResult(false, -1,
                    "用户登陆ID不能重复");
        }
        return actionResult;
    }
}
