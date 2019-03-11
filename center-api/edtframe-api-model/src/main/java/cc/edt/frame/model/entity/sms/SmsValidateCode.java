package cc.edt.frame.model.entity.sms;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 手机验证码信息
 *
 * @author 刘钢
 * @date 2017/12/18 13:33
 */
@Data
public class SmsValidateCode implements Serializable {

    private static final long serialVersionUID = -7245439400818742681L;
    private String phone;
    private String code;
    private Date validTime;

}
