package cc.edt.frame.model.condition;

import lombok.Data;

import java.io.Serializable;

/**
 * 条件查询基础类
 *
 * @author 刘钢
 * @date 2017/12/18 10:57
 */
@Data
public class FindCondition implements Serializable {
    private static final long serialVersionUID = 2132944047590456924L;
    private Integer page;
    private Integer limit;
    private Long total;
}
