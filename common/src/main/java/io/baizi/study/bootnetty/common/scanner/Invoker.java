package io.baizi.study.bootnetty.common.scanner;

import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author juwencheng
 * @date 2020 2020/7/9 10:23 下午
 */
@Data
public class Invoker {
    private Method method;
    private Object target;

    /**
     * 创建invoker对象
     * @param target
     * @param method
     * @return
     */
    public static Invoker createInvoker(Object target, Method method) {
        Invoker invoker = new Invoker();
        invoker.setTarget(target);
        invoker.setMethod(method);
        return invoker;
    }


    /**
     * 反射调用
     * @param params
     * @return 返回方法结果
     */
    public Object invoke(Object ...params) {
        try {
            return method.invoke(target, params);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
