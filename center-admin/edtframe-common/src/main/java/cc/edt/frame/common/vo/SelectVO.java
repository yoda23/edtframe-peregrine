package cc.edt.frame.common.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * 下拉框VO
 *
 * @author 刘钢
 * @date 2019/1/10 11:45
 */
@Data
public class SelectVO implements Serializable {
    private static final long serialVersionUID = 8949563162079653909L;
    private String name;
    private String value;
    private String selected;
    private String disabled;
}
