package cc.edt.frame.location.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 手机归属地信息
 *
 * @author 刘钢
 * @date 2018/12/27 11:17
 */
@Data
public class PhoneLocationInfo implements Serializable {
    private static final long serialVersionUID = 5048642001716532452L;
    private String province;
    private String city;
    private String operator;
}
