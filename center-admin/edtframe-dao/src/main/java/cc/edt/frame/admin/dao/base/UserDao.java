package cc.edt.frame.admin.dao.base;

import cc.edt.frame.model.condition.FindCondition;
import cc.edt.frame.model.entity.base.User;
import cc.edt.frame.model.entity.base.UserMechanismsLinked;

import java.util.List;

/**
 * 用户信息
 *
 * @author 刘钢
 * @date 2017/12/18 13:29
 */
public interface UserDao {
    /**
     * 验证用户登录
     *
     * @param loginId loginId
     * @return com.edt.entity.User
     * @author 刘钢 2017/5/17 22:50
     */

    User login(String loginId);

    /**
     * 获取所有用户信息
     *
     * @param condition condition
     * @return java.util.List<com.edt.entity.User>
     * @author 刘钢 2017/5/17 22:51
     */
    List<User> listUserByCondition(FindCondition condition);

    /**
     * 保存用户
     *
     * @param user base
     * @author 刘钢 2017/5/17 22:51
     */
    void saveUser(User user);

    /**
     * 根据用户ID查询用户
     *
     * @param userId userId
     * @return com.edt.entity.User
     * @author 刘钢 2017/5/17 22:51
     */
    User getUserById(String userId);

    /**
     * 修改用户信息
     *
     * @param user base
     * @author 刘钢 2017/5/17 22:51
     */
    void updateUser(User user);

    /**
     * 删除用户
     *
     * @param id id
     * @author 刘钢 2017/5/17 22:51
     */
    void deleteUser(String id);

    /**
     * 根据登陆账号ID查询用户
     *
     * @param loginId loginId
     * @return com.edt.entity.User
     * @author 刘钢 2017/5/17 22:51
     */
    User getUserByLoginId(String loginId);

    /**
     * 保存用户机构关联表
     *
     * @param userMechanismsLinked userMechanismsLinked
     * @author 刘钢 2017-07-06 10:25
     */

    void saveUserMechanismsLinked(UserMechanismsLinked userMechanismsLinked);

    /**
     * 删除用户机构关联表
     *
     * @param userId userId
     * @author 刘钢 2017-07-06 10:26
     */

    void deleteUserMechanismsLinked(String userId);

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
     * @author 刘钢 2017/5/17 22:51
     */
    void updateState(User user);

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
     * 修改登录帐号密码
     *
     * @param user user
     * @author 刘钢 2017/5/17 22:52
     */
    void updateLoginPassword(User user);

    /**
     * 修改用户个人信息
     *
     * @param user user
     * @author 刘钢 2017/5/17 22:52
     */
    void updateUserInfo(User user);
}
