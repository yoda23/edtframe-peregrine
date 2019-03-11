package cc.edt.frame.sensitivewords.service;

import cc.edt.frame.common.result.ActionResult;
import cc.edt.frame.model.condition.FindCondition;
import cc.edt.frame.model.entity.base.SensitiveWords;

import java.util.List;

/**
 * 敏感词的service接口
 *
 * @author 姜宁
 * @date 2018-11-16 14:45:37
 */
public interface SensitiveWordsService {

    /**
     * 根据条件查询敏感词
     *
     * @param condition condition
     * @return java.util.List<cc.edt.frame.model.entity.base.SensitiveWords>
     * @author 姜宁 2018-11-13 14:22:34
     */
    List<SensitiveWords> listSensitiveWordsByCondition(FindCondition condition);

    /**
     * 根据id查询敏感词
     *
     * @param id id
     * @return cc.edt.frame.model.entity.base.SensitiveWords
     * @author 姜宁 2018-11-16 0016 14:47:18
     */
    SensitiveWords getSensitiveWordsById(String id);

    /**
     * 删除敏感词
     *
     * @param id id
     * @return cc.edt.frame.common.result.ActionResult
     * @author 丁祎凡 2018/11/13 14:11
     */
    ActionResult deleteSensitiveWords(String id);

    /**
     * 保存敏感词
     *
     * @param sensitiveWords sensitiveWords
     * @return cc.edt.frame.common.result.ActionResult
     * @author 丁祎凡 2018/11/13 15:03
     */
    ActionResult saveSensitiveWords(SensitiveWords sensitiveWords);

    /**
     * 修改敏感词
     *
     * @param sensitiveWords sensitiveWords
     * @return cc.edt.frame.common.result.ActionResult
     * @author 丁祎凡 2018/11/13 15:03
     */
    ActionResult updateSensitiveWords(SensitiveWords sensitiveWords);

    /**
     * 从excel表格导入
     *
     * @param filePath filePath
     * @param userId   userId
     * @return cc.edt.frame.common.result.ActionResult
     * @author 姜宁 2018-11-13 0013 14:57:36
     */
    ActionResult importFromExcel(String filePath, String userId);

}
