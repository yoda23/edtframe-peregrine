package cc.edt.frame.admin.dao.base;


import cc.edt.frame.model.condition.FindCondition;
import cc.edt.frame.model.entity.base.Mechanisms;
import cc.edt.frame.model.entity.base.UserMechanismsLinked;

import java.util.List;

/**
 * 机构信息
 *
 * @author 刘钢
 * @date 2018/1/12 13:36
 */
public interface MechanismsDao {

    /**
     * 保存组织机构
     *
     * @param mechanisms mechanisms
     * @author 刘艳柔
     * @date 2018/8/21 16:13
     */
    void saveMechanisms(Mechanisms mechanisms);

    /**
     * 修改组织机构
     *
     * @param mechanisms mechanisms
     * @author 刘艳柔
     * @date 2018/8/21 16:14
     */
    void updateMechanisms(Mechanisms mechanisms);

    /**
     * 删除组织机构
     *
     * @param id id
     * @author 刘艳柔
     * @date 2018/8/21 16:14
     */
    void deleteMechanisms(String id);

    /**
     * 根据机构编码查询机构信息
     *
     * @param code code
     * @return cc.edt.frame.core.mechanisms.entity.Mechanisms
     * @author 刘艳柔
     * @date 2018/8/21 16:14
     */
    Mechanisms getMechanismsByCode(String code);

    /**
     * 根据机构名称查询机构信息
     *
     * @param name name
     * @return cc.edt.frame.core.mechanisms.entity.Mechanisms
     * @author 刘艳柔
     * @date 2018/8/21 16:14
     */
    Mechanisms getMechanismsByName(String name);

    /**
     * 分页查询组织机构
     *
     * @param condition codition
     * @return java.util.List<cc.edt.frame.core.mechanisms.entity.Mechanisms>
     * @author 刘艳柔
     * @date 2018/8/21 16:14
     */
    List<Mechanisms> listMechanismsByCondition(FindCondition condition);

    /**
     * 根据组织机构ID查询机构信息
     *
     * @param id id
     * @return cc.edt.frame.core.mechanisms.entity.Mechanisms
     * @author 刘艳柔
     * @date 2018/8/21 16:14
     */
    Mechanisms getMechanismsById(String id);

    /**
     * 删除所有组织机构
     *
     * @author 刘艳柔
     * @date 2018/8/21 16:15
     */
    void deleteAllMechanisms();

    /**
     * 获取组织机构子节点
     *
     * @param mechanismsId mechanismsId
     * @return java.util.List<cc.edt.frame.core.mechanisms.entity.Mechanisms>
     * @author 刘艳柔
     * @date 2018/8/21 16:15
     */
    List<Mechanisms> listMechanismsChild(String mechanismsId);

    /**
     * 获取所有父节点信息
     *
     * @param mechanismsId mechanismsId
     * @return java.util.List<com.edt.entity.Mechanisms>
     * @author 刘钢
     * @date 2017-07-10 13:17
     */

    List<Mechanisms> listMechanismsParentById(String mechanismsId);

    /**
     * 保存用户机构关联表
     *
     * @param userMechanismsLinked userMechanismsLinked
     * @author 刘钢
     * @date 2017-07-06 10:25
     */

    void saveUserMechanismsLinked(UserMechanismsLinked userMechanismsLinked);

    /**
     * 根据机构ID删除用户机构关联表
     *
     * @param mechanismsId mechanismsId
     * @author 刘钢
     * @date 2017-07-06 10:25
     */

    void deleteUserMechanismsLinked(String mechanismsId);


}
