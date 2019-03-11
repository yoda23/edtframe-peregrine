package cc.edt.frame.wechat.service.impl;

import cc.edt.frame.common.constant.CommonDictionary;
import cc.edt.frame.common.result.ActionResult;
import cc.edt.frame.common.result.ActionResultService;
import cc.edt.frame.admin.dao.wechat.WeChatAccountDao;
import cc.edt.frame.model.condition.FindCondition;
import cc.edt.frame.model.entity.wechat.WeChatAccount;
import cc.edt.frame.wechat.service.WeChatAccountService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

/**
 * 微信账号信息
 *
 * @author 刘钢
 * @date 2018/8/28 21:14
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class WeChatAccountServiceImpl implements WeChatAccountService {
    @Resource
    private WeChatAccountDao weChatAccountDao;
    @Resource
    private ActionResultService actionResultService;
    @Resource
    private CommonDictionary commonDictionary;

    @Override
    public ActionResult saveWeChatAccount(WeChatAccount weChatAccount) {
        ActionResult actionResult = checkSame(weChatAccount);
        if (actionResult.getStatus().isSuccess()) {
            weChatAccountDao.saveWeChatAccount(weChatAccount);
            actionResult = actionResultService.callBackResult(true, 0, "添加成功");
        }
        return actionResult;
    }

    @Override
    public ActionResult updateWeChatAccount(WeChatAccount weChatAccount) {
        ActionResult actionResult = checkSame(weChatAccount);
        if (actionResult.getStatus().isSuccess()) {
            WeChatAccount weChatAccountId = weChatAccountDao
                    .getWeChatAccountById(weChatAccount.getId());
            weChatAccountDao.updateWeChatAccount(weChatAccount);
            if (weChatAccountId != null
                    && !StringUtils.equals(weChatAccount.getCertificatePath(),
                    weChatAccountId.getCertificatePath())
                    && weChatAccount.getCertificatePath() != null) {
                File certificateFile = new File(
                        commonDictionary.getUploadPathDisk() + "/"
                                + weChatAccountId.getCertificatePath());
                certificateFile.delete();
            }
            if (weChatAccountId != null
                    && !StringUtils.equals(weChatAccount.getPicPath(),
                    weChatAccountId.getPicPath())
                    && weChatAccountId.getPicPath() != null) {
                File picfile = new File(commonDictionary.getUploadPathDisk()
                        + "/" + weChatAccountId.getPicPath());
                picfile.delete();
            }
            actionResult = actionResultService.callBackResult(true, 0, "修改成功");
        }
        return actionResult;

    }

    @Override
    public ActionResult deleteWeChatAccount(String id) {
        WeChatAccount weChatAccountId = weChatAccountDao
                .getWeChatAccountById(id);
        if (weChatAccountId != null) {
            weChatAccountDao.deleteWeChatAccount(id);
            File certificateFile = new File(commonDictionary.getUploadPathDisk()
                    + "/" + weChatAccountId.getCertificatePath());
            certificateFile.delete();
            File picFile = new File(commonDictionary.getUploadPathDisk() + "/"
                    + weChatAccountId.getPicPath());
            picFile.delete();
        }
        return actionResultService.callBackResult(true, 0, "删除成功");
    }

    @Override
    public WeChatAccount getWeChatAccountById(String id) {
        return weChatAccountDao.getWeChatAccountById(id);
    }

    @Override
    public List<WeChatAccount> listWeChatAccountByCondition(
            FindCondition findCondition) {
        PageHelper.startPage(findCondition.getPage(), findCondition.getLimit());
        List<WeChatAccount> listWeChatAccount = weChatAccountDao
                .listWeChatAccountByCondition(findCondition);
        PageInfo<WeChatAccount> pageInfo = new PageInfo<>(listWeChatAccount);
        findCondition.setTotal(pageInfo.getTotal());
        if (findCondition.getPage() == 0 && findCondition.getLimit() == 0) {
            findCondition.setTotal((long) listWeChatAccount.size());
        }
        return listWeChatAccount;
    }

    /**
     * 重复验证
     *
     * @param weChatAccount weChatAccount
     * @return cc.edt.frame.common.result.ActionResult
     * @author 刘钢
     * @date 2019/1/24 9:12
     */
    private ActionResult checkSame(WeChatAccount weChatAccount) {
        List<WeChatAccount> listWeChatAccount = weChatAccountDao
                .checkSame(weChatAccount);
        for (WeChatAccount checkWeChatAccount : listWeChatAccount) {
            if (StringUtils.equals(weChatAccount.getName(),
                    checkWeChatAccount.getName())
                    && !StringUtils.equals(weChatAccount.getId(),
                    checkWeChatAccount.getId())) {
                return actionResultService.callBackResult(false, -1, "名称重复");
            }
            if (StringUtils.equals(weChatAccount.getOriginalId(),
                    checkWeChatAccount.getOriginalId())
                    && !StringUtils.equals(weChatAccount.getId(),
                    checkWeChatAccount.getId())) {
                return actionResultService.callBackResult(false, -1,
                        "微信原始ID重复");
            }
            if (StringUtils.equals(weChatAccount.getAppId(),
                    checkWeChatAccount.getAppId())
                    && !StringUtils.equals(weChatAccount.getId(),
                    checkWeChatAccount.getId())) {
                return actionResultService.callBackResult(false, -1, "appId重复");
            }
            if (StringUtils.equals(weChatAccount.getAppSecret(),
                    checkWeChatAccount.getAppSecret())
                    && !StringUtils.equals(weChatAccount.getId(),
                    checkWeChatAccount.getId())) {
                return actionResultService.callBackResult(false, -1,
                        "appSecret重复");
            }
            if (StringUtils.equals(weChatAccount.getTokenFlag(),
                    checkWeChatAccount.getTokenFlag())
                    && !StringUtils.equals(weChatAccount.getId(),
                    checkWeChatAccount.getId())) {
                return actionResultService.callBackResult(false, -1,
                        "tokenFlag重复");
            }
        }
        return actionResultService.callBackResult();
    }
}
