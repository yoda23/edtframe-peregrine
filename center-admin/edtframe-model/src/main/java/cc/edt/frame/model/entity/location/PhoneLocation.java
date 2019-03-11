package cc.edt.frame.model.entity.location;

import java.io.Serializable;

import lombok.Data;

/**
 * 手机号码归属地信息
 *
 * @author 刘钢
 * @date 2017/12/18 13:30
 */
@Data
public class PhoneLocation implements Serializable {

    private static final long serialVersionUID = 4154792663361271120L;

    private String id;
    private String prefix;
    private String mobileType;
    private String phoneHeader;
    private String province;
    private String city;
    private String areaCode;
    private String postCode;

}
