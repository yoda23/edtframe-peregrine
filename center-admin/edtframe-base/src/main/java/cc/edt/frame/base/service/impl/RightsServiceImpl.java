package cc.edt.frame.base.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cc.edt.frame.admin.dao.base.RightsDao;
import cc.edt.frame.base.service.RightsService;
import cc.edt.frame.model.entity.base.Rights;

/**
 * 权限信息
 *
 * @author 刘钢
 * @date 2018/6/21 10:25
 */
@Service
public class RightsServiceImpl implements RightsService {

    @Resource
    private RightsDao rightsDao;

    @Override
    public List<Rights> listRights() {
        return rightsDao.listRights();
    }

}
