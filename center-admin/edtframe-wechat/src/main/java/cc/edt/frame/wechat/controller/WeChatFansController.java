package cc.edt.frame.wechat.controller;

import cc.edt.frame.common.controller.BaseController;
import cc.edt.frame.common.result.TableResultService;
import cc.edt.frame.model.condition.WeChatFansCondition;
import cc.edt.frame.model.condition.params.WeChatFansConditionParams;
import cc.edt.frame.model.entity.base.User;
import cc.edt.frame.model.entity.wechat.WeChatFans;
import cc.edt.frame.wechat.service.WeChatFansService;
import cc.edt.frame.wechat.vo.condition.WeChatFansConditionVO;
import com.google.common.collect.Maps;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 微信粉丝
 *
 * @author 刘钢
 * @date 2018/9/17 11:31
 */
@Controller
@CrossOrigin
@RequestMapping("/wechat/fans")
public class WeChatFansController extends BaseController {
    @Resource
    private WeChatFansService weChatFansService;
    @Resource
    private TableResultService tableResultService;

    /**
     * 根据条件查询微信粉丝
     *
     * @param conditionVO conditionVO
     * @author 奚艺轩 2017-6-22上午11:50:21
     */
    @RequestMapping("/condition")
    @ResponseBody
    public void listWxFansByCondition(WeChatFansConditionVO conditionVO) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder()
                .build();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        User user = (User) httpSession.getAttribute("USER");
        WeChatFansCondition weChatFansCondition = mapper.map(conditionVO,
                WeChatFansCondition.class);
        WeChatFansConditionParams weChatFansConditionParams = mapper
                .map(conditionVO, WeChatFansConditionParams.class);
        weChatFansConditionParams.setUserId(user.getId());
        weChatFansCondition.setParams(weChatFansConditionParams);
        List<WeChatFans> listWeChatFans = weChatFansService
                .listWeChatFansByCondition(weChatFansCondition);
        writerToPageByJson(tableResultService
                .tableResult(weChatFansCondition.getTotal(), listWeChatFans));
    }

    /**
     * 粉丝同步
     *
     * @param accountId accountId
     * @author 刘钢
     * @date 2018/9/17 14:37
     */
    @RequestMapping("/toSyncWeChatFans/{accountId}")
    @ResponseBody
    public void syncWeChatFans(@PathVariable("accountId") String accountId) {
        writerToPageByJson(weChatFansService.syncWeChatFans(accountId));
    }
}
