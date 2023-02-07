package com.geekerstar.websocket.message;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author geekerstar
 * @date 2023/2/7 12:04
 * <p>
 * 用户认证请求
 */
@Data
@Accessors(chain = true)
public class AuthRequest implements Message {

    public static final String TYPE = "AUTH_REQUEST";

    /**
     * 认证 Token
     */
    private String accessToken;
}
