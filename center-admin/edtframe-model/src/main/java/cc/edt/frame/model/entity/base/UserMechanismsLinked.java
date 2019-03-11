package cc.edt.frame.model.entity.base;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户组织机构信息
 *
 * @author 刘钢
 * @date 2017-07-06
 */
@Data
public class UserMechanismsLinked implements Serializable {

    private static final long serialVersionUID = -6135553015058755870L;
    private String userId;
    private String mechanismsId;

}
