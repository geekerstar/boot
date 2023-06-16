package com.geekerstar.starrocks.config.apm;

/**
 * @author geekerstar
 * @date 2023/2/3 16:18
 *
 * 使用ThreadLocal传递上下文信息
 */
public class MetricHolder {
    public static ThreadLocal<String> metric = new ThreadLocal<>();
}
