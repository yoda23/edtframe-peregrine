package cc.edt.frame.model.entity.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 组织机构信息
 *
 * @author 刘钢
 * @date 2017/12/18 13:30
 */
@Data
public class Mechanisms implements Serializable {
    private static final long serialVersionUID = -144190076622140757L;
    private String id;
    private String parentId;
    private String name;
    private String code;
    private String linkName;
    private String linkPhone;
    private String parentName;
    private String addUser;
    private String addUserName;
    private Date addTime;

}
