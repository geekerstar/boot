package com.geekerstar.netty.protocol.message;

import lombok.Data;

/**
 * @author geekerstar
 * @date 2023/2/27 15:20
 */
@Data
public abstract class Packet {
    /**
     * 版本
     */
    private Byte version = 1;

    public abstract Byte getCommand();
}

