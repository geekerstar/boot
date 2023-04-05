package com.geekerstar.function.module.event.listener.async;

import com.geekerstar.function.constant.CommonConstant;
import com.geekerstar.function.module.event.event.PlaceOrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author geekerstar
 * @date 2023/4/5 14:23
 */
@Slf4j
@Service("AsyncOrderLogListener")
public class OrderLogListener  {

    @EventListener
    @Async(CommonConstant.ASYNC_POOL)
    public void orderLog(PlaceOrderEvent event) {
        log.info("异步事件[订单日志]");
    }
}
