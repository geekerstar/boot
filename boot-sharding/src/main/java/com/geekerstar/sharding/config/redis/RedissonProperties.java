package com.geekerstar.sharding.config.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author geekerstar
 * @date 2023/2/3 17:56
 */
@ConfigurationProperties(prefix = "spring.redisson")
@Data
public class RedissonProperties {

    private String serverAddress;

    private String port;

    private String password;

    private Integer database;

    public Integer getDatabase() {
        if (null == database) {
            return 0;
        }
        return database;
    }
}
