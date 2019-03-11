package cc.edt.frame.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import cc.edt.frame.common.constant.CommonDictionary;
import cn.hutool.core.util.ReflectUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 初始化
 *
 * @author 刘钢
 * @date 2018/9/14 22:49
 */
@Slf4j
@Component
@Order(1)
public class SystemStarter implements CommandLineRunner {
    @Resource
    private CommonDictionary commonDictionary;

    @Override
    public void run(String... args)
            throws InvocationTargetException, IllegalAccessException {
        log.info("---------------------------------------------");
        log.info("|      " + commonDictionary.getApplicationTitle() + " "
                + commonDictionary.getApplicationVersion() + "       |");
        log.info("---------------------------------------------");
        log.info("--------------------系统开始初始化--------------------");
        print(commonDictionary);
        log.info("--------------------系统完成初始化--------------------");
    }

    /**
     * 打印数据字典内容
     *
     * @param object object
     * @author 刘钢
     * @date 2018/9/30 16:49
     */
    public void print(Object object)
            throws InvocationTargetException, IllegalAccessException {
        Field[] fieldArray = ReflectUtil.getFields(object.getClass());
        for (Field field : fieldArray) {
            String methodName = "get"
                    + StringUtils.substring(field.getName(), 0, 1).toUpperCase()
                    + StringUtils.substring(field.getName(), 1);
            Method method = ReflectUtil.getMethod(object.getClass(),
                    methodName);
            log.info(field.getName() + "-->" + method.invoke(object));
        }
    }
}
