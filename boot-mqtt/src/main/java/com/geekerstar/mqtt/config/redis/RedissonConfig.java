//package com.geekerstar.mqtt.config.redis;
//
//import cn.hutool.core.util.StrUtil;
//import org.redisson.Redisson;
//import org.redisson.api.RedissonClient;
//import org.redisson.config.Config;
//import org.redisson.config.SingleServerConfig;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author geekerstar
// * @date 2023/2/3 17:54
// */
//@EnableConfigurationProperties(RedissonProperties.class)
//@Configuration
//public class RedissonConfig {
//
//    @Bean
//    public RedissonClient redissonClient(RedissonProperties properties) {
//        if (properties.getServerAddress() == null) {
//            return null;
//        }
//        Config config = new Config();
//        config.useSingleServer().setConnectionMinimumIdleSize(2);
//        SingleServerConfig singleServerConfig = config.useSingleServer();
//        singleServerConfig.setAddress("redis://" + properties.getServerAddress() + ":" + properties.getPort());
//        singleServerConfig.setDatabase(properties.getDatabase());
//        String password = properties.getPassword();
//        if (StrUtil.isNotBlank(password)) {
//            singleServerConfig.setPassword(password);
//        }
//        return Redisson.create(config);
//
////        Config config = new Config();
////        for (String node : properties.getServerAddress().split(",")) {
////            config.useClusterServers().addNodeAddress("redis://" + node);
////        }
////        config.useClusterServers().setMasterConnectionPoolSize(200);
////        config.useClusterServers().setTimeout(10000);
////        config.useClusterServers().setSlaveConnectionMinimumIdleSize(200);
////        config.useClusterServers().setMasterConnectionMinimumIdleSize(200);
////        config.useClusterServers().setSlaveConnectionPoolSize(200);
////        config.useClusterServers().setScanInterval(3000);
////        String password = properties.getPassword();
////        config.useClusterServers().setPassword(password);
////        return Redisson.create(config);
//    }
//
//}
//
