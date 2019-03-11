package cc.edt.frame.model.entity.base;

import lombok.Data;

import java.io.Serializable;

/**
 * 权限信息
 *
 * @author 刘钢
 * @date 2017/12/18 13:31
 */
@Data
public class Rights implements Serializable {

    private static final long serialVersionUID = -7810769625187127602L;
    private String id;
    private String name;
    private String parentId;
}
