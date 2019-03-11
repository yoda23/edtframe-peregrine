package cc.edt.frame.base.service;

import java.util.List;

import cc.edt.frame.model.entity.base.Rights;

/**
 * 权限信息
 *
 * @author 刘钢
 * @date 2017/12/18 13:59
 */
public interface RightsService {

    /**
     * 获取所有权限
     *
     * @return java.util.List<com.edt.entity.Rights>
     * @author 刘钢
     * @date 2017-05-18 11:46
     */

    List<Rights> listRights();
}
