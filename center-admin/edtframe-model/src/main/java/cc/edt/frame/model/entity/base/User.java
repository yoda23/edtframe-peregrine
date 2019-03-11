package cc.edt.frame.model.entity.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息
 *
 * @author 刘钢
 * @date 2017/12/18 13:33
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -8764745528293684510L;
    private String id;
    private String mechanismsId;
    private String roleId;
    private String loginId;
    private String loginPassword;
    private String name;
    private Integer active;
    private String addUser;
    private Date addTime;
    private String userMechanismsRights;
    private Role role;
    private Mechanisms mechanisms;
    private String oldPassword;
}
