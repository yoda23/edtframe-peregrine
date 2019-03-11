package cc.edt.frame.sensitivewords.controller;

import cc.edt.frame.common.controller.BaseController;
import cc.edt.frame.common.result.ActionResult;
import cc.edt.frame.common.result.TableResultService;
import cc.edt.frame.model.condition.SensitiveWordsCondition;
import cc.edt.frame.model.condition.params.SensitiveWordsConditionParams;
import cc.edt.frame.model.entity.base.SensitiveWords;
import cc.edt.frame.model.entity.base.User;
import cc.edt.frame.sensitivewords.service.SensitiveWordsService;
import cc.edt.frame.sensitivewords.vo.condition.SensitiveWordsConditionVO;
import cn.hutool.core.util.IdUtil;
import com.google.common.collect.Maps;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 敏感词的控制器
 *
 * @author 姜宁
 * @date 2018-11-16 15:00:07
 */
@Controller
@RequestMapping("sensitive-words")
public class SensitiveWordsController extends BaseController {

    @Resource
    private SensitiveWordsService sensitiveWordsService;
    @Resource
    private TableResultService tableResultService;

    /**
     * 根据条件查询敏感词
     *
     * @param conditionVO conditionVO
     * @author 姜宁 2018-11-13 0013 14:23:47
     */
    @PostMapping("condition")
    @ResponseBody
    public void listSensitiveByCondition(
            SensitiveWordsConditionVO conditionVO) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder()
                .build();
        SensitiveWordsCondition sensitiveCondition = mapperFactory
                .getMapperFacade()
                .map(conditionVO, SensitiveWordsCondition.class);
        SensitiveWordsConditionParams sensitiveConditionParams = mapperFactory
                .getMapperFacade()
                .map(conditionVO, SensitiveWordsConditionParams.class);
        sensitiveCondition.setParams(sensitiveConditionParams);
        List<SensitiveWords> listSensitiveWords = sensitiveWordsService
                .listSensitiveWordsByCondition(sensitiveCondition);
        writerToPageByJsonNoNull(tableResultService.tableResult(
                sensitiveCondition.getTotal(), listSensitiveWords));
    }

    /**
     * 删除敏感词
     *
     * @param id id
     * @author 丁祎凡 2018/11/13 14:41
     */
    @PostMapping("delete/{id}")
    @ResponseBody
    public void deleteSensitive(@PathVariable("id") String id) {
        writerToPageByJson(sensitiveWordsService.deleteSensitiveWords(id));
    }

    /**
     * 保存敏感词
     *
     * @param sensitiveWords sensitiveWords
     * @author 姜宁 2018/11/14 9:18
     */
    @PostMapping("save")
    @ResponseBody
    public void saveSensitive(SensitiveWords sensitiveWords) {
        User user = (User) httpSession.getAttribute("USER");
        sensitiveWords.setId(IdUtil.simpleUUID());
        sensitiveWords.setAddTime(new Date());
        sensitiveWords.setAddUser(user.getId());
        ActionResult actionResult = sensitiveWordsService
                .saveSensitiveWords(sensitiveWords);
        writerToPageByJson(actionResult);
    }

    /**
     * 跳转到修改页面
     *
     * @param id    id
     * @param model model
     * @return java.lang.String
     * @author 丁祎凡 2018/11/14 9:19
     */
    @GetMapping("toUpdate/{id}")
    public String toUpdate(@PathVariable("id") String id, Model model) {
        SensitiveWords sensitiveWords = sensitiveWordsService
                .getSensitiveWordsById(id);
        if (sensitiveWords != null) {
            model.addAttribute("sensitiveWords", sensitiveWords);
            return "sensitiveWords/sensitiveWordsUpdate";
        }
        return "none";
    }

    /**
     * 修改敏感词信息
     *
     * @param sensitiveWords sensitiveWords
     * @author 姜宁 2018/11/14 9:20
     */
    @PostMapping("update")
    @ResponseBody
    public void updateSensitive(SensitiveWords sensitiveWords) {
        writerToPageByJson(
                sensitiveWordsService.updateSensitiveWords(sensitiveWords));
    }

    /**
     * 批量导入敏感词
     *
     * @param filePath filePath
     * @author 姜宁 2018-11-13 14:56:29
     */
    @RequestMapping("importSensitiveFromExcel")
    public void importSensitiveFromExcel(String filePath) {
        User user = (User) httpSession.getAttribute("USER");
        writerToPageByJson(
                sensitiveWordsService.importFromExcel(filePath, user.getId()));
    }

}
