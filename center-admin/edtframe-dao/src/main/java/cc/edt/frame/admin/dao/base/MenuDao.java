package cc.edt.frame.admin.dao.base;


import java.util.List;

import cc.edt.frame.model.entity.base.Menu;

/**
 * 菜单信息
 *
 * @author 刘钢
 * @date 2018/1/12 13:36
 */
public interface MenuDao {
    /**
     * 查询所有的菜单
     *
     * @return List<Menu>
     * @author 刘钢
     * @date 2017/5/17 22:43
     */
    List<Menu> listMenu();

}
