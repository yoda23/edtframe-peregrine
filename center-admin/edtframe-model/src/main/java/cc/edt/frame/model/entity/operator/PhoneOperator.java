package cc.edt.frame.model.entity.operator;

import java.io.Serializable;

import lombok.Data;

/**
 * 手机号码运营商信息
 *
 * @author 刘钢
 * @date 2017/12/18 13:31
 */
@Data
public class PhoneOperator implements Serializable {

    private static final long serialVersionUID = 4370637462208246356L;

    private String id;
    private String prefix;
    private String operator;
    private String operatorName;

}
