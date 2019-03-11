package cc.edt.frame.admin.dao.base;

import cc.edt.frame.model.condition.FindCondition;
import cc.edt.frame.model.entity.operator.PhoneOperator;

import java.util.List;

/**
 * 运营商信息
 *
 * @author 刘钢
 */
public interface PhoneOperatorDao {

    /**
     * 通过分页条件查询所有的运营商号码
     *
     * @param condition condition
     * @return java.util.List<com.edt.entity.PhoneOperator>
     * @author 刘钢
     * @date 2017/5/17 22:45
     */
    List<PhoneOperator> listPhoneOperatorByCondition(FindCondition condition);

    /**
     * 保存运营商号码
     *
     * @param phoneOperator phoneOperator
     * @author 刘钢
     * @date 2017/5/17 22:45
     */
    void savePhoneOperator(PhoneOperator phoneOperator);

    /**
     * 修改运营商号码
     *
     * @param phoneOperator phoneOperator
     * @author 刘钢
     * @date 2017/5/17 22:45
     */
    void updatePhoneOperator(PhoneOperator phoneOperator);

    /**
     * 删除运营商号码
     *
     * @param id id
     * @author 刘钢
     * @date 2017/5/17 22:46
     */
    void deletePhoneOperator(String id);

    /**
     * 根据id查询运营商信息
     *
     * @param id id
     * @return com.edt.entity.PhoneOperator
     * @author 刘钢
     * @date 2017/5/17 22:46
     */
    PhoneOperator getPhoneOperatorById(String id);

    /**
     * 根据号码前缀查询运营商
     *
     * @param prefix prefix
     * @return com.edt.entity.PhoneOperator
     * @author 刘钢
     * @date 2017/5/17 22:46
     */
    PhoneOperator getPhoneOperatorByPrefix(String prefix);
}
