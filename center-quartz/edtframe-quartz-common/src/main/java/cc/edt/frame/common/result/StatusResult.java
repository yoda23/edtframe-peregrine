package cc.edt.frame.common.result;

import java.io.Serializable;

import lombok.Data;

/**
 * 状态结果
 *
 * @author 刘钢
 * @date 2018/8/11 21:11
 */
@Data
public class StatusResult implements Serializable {
    private static final long serialVersionUID = 1041685242521737979L;
    private boolean success;
    private Integer code;
    private String message;
}
