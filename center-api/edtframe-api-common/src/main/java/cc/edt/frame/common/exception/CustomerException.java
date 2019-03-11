package cc.edt.frame.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统自定义异常信息
 *
 * @author 刘钢
 * @date 2018/8/23 11:25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerException extends RuntimeException {
    private static final long serialVersionUID = -123172899461679919L;
    private Integer errorCode;
    private String errorMsg;

    public CustomerException(Integer errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
