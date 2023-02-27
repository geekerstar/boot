package com.geekerstar.netty.protocol.message;

import com.geekerstar.netty.protocol.message.command.Command;
import lombok.Data;


/**
 * @author geekerstar
 * @date 2023/2/27 15:19
 */
@Data
public class HeartbeatRequestPacket extends Packet {

    @Override
    public Byte getCommand() {
        return Command.HEARTBEAT_REQUEST;
    }
}
