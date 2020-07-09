package io.baizi.study.bootnetty.common.protobuf;

import com.google.protobuf.GeneratedMessageV3;
import lombok.Data;

/**
 * @author juwencheng
 * @date 2020 2020/7/9 10:53 下午
 */
@Data
public class Result<T extends GeneratedMessageV3> {
    private MessageModule.ResponseType responseType;
    private T content;

    public static <T extends GeneratedMessageV3> Result<T> SUCCESS(T content) {
        Result<T> result = new Result<>();
        result.setResponseType(MessageModule.ResponseType.SUCCESS);
        result.setContent(content);
        return result;
    }

    public static <T extends GeneratedMessageV3> Result<T> FAILURE() {
        Result<T> result = new Result<>();
        result.setResponseType(MessageModule.ResponseType.FAILURE);
        return result;
    }

    public static <T extends GeneratedMessageV3> Result<T> ERROR() {
        Result<T> result = new Result<>();
        result.setResponseType(MessageModule.ResponseType.SYS_ERROR);
        return result;
    }
}
