package com.geekerstar.websocket.handler;

import com.geekerstar.websocket.message.Message;
import org.springframework.web.socket.WebSocketSession;

/**
 * @author geekerstar
 * @date 2023/2/7 12:14
 * 消息处理器接口
 */
public interface MessageHandler<T extends Message> {

    /**
     * 执行处理消息
     *
     * @param session 会话
     * @param message 消息
     */
    void execute(WebSocketSession session, T message);

    /**
     * @return 消息类型，即每个 Message 实现类上的 TYPE 静态字段
     */
    String getType();

}
