package com.geekerstar.mqtt.config.apm;

import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;

import java.util.Map;

/**
 * @author geekerstar
 * @date 2023/2/3 16:18
 */
public class MdcTaskDecorator implements TaskDecorator {
    /**
     * 使异步线程池获得主线程的上下文
     *
     * @param runnable
     * @return
     */
    @Override
    public Runnable decorate(Runnable runnable) {
        Map<String, String> map = MDC.getCopyOfContextMap();
        return () -> {
            try {
                MDC.setContextMap(map);
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }
}
