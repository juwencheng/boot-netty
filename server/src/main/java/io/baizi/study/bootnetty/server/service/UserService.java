package io.baizi.study.bootnetty.server.service;

import io.baizi.study.bootnetty.common.annotation.Cmd;
import io.baizi.study.bootnetty.common.annotation.Module;
import org.springframework.stereotype.Service;

/**
 * @author juwencheng
 * @date 2020 2020/7/9 10:10 下午
 */
@Service
@Module(module = "user")
public class UserService {

    @Cmd(cmd = "save")
    public Object save() {
        return null;
    }

    @Cmd(cmd = "update")
    public Object update() {
        return null;
    }
}
