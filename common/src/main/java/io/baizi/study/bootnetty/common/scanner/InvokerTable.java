package io.baizi.study.bootnetty.common.scanner;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author juwencheng
 * @date 2020 2020/7/9 10:26 下午
 */
@Data
public class InvokerTable {
    private static ConcurrentHashMap<String, Map<String, Invoker>> table = new ConcurrentHashMap<>();


    /**
     * 添加invoker
     * @param module
     * @param cmd
     * @param invoker
     */
    public static void addInvoker(String module, String cmd, Invoker invoker) {
        Map<String, Invoker> methodTable = InvokerTable.table.get(module);
        if (methodTable == null) {
            methodTable = new HashMap<>();
            InvokerTable.table.put(module, methodTable);
        }
        methodTable.put(cmd, invoker);
    }

    /**
     * 获取invoker
     * @param module
     * @param cmd
     * @return
     */
    public static Invoker getInvoker(String module, String cmd) {
        Map<String, Invoker> methodTable = InvokerTable.table.get(module);
        if (methodTable == null) {
            return null;
        }
        return methodTable.get(cmd);
    }
}
