package com.geekerstar.websocket.message;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author geekerstar
 * @date 2023/2/7 12:04
 * 用户认证响应
 */
@Data
@Accessors(chain = true)
public class AuthResponse implements Message {

    public static final String TYPE = "AUTH_RESPONSE";

    /**
     * 响应状态码
     */
    private Integer code;
    /**
     * 响应提示
     */
    private String message;

}

