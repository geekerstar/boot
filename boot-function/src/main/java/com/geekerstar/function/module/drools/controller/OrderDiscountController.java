package com.geekerstar.function.module.drools.controller;

import com.geekerstar.function.module.drools.domain.OrderDiscount;
import com.geekerstar.function.module.drools.domain.OrderRequest;
import com.geekerstar.function.module.drools.service.OrderDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author geekerstar
 * @date 2023/4/26 20:59
 * https://mp.weixin.qq.com/s/ihkd8wkIVvcLpWLyAb4Ksw
 */
@RestController
public class OrderDiscountController {

    @Autowired
    private OrderDiscountService orderDiscountService;

    @PostMapping("/get-discount")
    public ResponseEntity<OrderDiscount> getDiscount(@RequestBody OrderRequest orderRequest) {
        OrderDiscount discount = orderDiscountService.getDiscount(orderRequest);
        return new ResponseEntity<>(discount, HttpStatus.OK);
    }
}
