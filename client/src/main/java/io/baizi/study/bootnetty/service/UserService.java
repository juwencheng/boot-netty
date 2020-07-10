package io.baizi.study.bootnetty.service;

import io.baizi.study.bootnetty.common.annotation.Cmd;
import io.baizi.study.bootnetty.common.annotation.Module;
import org.springframework.stereotype.Service;

/**
 * @author juwencheng
 * @date 2020 2020/7/10 10:38 上午
 */
@Service
@Module(module = "user-return")
public class UserService {

    @Cmd(cmd = "save-return")
    public void save() {
        System.out.println("保存成功，收到服务端返回");
    }
    @Cmd(cmd = "update-return")
    public void update() {
        System.out.println("更新成功，收到服务端返回");
    }
}
