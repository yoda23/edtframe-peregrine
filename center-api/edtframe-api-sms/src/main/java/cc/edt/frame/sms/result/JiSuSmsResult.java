package cc.edt.frame.sms.result;

import lombok.Data;

import java.io.Serializable;

/**
 * 急速短信返回
 *
 * @author 刘钢
 * @date 2018/12/19 11:48
 */
@Data
public class JiSuSmsResult implements Serializable {
    private static final long serialVersionUID = -3123221134575717459L;
    private Boolean success;
    private String message;
}
