package io.baizi.study.bootnetty.common.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageV3;

/**
 * @author juwencheng
 * @date 2020 2020/7/9 11:02 下午
 */
public class MessageBuilder {
    private static final int CRCCODE = 0xff12232;

    public static MessageModule.Message buildResponseMessage(String module, String cmd,
                                                             MessageModule.ResponseType responseType,
                                                             GeneratedMessageV3 data) {
        return MessageModule.Message.newBuilder()
                .setCrcCode(MessageBuilder.CRCCODE)
                .setMessageType(MessageModule.MessageType.RESPONSE)
                .setModule(module)
                .setCmd(cmd)
                .setResponseType(responseType)
                .setBody(ByteString.copyFrom(data.toByteArray()))
                .build();
    }

    public static MessageModule.Message buildRequestMessage(String module, String cmd, GeneratedMessageV3 data) {
        return MessageModule.Message.newBuilder()
                .setCrcCode(MessageBuilder.CRCCODE)
                .setMessageType(MessageModule.MessageType.REQUEST)
                .setModule(module)
                .setCmd(cmd)
                .setResponseType(MessageModule.ResponseType.SUCCESS)
                .setBody(ByteString.copyFrom(data.toByteArray()))
                .build();
    }
}
