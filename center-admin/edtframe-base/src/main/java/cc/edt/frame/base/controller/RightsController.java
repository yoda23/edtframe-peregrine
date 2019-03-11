package cc.edt.frame.base.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.edt.frame.common.controller.BaseController;
import cc.edt.frame.common.result.ActionResultService;
import cc.edt.frame.base.service.RightsService;
import cc.edt.frame.model.entity.base.Rights;

/**
 * 权限信息
 *
 * @author 刘钢
 * @date 2018/8/15 10:27
 */
@CrossOrigin
@RestController
@RequestMapping("/rights")
public class RightsController extends BaseController {
    @Resource
    private RightsService rightsService;
    @Resource
    private ActionResultService actionResultService;

    @PostMapping("/list")
    public void listRights() {
        List<Rights> listRights = rightsService.listRights();
        writerToPageByJsonNoNull(listRights);
    }
}
