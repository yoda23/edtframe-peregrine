package cc.edt.frame.base.service;

import java.util.List;

import cc.edt.frame.common.result.ActionResult;
import cc.edt.frame.model.condition.FindCondition;
import cc.edt.frame.model.entity.base.Mechanisms;

/**
 * 组织机构信息
 *
 * @author 刘钢
 * @date 2017/12/18 13:54
 */
public interface MechanismsService {

    /**
     * 保存组织机构
     *
     * @param mechanisms mechanisms
     * @return cc.edt.frame.core.mechanisms.result.ActionResult
     * @author 刘艳柔
     * @date 2018/8/21 16:16
     */
    ActionResult saveMechanisms(Mechanisms mechanisms);

    /**
     * 修改组织机构
     *
     * @param mechanisms mechanisms
     * @return cc.edt.frame.core.mechanisms.result.ActionResult
     * @author 刘艳柔
     * @date 2018/8/21 16:16
     */
    ActionResult updateMechanisms(Mechanisms mechanisms);

    /**
     * 删除组织机构
     *
     * @param id id
     * @return cc.edt.frame.core.mechanisms.result.ActionResult
     * @author 刘艳柔
     * @date 2018/8/21 16:16
     */
    ActionResult deleteMechanisms(String id);

    /**
     * 根据机构编码查询机构信息
     *
     * @param code code
     * @return cc.edt.frame.core.mechanisms.entity.Mechanisms
     * @author 刘艳柔
     * @date 2018/8/21 16:17
     */
    Mechanisms getMechanismsByCode(String code);

    /**
     * 根据机构名称查询机构信息
     *
     * @param name name
     * @return cc.edt.frame.core.mechanisms.entity.Mechanisms
     * @author 刘艳柔
     * @date 2018/8/21 16:17
     */
    Mechanisms getMechanismsByName(String name);

    /**
     * 分页查询组织机构
     *
     * @param findCondition findCondition
     * @return java.util.List<cc.edt.frame.core.mechanisms.entity.Mechanisms>
     * @author 刘艳柔
     * @date 2018/8/21 16:17
     */
    List<Mechanisms> listMechanismsByCondition(FindCondition findCondition);

    /**
     * 根据组织机构ID查询机构信息
     *
     * @param id id
     * @return cc.edt.frame.core.mechanisms.entity.Mechanisms
     * @author 刘艳柔
     * @date 2018/8/21 16:17
     */
    Mechanisms getMechanismsById(String id);

    /**
     * 获取组织机构子节点
     *
     * @param mechanismsId mechanismsId
     * @return java.util.List<cc.edt.frame.core.mechanisms.entity.Mechanisms>
     * @author 刘艳柔
     * @date 2018/8/21 16:17
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

}
