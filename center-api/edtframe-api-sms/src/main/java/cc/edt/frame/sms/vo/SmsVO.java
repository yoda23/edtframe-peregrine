package cc.edt.frame.sms.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 短信
 *
 * @author 刘钢
 * @date 2018/12/19 13:08
 */
@Data
public class SmsVO implements Serializable {
    private static final long serialVersionUID = 7856426083765172769L;
    private String phone;
    private String content;
}
