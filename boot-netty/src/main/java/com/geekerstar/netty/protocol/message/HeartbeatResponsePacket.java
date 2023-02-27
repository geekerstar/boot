package com.geekerstar.netty.protocol.message;

import com.geekerstar.netty.protocol.message.command.Command;

/**
 * @author geekerstar
 * @date 2023/2/27 15:21
 */
public class HeartbeatResponsePacket extends Packet {

    @Override
    public Byte getCommand() {
        return Command.HEARTBEAT_RESPONSE;
    }
}
