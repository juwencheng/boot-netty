package io.baizi.study.bootnetty.server.service;

import com.google.protobuf.InvalidProtocolBufferException;
import io.baizi.study.bootnetty.common.annotation.Cmd;
import io.baizi.study.bootnetty.common.annotation.Module;
import io.baizi.study.bootnetty.common.protobuf.Result;
import io.baizi.study.bootnetty.common.protobuf.UserModule;
import org.springframework.stereotype.Service;

/**
 * @author juwencheng
 * @date 2020 2020/7/9 10:10 下午
 */
@Service
@Module(module = "user")
public class UserService {

    @Cmd(cmd = "save")
    public Result<?> save(byte[] params) {
        try {
            UserModule.User user = UserModule.User.parseFrom(params);
            System.out.println("保存 " + user.getUserId());
            return Result.SUCCESS(user);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            return Result.ERROR();
        }
    }

    @Cmd(cmd = "update")
    public Result<?> update(byte[] params) {
        try {
            UserModule.User user = UserModule.User.parseFrom(params);
            System.out.println("更新 " + user.getUserId());
            return Result.SUCCESS(user);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            return Result.ERROR();
        }
    }
}
