/*
 * Copyright (c) emfuture, 2023, Emfuture Technology Co.,Ltd. All rights reserved.
 */
package com.geekerstar.common.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * @author geekerstar
 * @date 2023/7/3 13:48
 */
public class ByteUtil {

    public static byte[] prependLength(byte[] data) {
        int length = data.length;
        ByteBuffer buffer = ByteBuffer.allocate(4 + length);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.putInt(length);
        buffer.put(data);
        return buffer.array();
    }

    public static int extractLength(byte[] data) {
        ByteBuffer buffer = ByteBuffer.wrap(data);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        return buffer.getInt();
    }

    public static byte[] extractData(byte[] data) {
        return Arrays.copyOfRange(data, 4, data.length);
    }
}
