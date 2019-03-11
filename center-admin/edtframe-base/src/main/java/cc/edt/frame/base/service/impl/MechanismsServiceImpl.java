package cc.edt.frame.base.service.impl;

import cc.edt.frame.admin.dao.base.MechanismsDao;
import cc.edt.frame.admin.dao.base.UserDao;
import cc.edt.frame.base.service.MechanismsService;
import cc.edt.frame.common.result.ActionResult;
import cc.edt.frame.common.result.ActionResultService;
import cc.edt.frame.model.condition.FindCondition;
import cc.edt.frame.model.entity.base.Mechanisms;
import cc.edt.frame.model.entity.base.UserMechanismsLinked;
import cn.hutool.core.collection.CollUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 组织机构
 *
 * @author 刘钢
 * @date 2018/6/21 16:17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MechanismsServiceImpl implements MechanismsService {

    private static final String TREE_ROOT = "0";
    @Resource
    private MechanismsDao mechanismsDao;
    @Resource
    private UserDao userDao;
    @Resource
    private ActionResultService actionResultService;

    @Override
    public ActionResult saveMechanisms(Mechanisms mechanisms) {
        ActionResult actionResult = check(mechanisms);
        if (actionResult.getStatus().isSuccess()) {
            mechanismsDao.saveMechanisms(mechanisms);
            // 获取添加机构的所有父机构
            List<Mechanisms> listMechanisms = mechanismsDao
                    .listMechanismsParentById(mechanisms.getParentId());
            // 遍历所有父机构，给对应的用户授权当前机构信息
            for (Mechanisms mechanismsFather : listMechanisms) {
                // 根据机构ID获取用户信息
                List<String> listUser = userDao
                        .listUserByMechanismsId(mechanismsFather.getId());
                for (String userId : listUser) {
                    UserMechanismsLinked userMechanismsLinked = new UserMechanismsLinked();
                    userMechanismsLinked.setMechanismsId(mechanisms.getId());
                    userMechanismsLinked.setUserId(userId);
                    mechanismsDao
                            .saveUserMechanismsLinked(userMechanismsLinked);
                }
            }
            actionResult = actionResultService.callBackResult(true, 0,
                    "机构添加成功");
        }
        return actionResult;
    }

    @Override
    public ActionResult updateMechanisms(Mechanisms mechanisms) {
        ActionResult actionResult = check(mechanisms);
        if (actionResult.getStatus().isSuccess()) {
            List<Mechanisms> listMechanisms = mechanismsDao
                    .listMechanismsChild(mechanisms.getId());
            List<String> listMechanismsId = Lists.newArrayList();
            for (Mechanisms mechanism : listMechanisms) {
                listMechanismsId.add(mechanism.getId());
            }
            // 所属机构不能是自己或自己的子机构
            if (StringUtils.equals(mechanisms.getId(), mechanisms.getParentId())
                    || CollUtil.contains(listMechanismsId,
                    mechanisms.getParentId())) {
                actionResult = actionResultService.callBackResult(false, -1,
                        "所属机构不能是自己或自己的子机构");
            } else {
                mechanismsDao.updateMechanisms(mechanisms);
                actionResult = actionResultService.callBackResult(true, 0,
                        "机构修改成功");
            }
        }
        return actionResult;
    }

    @Override
    public ActionResult deleteMechanisms(String id) {
        ActionResult actionResult = actionResultService.callBackResult(true, 0,
                "机构删除成功");
        if (!StringUtils.equals(id, TREE_ROOT)) {
            mechanismsDao.deleteUserMechanismsLinked(id);
            mechanismsDao.deleteMechanisms(id);
        } else {
            actionResult = actionResultService.callBackResult(true, 0,
                    "根机构不能删除");
        }
        // 角色对应的机构信息
        return actionResult;
    }

    @Override
    public Mechanisms getMechanismsByCode(String code) {
        return mechanismsDao.getMechanismsByCode(code);
    }

    @Override
    public Mechanisms getMechanismsByName(String name) {
        return mechanismsDao.getMechanismsByName(name);
    }

    @Override
    public List<Mechanisms> listMechanismsByCondition(
            FindCondition findCondition) {
        PageHelper.startPage(findCondition.getPage(), findCondition.getLimit());
        List<Mechanisms> listMechanisms = mechanismsDao
                .listMechanismsByCondition(findCondition);
        PageInfo<Mechanisms> pageInfo = new PageInfo<>(listMechanisms);
        findCondition.setTotal(pageInfo.getTotal());
        if (findCondition.getPage() == 0 && findCondition.getLimit() == 0) {
            findCondition.setTotal((long) listMechanisms.size());
        }
        return listMechanisms;
    }

    @Override
    public Mechanisms getMechanismsById(String id) {
        return mechanismsDao.getMechanismsById(id);
    }

    @Override
    public List<Mechanisms> listMechanismsChild(String mechanismsId) {
        return mechanismsDao.listMechanismsChild(mechanismsId);
    }

    @Override
    public List<Mechanisms> listMechanismsParentById(String mechanismsId) {
        return mechanismsDao.listMechanismsParentById(mechanismsId);
    }

    /**
     * 验证
     *
     * @param mechanisms mechanisms
     * @return cc.edt.frame.core.mechanisms.result.ActionResult
     * @author 刘艳柔
     * @date 2018/8/21 16:25
     */
    private ActionResult check(Mechanisms mechanisms) {
        ActionResult actionResult = actionResultService.callBackResult();
        if (StringUtils.equals(mechanisms.getId(), TREE_ROOT)
                && !mechanisms.getParentId().equals("-1")) {
            actionResult = actionResultService.callBackResult(false, -1,
                    "根机构不能修改所属机构");
        }
        Mechanisms mechanismsName = getMechanismsByName(mechanisms.getName());
        if (mechanismsName != null && !StringUtils
                .equals(mechanismsName.getId(), mechanisms.getId())) {
            actionResult = actionResultService.callBackResult(false, -1,
                    "机构名称重复");
        }
        Mechanisms mechanismsCode = getMechanismsByCode(mechanisms.getCode());
        if (mechanismsCode != null && !StringUtils
                .equals(mechanismsCode.getId(), mechanisms.getId())) {
            actionResult = actionResultService.callBackResult(false, -1,
                    "机构码重复");
        }
        return actionResult;
    }
}
