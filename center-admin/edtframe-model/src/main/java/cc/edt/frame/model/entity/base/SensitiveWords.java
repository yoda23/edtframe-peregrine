package cc.edt.frame.model.entity.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 敏感词实体类
 *
 * @author 姜宁
 * @date 2018-11-16 14:27:24
 */
@Data
public class SensitiveWords implements Serializable {
    private static final long serialVersionUID = 4510961123184331211L;
    /**
     * 主键ID
     */
    private String id;
    /**
     * 敏感词内容
     */
    private String content;
    /**
     * 添加时间
     */
    private Date addTime;
    /**
     * 添加人ID
     */
    private String addUser;
    /**
     * 添加人姓名
     */
    private String addUserName;
}
