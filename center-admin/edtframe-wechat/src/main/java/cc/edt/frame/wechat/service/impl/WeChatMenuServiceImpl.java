package cc.edt.frame.wechat.service.impl;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.annotation.Resource;

import cc.edt.frame.common.constant.SystemConstant;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import cc.edt.frame.common.exception.CustomerException;
import cc.edt.frame.common.exception.CustomerExceptionCode;
import cc.edt.frame.common.result.ActionResult;
import cc.edt.frame.common.result.ActionResultService;
import cc.edt.frame.common.service.AccessTokenService;
import cc.edt.frame.admin.dao.wechat.WeChatMenuDao;
import cc.edt.frame.admin.dao.wechat.WeChatMenuHistoryDao;
import cc.edt.frame.model.condition.FindCondition;
import cc.edt.frame.model.condition.WeChatMenuCondition;
import cc.edt.frame.model.condition.params.WeChatMenuConditionParams;
import cc.edt.frame.model.entity.wechat.WeChatMenu;
import cc.edt.frame.model.entity.wechat.WeChatMenuHistory;
import cc.edt.frame.wechat.service.WeChatMenuService;
import cc.edt.iceutils3.date.IceDateFormatUtils;
import cc.edt.iceutils3.string.IceStringUtils;
import cc.edt.iceutils3.wechat.menu.IceWeChatMenuUtils;
import cc.edt.iceutils3.wechat.menu.bean.Button;
import cc.edt.iceutils3.wechat.menu.bean.SubButton;
import cc.edt.iceutils3.wechat.menu.bean.WeChatMenuResult;
import cc.edt.iceutils3.wechat.security.bean.AccessTokenResult;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * 微信菜单
 *
 * @author 刘钢
 * @date 2018/9/17 15:32
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class WeChatMenuServiceImpl implements WeChatMenuService {
    @Resource
    private WeChatMenuDao weChatMenuDao;
    @Resource
    private ActionResultService actionResultService;
    @Resource
    private AccessTokenService accessTokenService;
    @Resource
    private WeChatMenuHistoryDao weChatMenuHistoryDao;
    private final static int MENU_MAX_COUNT = 3;

    @Override
    public ActionResult saveWeChatMenuFirst(WeChatMenu weChatMenu) {
        ActionResult actionResult;
        List<WeChatMenu> listWeChatMenuFirst = listWeChatMenuFirst(
                weChatMenu.getAccountId());
        if (listWeChatMenuFirst.size() == MENU_MAX_COUNT) {
            actionResult = actionResultService.callBackResult(false, -1,
                    "一级菜单不能超过" + MENU_MAX_COUNT + "个");
        } else {
            IceStringUtils.blankToDefault(weChatMenu.getType(), "");
            weChatMenuDao.saveWeChatMenu(weChatMenu);
            actionResult = actionResultService.callBackResult(true, 0,
                    "一级菜单添加成功");
        }
        return actionResult;
    }

    @Override
    public ActionResult updateWeChatMenuFirst(WeChatMenu weChatMenu) {
        weChatMenuDao.updateWeChatMenu(weChatMenu);
        return actionResultService.callBackResult(true, 0, "一级菜单修改成功");
    }

    @Override
    public ActionResult deleteWeChatMenu(String id) {
        weChatMenuDao.deleteWeChatMenu(id);
        return actionResultService.callBackResult(true, 0, "菜单删除成功");
    }

    @Override
    public void deleteWeChatMenuByAll(String accountId) {
        weChatMenuDao.deleteWeChatMenuByAll(accountId);
    }

    @Override
    public List<WeChatMenu> listWeChatMenuFirst(String accountId) {
        return weChatMenuDao.listWeChatMenuFirst(accountId);
    }

    @Override
    public ActionResult saveWeChatMenuSecond(WeChatMenu weChatMenu) {
        WeChatMenu weChatMenuPid = getWeChatMenuById(weChatMenu.getPid());
        weChatMenu.setOrder(
                weChatMenuPid.getOrder() + (weChatMenu.getOrder() / 10));
        weChatMenuDao.saveWeChatMenu(weChatMenu);
        return actionResultService.callBackResult(true, 0, "二级菜单添加成功");
    }

    @Override
    public ActionResult updateWeChatMenuSecond(WeChatMenu weChatMenu) {
        ActionResult actionResult = new ActionResult();
        WeChatMenu weChatMenuPid = getWeChatMenuById(weChatMenu.getPid());
        if (weChatMenuPid != null) {
            weChatMenu.setOrder(
                    weChatMenuPid.getOrder() + (weChatMenu.getOrder() / 10));
            weChatMenuDao.updateWeChatMenu(weChatMenu);
        }
        return actionResultService.callBackResult(true, 0, "二级菜单修改成功");
    }

    @Override
    public WeChatMenu getWeChatMenuById(String id) {
        return weChatMenuDao.getWeChatMenuById(id);
    }

    @Override
    public List<WeChatMenu> listWeChatMenuForView(FindCondition condition) {
        return null;
    }

    @Override
    public List<WeChatMenu> listWeChatMenuByAll(FindCondition condition) {
        List<WeChatMenu> listWeChatMenu = weChatMenuDao
                .listWeChatMenuByAll(condition);
        for (WeChatMenu weChatMenu : listWeChatMenu) {
            if (StringUtils.isNotBlank(weChatMenu.getPid())) {
                weChatMenu.setNameDisplay(
                        "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|--"
                                + weChatMenu.getName());
            } else {
                weChatMenu.setNameDisplay(weChatMenu.getName());
            }
            switch (weChatMenu.getRank()) {
                case 1:
                    weChatMenu.setRankName("一级菜单");
                    break;
                case 2:
                    weChatMenu.setRankName("二级菜单");
                    break;
                default:
                    break;
            }
            if (StringUtils.isBlank(weChatMenu.getPid())) {
                weChatMenu.setTypeName("一级菜单");
            } else {
                switch (weChatMenu.getType()) {
                    case "click":
                        weChatMenu.setTypeName("用户单击");
                        break;
                    case "view":
                        weChatMenu.setTypeName("URL跳转");
                        break;
                    case "scancode_push":
                        weChatMenu.setTypeName("扫码推事件");
                        break;
                    case "scancode_waitmsg":
                        weChatMenu.setTypeName("扫码推事件且弹出“消息接收中”提示框");
                        break;
                    case "pic_sysphoto":
                        weChatMenu.setTypeName("弹出系统拍照发图");
                        break;
                    case "pic_photo_or_album":
                        weChatMenu.setTypeName("弹出拍照或者相册发图");
                        break;
                    case "pic_weixin":
                        weChatMenu.setTypeName("弹出微信相册发图器");
                        break;
                    case "location_select":
                        weChatMenu.setTypeName("弹出地理位置选择器");
                        break;
                    case "media_id":
                        weChatMenu.setTypeName("下发消息（除文本消息）");
                        break;
                    case "view_limited":
                        weChatMenu.setTypeName("跳转图文消息URL");
                        break;
                    default:
                        weChatMenu.setTypeName("未知");
                        break;
                }
            }
        }
        return listWeChatMenu;
    }

    @Override
    public List<WeChatMenu> listWeChatMenuByPid(String pid) {
        return weChatMenuDao.listWeChatMenuByPid(pid);
    }

    @Override
    public ActionResult syncWeChatToLocal(WeChatMenu weChatMenu) {
        ActionResult<AccessTokenResult, String> actionResult = accessTokenService
                .getAccessTokenByAccountId(weChatMenu.getAccountId());
        if (actionResult.getStatus().isSuccess()) {
            AccessTokenResult accessToken = actionResult.getResult();
            WeChatMenuResult result = null;
            try {
                result = IceWeChatMenuUtils
                        .getMenu(accessToken.getAccessToken());
            } catch (IOException | NoSuchAlgorithmException
                    | KeyManagementException e) {
                e.printStackTrace();
            }
        } else {
            actionResult = actionResultService.callBackResult(false, -1,
                    actionResult.getStatus().getMessage());
        }
        return actionResult;
    }

    @Override
    public ActionResult syncLocalToWeChat(WeChatMenu weChatMenu) {
        WeChatMenuCondition condition = new WeChatMenuCondition();
        WeChatMenuConditionParams params = new WeChatMenuConditionParams();
        List<Button> listButton = Lists.newArrayList();
        params.setAccountId(weChatMenu.getAccountId());
        condition.setParams(params);
        ActionResult<AccessTokenResult, String> actionResult = accessTokenService
                .getAccessTokenByAccountId(weChatMenu.getAccountId());
        if (actionResult.getStatus().isSuccess()) {
            AccessTokenResult accessTokenResult = actionResult.getResult();
            // 获取所有一级菜单
            List<WeChatMenu> listWxMenuFather = listWeChatMenuFirst(
                    weChatMenu.getAccountId());
            for (WeChatMenu aListWxMenuFather : listWxMenuFather) {
                Button button = new Button();
                button.setName(aListWxMenuFather.getName());
                // 没有子菜单
                if (StringUtils.isNotBlank(aListWxMenuFather.getType())) {
                    button.setName(aListWxMenuFather.getName());
                    button.setType(aListWxMenuFather.getType());
                    switch (aListWxMenuFather.getType()) {
                        case "view":
                            button.setUrl(aListWxMenuFather.getKey());
                            break;
                        case "media_id":
                            button.setMediaId(aListWxMenuFather.getKey());
                            break;
                        default:
                            button.setKey(aListWxMenuFather.getKey());
                    }
                    button.setSubButton(null);
                } else {
                    List<SubButton> listSubButton = Lists.newArrayList();
                    // 获取当前一级下单下的所有二级菜单
                    List<WeChatMenu> listWxMenuSecond = listWeChatMenuByPid(
                            aListWxMenuFather.getId());
                    for (WeChatMenu wxMenuSecond : listWxMenuSecond) {
                        SubButton subButton = new SubButton();
                        subButton.setName(wxMenuSecond.getName());
                        subButton.setType(wxMenuSecond.getType());
                        switch (wxMenuSecond.getType()) {
                            case "view":
                                subButton.setUrl(wxMenuSecond.getKey());
                                break;
                            case "media_id":
                                subButton.setMediaId(wxMenuSecond.getKey());
                                break;
                            default:
                                subButton.setKey(wxMenuSecond.getKey());
                        }
                        listSubButton.add(subButton);
                    }
                    button.setSubButton(listSubButton);
                }
                listButton.add(button);
            }
            // log.info(IceFastJsonUtils.toJsonString(listButton));
            WeChatMenuResult result = null;
            try {
                result = IceWeChatMenuUtils.createMenu(
                        accessTokenResult.getAccessToken(), listButton);
            } catch (IOException | NoSuchAlgorithmException
                    | KeyManagementException e) {
                e.printStackTrace();
                throw new CustomerException(
                        CustomerExceptionCode.NETWORK_EXCEPTION_CODE,
                        CustomerExceptionCode.NETWORK_EXCEPTION_MESSAGE);
            }
            if (SystemConstant.SUCCESS_STRING_0.equals(result.getErrorInfo().getErrCode())) {
                // 将现在的微信菜单的所有数据存入到为新菜单历史表中
                List<WeChatMenu> listWeChatMenu = listWeChatMenuByAll(
                        condition);
                String bakName = IceDateFormatUtils.getNowDateTime(
                        "yyyyMMddHHmmss") + RandomStringUtils.randomNumeric(10);
                MapperFactory mapperFactory = new DefaultMapperFactory.Builder()
                        .build();
                for (WeChatMenu weChatMenuForHistory : listWeChatMenu) {
                    WeChatMenuHistory weChatMenuHistory = mapperFactory
                            .getMapperFacade()
                            .map(weChatMenuForHistory, WeChatMenuHistory.class);
                    weChatMenuHistory.setId(IdUtil.simpleUUID());
                    weChatMenuHistory.setBakname(bakName);
                    weChatMenuHistory.setMenuId(weChatMenuForHistory.getId());
                    weChatMenuHistoryDao
                            .saveWeChatMenuHistory(weChatMenuHistory);
                }
                actionResult = actionResultService.callBackResult(true, 0,
                        "自定义菜单同步成功，请到微信中进行查看");
            } else {
                actionResult = actionResultService.callBackResult(false, -1,
                        result.getErrorInfo().getErrMsg());
            }
        }
        return actionResult;
    }

}
