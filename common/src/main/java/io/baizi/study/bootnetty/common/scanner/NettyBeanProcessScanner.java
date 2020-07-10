package io.baizi.study.bootnetty.common.scanner;

import io.baizi.study.bootnetty.common.annotation.Cmd;
import io.baizi.study.bootnetty.common.annotation.Module;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 使用NettyBeanProcessScanner扫描符合条件的bean，创建缓存
 * @author juwencheng
 * @date 2020 2020/7/9 10:13 下午
 */
@Slf4j
@Component
public class NettyBeanProcessScanner implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // 1. 获取bean的class类型
        Class<?> aClass = bean.getClass();
        // 2. 类上是否有annotation
        boolean isPresent = aClass.isAnnotationPresent(Module.class);
        if (isPresent) {
            // 通过class获得methods
            Module module = aClass.getAnnotation(Module.class);
            Method[] methods = aClass.getMethods();
            for (Method method : methods) {

                Cmd cmd = method.getAnnotation(Cmd.class);
                if (cmd == null) {
                    continue;
                }
                String moduleValue = module.module();
                String cmdValue = cmd.cmd();

                // 把对应的反射对象和方法
                if (InvokerTable.getInvoker(moduleValue, cmdValue) == null) {
                    InvokerTable.addInvoker(moduleValue, cmdValue, Invoker.createInvoker(bean, method));
                }else {
                    log.error("module: {} cmd: {} 重复添加了", module, cmd);
                }
            }
        }
        return bean;
    }

}
