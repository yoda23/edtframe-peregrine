package cc.edt.frame.common.exception;

import java.util.ArrayDeque;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageConversionException;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Queues;

/**
 * 异常信息
 *
 * @author 刘钢
 * @date 2018/9/12 14:51
 */
class DefaultExceptionInfo {
    private final static Integer MIN_EXCEPTION_NUM = 9900;
    private final static Integer MAX_EXCEPTION_NUM = 9998;
    static Map<String, Map<Integer, String>> EXCEPTION_MAP;

    static {
        ArrayDeque<Integer> queue = Queues.newArrayDeque();
        for (int i = MIN_EXCEPTION_NUM; i < MAX_EXCEPTION_NUM; i++) {
            queue.add(i);
        }
        ImmutableMap.Builder<String, Map<Integer, String>> builder = new ImmutableMap.Builder<>();
        builder.put(NullPointerException.class.toString(),
                new ImmutableMap.Builder<Integer, String>()
                        .put(queue.pop(), "系统出现空指针异常，请联系管理员").build());
        builder.put(ArithmeticException.class.toString(),
                new ImmutableMap.Builder<Integer, String>()
                        .put(queue.pop(), "系统出现算数异常，请联系管理员").build());
        builder.put(ClassCastException.class.toString(),
                new ImmutableMap.Builder<Integer, String>()
                        .put(queue.pop(), "系统出现类型强制转换异常，请联系管理员").build());
        builder.put(HttpMessageConversionException.class.toString(),
                new ImmutableMap.Builder<Integer, String>()
                        .put(queue.pop(), "系统出现参数转换异常，请联系管理员").build());
        builder.put(DataIntegrityViolationException.class.toString(),
                new ImmutableMap.Builder<Integer, String>()
                        .put(queue.pop(), "违反数据操作约束，不能执行此操作").build());
        builder.put(ClassNotFoundException.class.toString(),
                new ImmutableMap.Builder<Integer, String>()
                        .put(queue.pop(), "系统出现没有找到对应类异常，去请联系管理员").build());
        builder.put(ArrayIndexOutOfBoundsException.class.toString(),
                new ImmutableMap.Builder<Integer, String>()
                        .put(queue.pop(), "系统出现数组越界异常，去请联系管理员").build());
        builder.put(IllegalArgumentException.class.toString(),
                new ImmutableMap.Builder<Integer, String>()
                        .put(queue.pop(), "系统出现方法参数错误异常，去请联系管理员").build());
        builder.put(StackOverflowError.class.toString(),
                new ImmutableMap.Builder<Integer, String>()
                        .put(queue.pop(), "系统出现堆栈溢出错误异常，去请联系管理员").build());
        EXCEPTION_MAP = builder.build();
    }

    private DefaultExceptionInfo() {
    }
}
