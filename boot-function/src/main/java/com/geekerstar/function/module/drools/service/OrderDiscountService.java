package com.geekerstar.function.module.drools.service;

import com.geekerstar.function.module.drools.domain.OrderDiscount;
import com.geekerstar.function.module.drools.domain.OrderRequest;
import org.springframework.stereotype.Service;

/**
 * @author geekerstar
 * @date 2023/4/26 20:57
 */
@Service
public class OrderDiscountService {

//    @Autowired
//    private KieContainer kieContainer;

    public OrderDiscount getDiscount(OrderRequest orderRequest) {
        OrderDiscount orderDiscount = new OrderDiscount();
//        // 开启会话
//        KieSession kieSession = kieContainer.newKieSession();
//        // 设置折扣对象
//        kieSession.setGlobal("orderDiscount", orderDiscount);
//        // 设置订单对象
//        kieSession.insert(orderRequest);
//        // 触发规则
//        kieSession.fireAllRules();
//        // 中止会话
//        kieSession.dispose();
        return orderDiscount;
    }
}
