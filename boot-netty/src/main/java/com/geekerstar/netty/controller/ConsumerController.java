package com.geekerstar.netty.controller;

import com.geekerstar.netty.client.NettyClient;
import com.geekerstar.netty.protocol.protobuf.MessageBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author geekerstar
 * @date 2023/2/27 15:26
 */
@RestController
public class ConsumerController {
    @Autowired
    private NettyClient nettyClient;

    @GetMapping("/send")
    public String send() {
        MessageBase.Message message = new MessageBase.Message()
                .toBuilder().setCmd(MessageBase.Message.CommandType.NORMAL)
                .setContent("hello netty")
                .setRequestId(UUID.randomUUID().toString()).build();
        nettyClient.sendMsg(message);
        return "send ok";
    }
}

