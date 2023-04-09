package com.geekerstar.function.module.design_pattern.ifelse.case2.controller;

import com.geekerstar.function.module.design_pattern.ifelse.case2.entity.Cart;
import com.geekerstar.function.module.design_pattern.ifelse.case2.mapper.DbMapper;
import com.geekerstar.function.module.design_pattern.ifelse.case2.service.AbstractCart;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author geekerstar
 * @date 2023/4/9 12:34
 */
@RequiredArgsConstructor
public class CartController {

    private final DbMapper dbMapper;
    private final ApplicationContext applicationContext;

    @GetMapping("right")
    public Cart right(@RequestParam("userId") int userId) {
        String userCategory = dbMapper.getUserCategory(userId);
        AbstractCart cart = (AbstractCart) applicationContext.getBean(userCategory + "UserCart");
        Map<Long, Integer> items = Maps.newHashMap();
        return cart.process(userId, items);
    }
}
