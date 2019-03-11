package cc.edt.frame.base.service;

import java.util.List;

import cc.edt.frame.common.result.ActionResult;
import cc.edt.frame.model.condition.FindCondition;
import cc.edt.frame.model.entity.base.User;
import cc.edt.frame.model.entity.base.UserMechanismsLinked;

/**
 * 用户信息
 *
 * @author 刘钢
 * @date 2017/12/18 13:29
 */
public interface UserService {

    /**
     * 获取所有用户信息
     *
     * @param findCondition findCondition
     * @return java.util.List<com.edt.entity.User>
     * @author 刘钢 2017/5/17 22:51
     */
    List<User> listUserByCondition(FindCondition findCondition);

    /**
     * 保存用户
     *
     * @param user base
     * @return cc.edt.frame.core.base.result.ActionResult
     * @author 刘钢 2017/5/17 22:51
     */
    ActionResult saveUser(User user);

    /**
     * 根据用户ID查询用户
     *
     * @param id id
     * @return com.edt.entity.User
     * @author 刘钢 2017/5/17 22:51
     */
    User getUserById(String id);

    /**
     * 修改用户信息
     *
     * @param user base
     * @return cc.edt.frame.core.base.result.ActionResult
     * @author 刘钢 2017/5/17 22:51
     */
    ActionResult updateUser(User user);

    /**
     * 根据登陆账号ID查询用户
     *
     * @param loginId loginId
     * @return com.edt.entity.User
     * @author 刘钢 2017/5/17 22:51
     */
    User getUserByLoginId(String loginId);

    /**
     * 根据UserId获取用户机构中间表信息
     *
     * @param userId userId
     * @return java.util.List<com.edt.entity.UserMechanismsLinked>
     * @author 刘钢 2017-07-07 11:11
     */

    List<UserMechanismsLinked> listUserMechanismsLinkedByUserId(String userId);

    /**
     * 修改用户状态
     *
     * @param user base
     * @return com.edt.common.bean.ActionResult
     * @author 刘钢
     * @date 2017-05-18 11:50
     */

    ActionResult updateState(User user);

    /**
     * 根据机构ID获取所有用户信息
     *
     * @param mechanismsId mechanismsId
     * @return java.util.List<com.edt.entity.User>
     * @author 刘钢
     * @date 2017-07-10 14:40
     */

    List<String> listUserByMechanismsId(String mechanismsId);

    /**
     * 重置用户登录帐号密码
     *
     * @param user user
     * @return com.edt.common.bean.ActionResult
     * @author 刘钢
     * @date 2017-05-18 11:51
     */

    ActionResult restUserPassword(User user);

    /**
     * 修改用户登录帐号密码
     *
     * @param user user
     * @return com.edt.common.bean.ActionResult
     * @author 刘钢
     * @date 2017-05-18 11:51
     */

    ActionResult updateUserPassword(User user);

    /**
     * 修改用户个人信息
     *
     * @param user user
     * @return com.edt.common.bean.ActionResult
     * @author 刘钢
     * @date 2017-05-18 11:50
     */

    ActionResult updateUserInfo(User user);
}
