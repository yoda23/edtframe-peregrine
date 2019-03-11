package cc.edt.frame.wechat.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.google.common.collect.Maps;

import cc.edt.frame.common.controller.BaseController;
import cc.edt.frame.common.result.ActionResult;
import cc.edt.frame.common.result.TableResultService;
import cc.edt.frame.model.condition.WeChatAccountCondition;
import cc.edt.frame.model.condition.params.WeChatAccountConditionParams;
import cc.edt.frame.model.entity.base.User;
import cc.edt.frame.model.entity.wechat.WeChatAccount;
import cc.edt.frame.wechat.service.WeChatAccountService;
import cc.edt.frame.wechat.vo.condition.WeChatAccountConditionVO;
import cn.hutool.core.util.IdUtil;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * 微信账号
 *
 * @author 刘钢
 * @date 2018/9/14 20:11
 */
@Controller
@CrossOrigin
@RequestMapping("/wechat/account")
public class WeChatAccountController extends BaseController {
    @Resource
    private WeChatAccountService weChatAccountService;
    @Resource
    private TableResultService tableResultService;

    /**
     * 根据查询条件显示
     *
     * @param weChatAccountConditionVO weChatAccountConditionVO
     * @author 刘钢
     * @date 2018/8/29 16:38
     */
    @PostMapping("/condition")
    @ResponseBody
    public void listWeChatAccountByCondition(
            WeChatAccountConditionVO weChatAccountConditionVO) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder()
                .build();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        User user = (User) httpSession.getAttribute("USER");
        WeChatAccountCondition weChatAccountCondition = mapper
                .map(weChatAccountConditionVO, WeChatAccountCondition.class);
        WeChatAccountConditionParams conditionParams = mapper.map(
                weChatAccountConditionVO, WeChatAccountConditionParams.class);
        conditionParams.setUserId(user.getId());
        weChatAccountCondition.setParams(conditionParams);
        List<WeChatAccount> listWeChatAccount = weChatAccountService
                .listWeChatAccountByCondition(weChatAccountCondition);
        writerToPageByJson(tableResultService.tableResult(
                weChatAccountCondition.getTotal(), listWeChatAccount));
    }

    /**
     * 保存
     *
     * @param weChatAccount weChatAccount
     * @author 刘钢
     * @date 2018/8/31 9:09
     */
    @RequestMapping("/save")
    @ResponseBody
    public void saveWeChatAccount(WeChatAccount weChatAccount) {
        User user = (User) httpSession.getAttribute("USER");
        weChatAccount.setId(IdUtil.simpleUUID());
        weChatAccount.setAddTime(new Date());
        weChatAccount.setAddUser(user.getId());
        writerToPageByJsonNoNull(
                weChatAccountService.saveWeChatAccount(weChatAccount));
    }

    /**
     * 跳转到修改页面
     *
     * @param id    id
     * @param model model
     * @return String
     * @author 奚艺轩 2017-6-14下午4:18:13
     */
    @GetMapping("/toUpdate/{id}")
    public String toUpdateWeChatAccount(@PathVariable("id") String id,
                                        Model model) {
        WeChatAccount weChatAccount = weChatAccountService
                .getWeChatAccountById(id);
        if (weChatAccount != null) {
            model.addAttribute(weChatAccount);
            return "wechatAccount/wechatAccountUpdate";
        }
        return "/none";
    }

    /**
     * 修改微信账号
     *
     * @param weChatAccount weChatAccount
     * @author 奚艺轩 2017-6-14下午4:20:06
     */
    @RequestMapping("/update")
    @ResponseBody
    public void updateWeChatAccount(WeChatAccount weChatAccount) {
        ActionResult actionResult = weChatAccountService
                .updateWeChatAccount(weChatAccount);
        writerToPageByJson(actionResult);
    }

    /**
     * 删除微信账号
     *
     * @param id id
     * @author 奚艺轩 2017-6-14下午4:20:44
     */
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public void deleteWxAccount(@PathVariable("id") String id) {
        ActionResult actionResult = weChatAccountService
                .deleteWeChatAccount(id);
        writerToPageByJson(actionResult);
    }

    /**
     * 获取当前用户下所有微信账号信息
     *
     * @author 刘钢
     * @date 2018/9/25 9:04
     */
    @RequestMapping("/user/id")
    @ResponseBody
    public void listWeChatAccountByUserId() {
        User user = (User) httpSession.getAttribute("USER");
        WeChatAccountCondition weChatAccountCondition = new WeChatAccountCondition();
        weChatAccountCondition.setPage(0);
        weChatAccountCondition.setLimit(0);
        WeChatAccountConditionParams weChatAccountConditionParams = new WeChatAccountConditionParams();
        weChatAccountConditionParams.setUserId(user.getId());
        weChatAccountCondition.setParams(weChatAccountConditionParams);
        List<WeChatAccount> listWeChatAccount = weChatAccountService
                .listWeChatAccountByCondition(weChatAccountCondition);
        writerToPageByJson(listWeChatAccount);
    }

    /**
     * 根据查询条件显示(不分页)
     *
     * @param weChatAccountConditionVO weChatAccountConditionVO
     * @author 姜宁
     * @date 2018/9/26 11:34
     */
    @PostMapping("/conditionNoPage")
    @ResponseBody
    public void listWeChatAccountByConditionNoPage(
            WeChatAccountConditionVO weChatAccountConditionVO) {
        User user = (User) httpSession.getAttribute("USER");
        weChatAccountConditionVO.setLimit(0);
        weChatAccountConditionVO.setPage(0);
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder()
                .build();
        WeChatAccountCondition weChatAccountCondition = mapperFactory
                .getMapperFacade()
                .map(weChatAccountConditionVO, WeChatAccountCondition.class);
        WeChatAccountConditionParams conditionParams = mapperFactory
                .getMapperFacade().map(weChatAccountConditionVO,
                        WeChatAccountConditionParams.class);
        conditionParams.setUserId(user.getId());
        weChatAccountCondition.setParams(conditionParams);
        List<WeChatAccount> listWeChatAccount = weChatAccountService
                .listWeChatAccountByCondition(weChatAccountCondition);
        tableResultService.tableResult(weChatAccountCondition.getTotal(),
                listWeChatAccount);
    }

}
