package cc.edt.frame.base.vo;

import java.io.Serializable;
import java.util.Date;

import cc.edt.frame.model.entity.base.Mechanisms;
import cc.edt.frame.model.entity.base.Role;
import lombok.Data;

/**
 * 用户信息
 *
 * @author 刘钢
 * @date 2017/12/18 13:33
 */
@Data
public class UserVO implements Serializable {
    private static final long serialVersionUID = -3677668804074317440L;
    private String id;
    private String mechanismsName;
    private String roleId;
    private String roleName;
    private String loginId;
    private String name;
    private Integer active;
    private String addUser;
    private Date addTime;
}
