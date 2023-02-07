package com.geekerstar.websocket.message;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author geekerstar
 * @date 2023/2/7 12:05
 * 发送给所有人的群聊消息的 Message
 */
@Data
@Accessors(chain = true)
public class SendToAllRequest implements Message {

    public static final String TYPE = "SEND_TO_ALL_REQUEST";

    /**
     * 消息编号
     */
    private String msgId;
    /**
     * 内容
     */
    private String content;

}

