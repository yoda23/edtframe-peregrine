package cc.edt.frame.common.result;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 表格返回
 *
 * @author 刘钢
 * @date 2019/1/11 15:09
 */
@Component
public class TableResultService {

    /**
     * 前端表格数据
     *
     * @param total total
     * @param list  list
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @author 刘钢
     * @date 2019/1/15 13:22
     */
    public Map<String, Object> tableResult(Long total, List list) {
        return tableResult(0, total, list);
    }

    /**
     * 前端表格数据
     *
     * @param code  code
     * @param total total
     * @param list  list
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @author 刘钢
     * @date 2019/1/15 13:22
     */
    public Map<String, Object> tableResult(Integer code, Long total,
                                           List list) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("code", code);
        map.put("count", total);
        map.put("data", list);
        return map;
    }
}
