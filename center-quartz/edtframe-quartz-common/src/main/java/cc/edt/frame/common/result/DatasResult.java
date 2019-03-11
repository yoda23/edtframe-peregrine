package cc.edt.frame.common.result;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

import lombok.Data;

/**
 * 返回结果集
 *
 * @author 刘钢
 * @date 2019/1/22 15:06
 */
@Data
public class DatasResult<T> implements Serializable {
    private static final long serialVersionUID = 2568315085711500083L;
    @JSONField(name = "datas", serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private T datas;
}
