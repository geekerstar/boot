package com.geekerstar.sharding.module.split.algorithm;

import lombok.Data;
import org.apache.shardingsphere.spi.masterslave.MasterSlaveLoadBalanceAlgorithm;

import java.util.List;
import java.util.Properties;

/**
 * @author geekerstar
 * @date 2023/2/7 19:20
 * 从节点的负载均衡算法-强制查主库
 */
@Data
public class HintMasterBalanceAlgorithm implements MasterSlaveLoadBalanceAlgorithm {

    private Properties properties = new Properties();

    public static final String ALGORITHM_TYPE = "HINT-MASTER";

    /**
     * 获取数据源名称
     *
     * @param name                 逻辑数据源，比如配置中定义的m1、m2
     * @param masterDataSourceName 主数据源
     * @param slaveDataSourceNames 从节点集合
     */
    @Override
    public String getDataSource(String name, String masterDataSourceName, List<String> slaveDataSourceNames) {
        return masterDataSourceName;
    }

    @Override
    public String getType() {
        return ALGORITHM_TYPE;
    }

    @Override
    public Properties getProperties() {
        return null;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
