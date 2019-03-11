package cc.edt.frame.sensitivewords.service.impl;

import cc.edt.frame.common.constant.CommonDictionary;
import cc.edt.frame.common.constant.SensitiveConstant;
import cc.edt.frame.common.result.ActionResult;
import cc.edt.frame.common.result.ActionResultService;
import cc.edt.frame.admin.dao.base.SensitiveWordsDao;
import cc.edt.frame.model.condition.FindCondition;
import cc.edt.frame.model.entity.base.SensitiveWords;
import cc.edt.frame.sensitivewords.service.SensitiveWordsService;
import cn.hutool.core.util.IdUtil;
import cn.hutool.poi.excel.ExcelUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 敏感词service接口的实现类
 *
 * @author 姜宁
 * @date 2018-11-16 14:47:39
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SensitiveWordsServiceImpl implements SensitiveWordsService {

    @Resource
    private SensitiveWordsDao sensitiveWordsDao;
    @Resource
    private ActionResultService actionResultService;
    @Resource
    private CommonDictionary commonDictionary;

    @Override
    public List<SensitiveWords> listSensitiveWordsByCondition(
            FindCondition condition) {
        PageHelper.startPage(condition.getPage(), condition.getLimit());
        List<SensitiveWords> listSensitiveWords = sensitiveWordsDao
                .listSensitiveWordsByCondition(condition);
        PageInfo<SensitiveWords> pageInfo = new PageInfo<>(listSensitiveWords);
        condition.setTotal(pageInfo.getTotal());
        return listSensitiveWords;
    }

    @Override
    public SensitiveWords getSensitiveWordsById(String id) {
        return sensitiveWordsDao.getSensitiveWordsById(id);
    }

    @Override
    public ActionResult deleteSensitiveWords(String id) {
        sensitiveWordsDao.deleteSensitiveWords(id);
        sensitiveOperationInDFA();
        return actionResultService.callBackResult(true, 0, "敏感词删除成功");
    }

    @Override
    public ActionResult saveSensitiveWords(SensitiveWords sensitiveWords) {
        //sensitiveWordsDao.saveSensitiveWords(sensitiveWords);
        sensitiveOperationInDFA();
        return actionResultService.callBackResult(true, 0, "敏感词保存成功");
    }

    @Override
    public ActionResult updateSensitiveWords(SensitiveWords sensitiveWords) {
        sensitiveWordsDao.updateSensitiveWords(sensitiveWords);
        sensitiveOperationInDFA();
        return actionResultService.callBackResult(true, 0, "敏感词修改成功");
    }

    @Override
    public ActionResult importFromExcel(String filePath, String userId) {
        int errorCount = 0;
        List<SensitiveWords> sensitiveList = Lists.newArrayList();
        List<List<Object>> readAll = ExcelUtil
                .getReader(
                        commonDictionary.getUploadPathDisk() + "/" + filePath)
                .read();
        for (List<Object> rows : readAll) {
            String content = String.valueOf(rows.get(0));
            if (StringUtils.isNotBlank(String.valueOf(rows.get(0)))) {
                SensitiveWords tempSensitiveWords = new SensitiveWords();
                tempSensitiveWords.setId(IdUtil.simpleUUID());
                tempSensitiveWords.setAddTime(new Date());
                tempSensitiveWords.setAddUser(userId);
                tempSensitiveWords.setContent(content);
                sensitiveList.add(tempSensitiveWords);
            } else {
                errorCount++;
            }
        }
        List<SensitiveWords> sensitiveListBatch = Lists.newArrayList();
        for (int i = 0; i < sensitiveList.size(); i++) {
            if (i != 0 && i % commonDictionary.getBatch() == 0) {
                sensitiveWordsDao.saveSensitiveWordsByBatch(sensitiveListBatch);
                sensitiveListBatch.clear();
            }
            sensitiveListBatch.add(sensitiveList.get(i));
        }
        // 将剩余的提交到数据库
        if (sensitiveListBatch.size() > 0) {
            sensitiveWordsDao.saveSensitiveWordsByBatch(sensitiveListBatch);
        }
        sensitiveOperationInDFA();
        return actionResultService.callBackResult(true, 0,
                "成功" + sensitiveList.size() + "条，失败：" + errorCount + "条");
    }

    /**
     * 敏感词在DFA中的操作
     *
     * @author 姜宁 2018-11-14 0014 8:56:51
     */
    private void sensitiveOperationInDFA() {
        List<String> sensitiveWordToDFA = sensitiveWordsDao.listSensitiveWordsContent();
        SensitiveConstant.SENSITIVE_TREE.clear();
        SensitiveConstant.SENSITIVE_TREE.addWords(sensitiveWordToDFA);

    }

}
