package cc.edt.frame.base.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletOutputStream;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;

import cc.edt.frame.common.controller.BaseController;
import cc.edt.frame.common.result.ActionResult;
import cc.edt.frame.base.bo.IndexRoleMenu;
import cc.edt.frame.model.entity.base.Menu;
import cc.edt.frame.model.entity.base.Role;
import cc.edt.frame.model.entity.base.User;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.util.ObjectUtil;

/**
 * @author 刘钢
 * @date 2018/8/9 19:10
 */
@Controller
@RequestMapping("/")
@Slf4j
public class IndexController extends BaseController {
    private final static String MENU_ROOT_NODE = "0";
    private LineCaptcha circleCaptcha = null;

    /**
     * 系统登录页
     *
     * @author 刘钢
     * @date 2017-05-18 11:24
     */
    @GetMapping("login")
    public String login() {
        log.debug("login->" + httpSession.getId());
        return "login";
    }

    /**
     * 验证用户登录
     *
     * @param user user
     * @author 刘钢
     * @date 2017/6/11 22:04
     */
    @PostMapping("login")
    @ResponseBody
    public void login(User user, @RequestParam String validateCode) {
        ActionResult actionResult = new ActionResult();
        log.debug("login-->" + httpSession.getId());
        if (ObjectUtil.isNotNull(circleCaptcha)
                && circleCaptcha.verify(validateCode)) {
            user.setLoginPassword(DigestUtils.md5Hex(user.getLoginPassword()));
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                    user.getLoginId(), user.getLoginPassword());
            // usernamePasswordToken.setRememberMe(true);
            Subject currentUser = SecurityUtils.getSubject();
            try {
                currentUser.login(usernamePasswordToken);
                actionResult.getStatus().setSuccess(true);
            } catch (UnknownAccountException uae) {
                actionResult.getStatus().setSuccess(false);
                actionResult.getStatus().setMessage("未知账号");
            } catch (LockedAccountException lae) {
                actionResult.getStatus().setSuccess(false);
                actionResult.getStatus().setMessage("账号已锁定");
            } catch (ExcessiveAttemptsException eae) {
                actionResult.getStatus().setSuccess(false);
                actionResult.getStatus().setMessage("用户名或密码错误次数过多");
            } catch (DisabledAccountException dae) {
                actionResult.getStatus().setSuccess(false);
                actionResult.getStatus().setMessage("账号已停用");
            } catch (AuthenticationException ice) {
                actionResult.getStatus().setSuccess(false);
                actionResult.getStatus().setMessage("用户名或密码不正确");
            }
            // 验证是否登录成功
            if (!currentUser.isAuthenticated()) {
                usernamePasswordToken.clear();
            }
        } else {
            actionResult.getStatus().setSuccess(false);
            actionResult.getStatus().setMessage("验证码错误");
        }
        writerToPageByJsonNoNull(actionResult);
    }

    /**
     * 用户退出
     *
     * @return org.springframework.web.servlet.ModelAndView
     * @author 刘钢
     * @date 2017/6/11 22:05
     */
    @RequestMapping("logout")
    public ModelAndView logoutAction() {
        httpSession.removeAttribute("USER");
        SecurityUtils.getSubject().logout();
        return new ModelAndView("redirect:/login");
    }

    /**
     * 页面跳转公共controller
     *
     * @author 刘钢
     * @date 2017/12/18 11:24
     */
    @RequestMapping("redirect")
    public String redirect(@RequestParam("page") String page) {
        return page;
    }

    /**
     * session超时处理页
     *
     * @author 刘钢
     * @date 2017/12/18 11:24
     */
    @RequestMapping("timeout")
    public String timeout() {
        try {
            // 如果是ajax请求响应头会有，x-requested-with
            if (httpServletRequest.getHeader("x-requested-with") != null
                    && "XMLHttpRequest".equalsIgnoreCase(
                    httpServletRequest.getHeader("x-requested-with"))) {
                // 在响应头设置session状态
                httpServletResponse.setHeader("sessionstatus", "timeout");
                httpServletResponse.setContentType("text/html;charset=utf-8");
                String str = "{\"success\":false,\"message\":\"用户登陆超时,请重新登录\"}";
                PrintWriter writer = httpServletResponse.getWriter();
                writer.write(str);
                writer.flush();
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "timeout";
    }

    /**
     * 系统主页
     *
     * @author 刘钢
     * @date 2017-05-18 11:24
     */
    @RequestMapping("index")
    public String index(Model model) {
        List<IndexRoleMenu> listIndexRoleMenu = Lists.newArrayList();
        User user = (User) httpSession.getAttribute("USER");
        if (user != null) {
            Role role = user.getRole();
            IndexRoleMenu indexRoleMenu = new IndexRoleMenu();
            indexRoleMenu.setId("0");
            indexRoleMenu.setPid("0");
            IndexRoleMenu indexRoleMenuFather = new IndexRoleMenu();
            List<IndexRoleMenu> listIndexRoleMenuFather = Lists.newArrayList();
            indexRoleMenuFather.setId("0");
            indexRoleMenuFather.setPid("0");
            indexRoleMenuFather.setListMenuChild(listIndexRoleMenuFather);
            getIndexRoleMenuChilds(role.getListMenu(), indexRoleMenu,
                    indexRoleMenuFather, listIndexRoleMenu);
            model.addAttribute(user);
            model.addAttribute("listIndexRoleMenu", listIndexRoleMenu);
            return "index";
        } else {
            return "login";
        }
    }

    /**
     * 功能菜单-将子节点挂载到父节点上
     *
     * @author 刘钢
     * @date 2017-05-18 11:24
     */
    private void getIndexRoleMenuChilds(List<Menu> listMenu,
                                        IndexRoleMenu indexRoleMenu, IndexRoleMenu indexRoleMenuFather,
                                        List<IndexRoleMenu> listIndexRoleMenu) {
        // 含有子节点
        if (hasChildNode(listMenu, indexRoleMenu)) {
            // 获取当前节点下的子节点
            List<IndexRoleMenu> listIndexRoleMenuChild = getIndexRoleMenuChild(
                    listMenu, indexRoleMenu);
            indexRoleMenu.setListMenuChild(listIndexRoleMenuChild);
            // 遍历父节点，加入到父节点的集合中
            for (int i = 0; i < indexRoleMenuFather.getListMenuChild()
                    .size(); i++) {
                if (indexRoleMenu.getId().equals(indexRoleMenuFather
                        .getListMenuChild().get(i).getId())) {
                    indexRoleMenuFather.getListMenuChild().get(i)
                            .setListMenuChild(listIndexRoleMenuChild);
                }
            }
            // 根节点不放到里面
            if (MENU_ROOT_NODE.equals(indexRoleMenu.getPid())
                    && !MENU_ROOT_NODE.equals(indexRoleMenu.getId())) {
                indexRoleMenuFather.setListMenuChild(listIndexRoleMenuChild);
                listIndexRoleMenu.add(indexRoleMenu);
            }
            for (int i = 0; i < listIndexRoleMenuChild.size(); i++) {
                getIndexRoleMenuChilds(listMenu,
                        indexRoleMenu.getListMenuChild().get(i),
                        indexRoleMenuFather, listIndexRoleMenu);
            }

        }
    }

    /**
     * 功能菜单-返回当前节点下的所有子节点
     *
     * @author 刘钢
     * @date 2017-05-18 11:24
     */
    private List<IndexRoleMenu> getIndexRoleMenuChild(List<Menu> listMenu,
                                                      IndexRoleMenu indexRoleMenu) {
        List<IndexRoleMenu> listIndexRoleMenuChild = Lists.newArrayList();
        for (Menu menu : listMenu) {
            IndexRoleMenu indexRoleMenuChild = new IndexRoleMenu();
            // 如果父节点等于当前ID
            if (menu.getParentId().equals(indexRoleMenu.getId())) {
                indexRoleMenuChild.setId(menu.getId());
                indexRoleMenuChild.setPid(menu.getParentId());
                indexRoleMenuChild.setName(menu.getName());
                indexRoleMenuChild.setOpenUrl(menu.getOpenUrl());
                indexRoleMenuChild.setIcon(menu.getIcon());
                listIndexRoleMenuChild.add(indexRoleMenuChild);
            }
        }
        return listIndexRoleMenuChild;

    }

    /**
     * 功能菜单-是否存在子节点
     *
     * @author 刘钢
     * @date 2017-05-18 11:24
     */
    private boolean hasChildNode(List<Menu> listMenu,
                                 IndexRoleMenu indexRoleMenu) {
        return getIndexRoleMenuChild(listMenu, indexRoleMenu).size() > 0;
    }

    /**
     * 验证码生成
     *
     * @author 刘钢
     * @date 2017-05-18 11:24
     */
    @RequestMapping("/getValidateCode")
    @ResponseBody
    public void getValidateCodeAction() {
        circleCaptcha = CaptchaUtil.createLineCaptcha(70, 30, 4, 4);
        // 禁止图像缓存。
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setHeader("Cache-Control", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream servletOutputStream = null;
        try {
            servletOutputStream = httpServletResponse.getOutputStream();
            circleCaptcha.write(servletOutputStream);
            log.debug("getValidateCode->"
                    + httpServletRequest.getSession(true).getId());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (servletOutputStream != null) {
                    servletOutputStream.flush();
                    servletOutputStream.close();
                    httpServletResponse.flushBuffer();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
