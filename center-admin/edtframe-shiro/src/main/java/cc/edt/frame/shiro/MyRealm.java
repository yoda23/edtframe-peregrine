package cc.edt.frame.shiro;

import cc.edt.frame.admin.dao.base.RoleDao;
import cc.edt.frame.admin.dao.base.UserDao;
import cc.edt.frame.model.entity.base.Rights;
import cc.edt.frame.model.entity.base.Role;
import cc.edt.frame.model.entity.base.User;
import com.google.common.collect.Lists;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * shirt用户验证
 *
 * @author 刘钢
 */
@Component
public class MyRealm extends AuthorizingRealm {
    @Resource
    private UserDao userDao;
    @Resource
    private RoleDao roleDao;

    /**
     * 权限认证
     *
     * @param principalCollection principalCollection
     * @return org.apache.shiro.authz.AuthorizationInfo
     * @author 刘钢
     * @date 2017-06-12 9:22
     */

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
        String loginId = (String) super.getAvailablePrincipal(
                principalCollection);
        User user = userDao.login(loginId);
        if (user != null) {
            // 获取角色对应的权限
            Role roleRights = roleDao.listRoleByIdForRights(user.getRoleId());
            if (roleRights != null) {
                user.setRole(roleRights);
                List<String> listString = Lists.newArrayList();
                for (Rights rights : roleRights.getListRights()) {
                    listString.add(rights.getId());
                }
                simpleAuthorInfo.addStringPermissions(listString);
            }
        }
        return simpleAuthorInfo;
    }

    /**
     * 登陆认证
     *
     * @param authenticationToken authenticationToken
     * @return org.apache.shiro.authc.AuthenticationInfo
     * @author 刘钢
     * @date 2017-06-12 9:22
     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authenticationToken)
            throws AuthenticationException {
        SimpleAuthenticationInfo info = null;
        // UsernamePasswordToken对象用来存放提交的登录信息
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 查询数据库中用户信息
        User user = userDao.login(token.getUsername());
        if (user != null) {
            // 账户锁定
            if (user.getActive() == 2) {
                throw new LockedAccountException();
            }
            // 获取角色对应的菜单
            Role roleMenu = roleDao.listRoleByIdForMenu(user.getRoleId());
            if (roleMenu != null) {
                user.getRole().setListMenu(roleMenu.getListMenu());
            }
            info = new SimpleAuthenticationInfo(user.getLoginId(),
                    user.getLoginPassword(), getName());
            // 获取角色对应的权限
            Role roleRights = roleDao.listRoleByIdForRights(user.getRoleId());
            if (roleRights != null) {
                user.getRole().setListRights(roleRights.getListRights());
            }
            setSession("USER", user);
        }
        return info;
    }

    private void setSession(Object key, Object value) {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            Session session = currentUser.getSession();
            if (null != session) {
                session.setAttribute(key, value);
            }
        }
    }

}
