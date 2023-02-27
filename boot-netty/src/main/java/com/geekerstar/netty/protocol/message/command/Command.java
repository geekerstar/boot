package com.geekerstar.netty.protocol.message.command;

/**
 * @author geekerstar
 * @date 2023/2/27 15:21
 */
public interface Command {
    Byte HEARTBEAT_REQUEST = 1;
    Byte HEARTBEAT_RESPONSE = 2;

}
