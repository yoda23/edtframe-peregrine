package cc.edt.frame.base.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cc.edt.frame.common.result.TableResultService;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.google.common.collect.Maps;

import cc.edt.frame.common.controller.BaseController;
import cc.edt.frame.common.result.ActionResult;
import cc.edt.frame.base.service.MechanismsService;
import cc.edt.frame.base.vo.condition.MechanismsConditionVO;
import cc.edt.frame.model.condition.MechanismsCondition;
import cc.edt.frame.model.condition.params.MechanismsConditionParams;
import cc.edt.frame.model.entity.base.Mechanisms;
import cc.edt.frame.model.entity.base.User;
import cn.hutool.core.util.IdUtil;

/**
 * 组织机构
 *
 * @author 刘钢
 * @date 2017/12/18 14:11
 */
@CrossOrigin
@Controller
@RequestMapping("/mechanisms")
public class MechanismsController extends BaseController {
    @Resource
    private MechanismsService mechanismsService;
    @Resource
    private TableResultService tableResultService;

    /**
     * 添加
     *
     * @param mechanisms mechanisms
     * @author 刘艳柔
     * @date 2018/8/21 16:30
     */
    @PostMapping("/save")
    @ResponseBody
    public void saveMechanisms(Mechanisms mechanisms) {
        User user = (User) httpSession.getAttribute("USER");
        mechanisms.setId(IdUtil.simpleUUID());
        mechanisms.setAddTime(new Date());
        mechanisms.setAddUser(user.getId());
        writerToPageByJsonNoNull(mechanismsService.saveMechanisms(mechanisms));
    }

    /**
     * 修改
     *
     * @param mechanisms mechanisms
     * @author 刘艳柔
     * @date 2018/8/21 16:30
     */
    @PostMapping("/update")
    @ResponseBody
    public void updateMechanisms(Mechanisms mechanisms) {
        ActionResult actionResult = mechanismsService
                .updateMechanisms(mechanisms);
        writerToPageByJsonNoNull(actionResult);
    }

    /**
     * 删除
     *
     * @param id id
     * @author 刘艳柔
     * @date 2018/8/21 16:31
     */
    @PostMapping("/delete/{id}")
    @ResponseBody
    public void deleteMechanisms(@PathVariable("id") String id) {
        writerToPageByJsonNoNull(mechanismsService.deleteMechanisms(id));
    }

    /**
     * 根据ID获取组织机构信息
     *
     * @param id id
     * @author 刘艳柔
     * @date 2018/8/21 16:31
     */
    @GetMapping("/toUpdate/{id}")
    public String getMechanismsById(@PathVariable("id") String id,
                                    Model model) {
        Mechanisms mechanisms = mechanismsService.getMechanismsById(id);
        if (mechanisms != null) {
            if (mechanisms.getId().equals("0")) {
                mechanisms.setParentName("无上级");
            }
            model.addAttribute(mechanisms);
            return "mechanisms/mechanismsUpdate";
        } else {
            return "none";
        }
    }

    /**
     * 根据查询条件查询
     *
     * @param conditionVO conditionVO
     * @author 刘艳柔
     * @date 2018/8/21 16:31
     */
    @PostMapping("/condition")
    @ResponseBody
    public void listMechanismByCondition(MechanismsConditionVO conditionVO) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder()
                .build();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        User user = (User) httpSession.getAttribute("USER");
        if (StringUtils.isBlank(conditionVO.getOrganizationId())) {
            conditionVO.setOrganizationId(user.getMechanismsId());
        }
        MechanismsCondition mechanismsCondition = mapper.map(conditionVO,
                MechanismsCondition.class);
        MechanismsConditionParams params = mapper.map(conditionVO,
                MechanismsConditionParams.class);
        mechanismsCondition.setParams(params);
        List<Mechanisms> listMechanisms = mechanismsService
                .listMechanismsByCondition(mechanismsCondition);
        writerToPageByJsonNoNull(tableResultService
                .tableResult(mechanismsCondition.getTotal(), listMechanisms));
    }

    /**
     * 获取当前登录用户组织机构
     *
     * @author 刘艳柔
     * @date 2018/8/21 16:31
     */
    @PostMapping("/user/tree")
    @ResponseBody
    public void listMechanismsChild() {
        User user = (User) httpSession.getAttribute("USER");
        List<Mechanisms> listMechanisms = mechanismsService
                .listMechanismsChild(user.getMechanismsId());
        writerToPageByJsonNoNull(listMechanisms);
    }

}
