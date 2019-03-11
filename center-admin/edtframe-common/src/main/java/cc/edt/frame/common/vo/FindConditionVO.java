package cc.edt.frame.common.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * 基础查询条件
 *
 * @author 刘钢
 * @date 2017/12/18 10:57
 */
@Data
public class FindConditionVO implements Serializable {
    private static final long serialVersionUID = -1537003473353455656L;
    private Integer page;
    private Integer limit;
}
