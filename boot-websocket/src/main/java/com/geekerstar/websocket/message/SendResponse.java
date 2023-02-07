package com.geekerstar.websocket.message;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author geekerstar
 * @date 2023/2/7 12:05
 * 发送消息响应结果的 Message
 */
@Data
@Accessors(chain = true)
public class SendResponse implements Message {

    public static final String TYPE = "SEND_RESPONSE";

    /**
     * 消息编号
     */
    private String msgId;
    /**
     * 响应状态码
     */
    private Integer code;
    /**
     * 响应提示
     */
    private String message;

}

