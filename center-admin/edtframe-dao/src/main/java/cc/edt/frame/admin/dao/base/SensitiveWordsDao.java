package cc.edt.frame.admin.dao.base;

import java.util.List;

import cc.edt.frame.model.condition.FindCondition;
import cc.edt.frame.model.entity.base.SensitiveWords;

/**
 * 敏感词的dao
 *
 * @author 姜宁
 * @date 2018-11-13 13:51:07
 */
public interface SensitiveWordsDao {

    /**
     * 根据条件查询敏感词
     *
     * @param condition condition
     * @return java.util.List<cc.edt.frame.model.entity.base.SensitiveWords>
     * @author 姜宁 2018-11-13 13:52:11
     */
    List<SensitiveWords> listSensitiveWordsByCondition(FindCondition condition);

    /**
     * 查询所有敏感词
     *
     * @return java.util.List<java.lang.String>
     * @author 姜宁 2018-11-14 8:53:26
     */
    List<String> listSensitiveWordsContent();

    /**
     * 根据id查询敏感词信息
     *
     * @param id id
     * @return cc.edt.frame.model.entity.base.SensitiveWords
     * @author 姜宁 2018-11-16 0016 14:45:01
     */
    SensitiveWords getSensitiveWordsById(String id);

    /**
     * 删除敏感词
     *
     * @param id id
     * @author 丁祎凡 2018/11/13 14:06
     */
    void deleteSensitiveWords(String id);

    /**
     * 保存敏感词
     *
     * @param sensitiveWords sensitiveWords
     * @author 丁祎凡 2018/11/13 14:53
     */
    void saveSensitiveWords(SensitiveWords sensitiveWords);

    /**
     * 修改敏感词
     *
     * @param sensitiveWords sensitiveWords
     * @author 丁祎凡 2018/11/13 14:53
     */
    void updateSensitiveWords(SensitiveWords sensitiveWords);

    /**
     * 批量保存
     *
     * @param listSensitive listSensitive
     * @author 姜宁 2018-11-13 15:44:20
     */
    void saveSensitiveWordsByBatch(List<SensitiveWords> listSensitive);

    /**
     * 查询所有敏感词
     *
     * @return java.util.List<java.lang.String>
     * @author 姜宁 2018-11-16 0016 14:44:37
     */
    List<String> listSensitiveWords();
}
