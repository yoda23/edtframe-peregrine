package cc.edt.frame.admin.dao.base;


import java.util.List;

import cc.edt.frame.model.entity.base.Rights;

/**
 * 权限信息
 *
 * @author 刘钢
 * @date 2017/12/18 13:28
 */
public interface RightsDao {
    /**
     * 获取所有权限
     *
     * @return java.util.List<com.edt.entity.Rights>
     * @author 刘钢
     * @date 2017/5/17 22:47
     */
    List<Rights> listRights();
}
