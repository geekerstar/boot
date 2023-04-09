//package com.geekerstar.function.module.design_pattern.ifelse.case2.service;
//
//import com.geekerstar.function.module.design_pattern.ifelse.case2.entity.Item;
//import com.geekerstar.function.module.design_pattern.ifelse.case2.mapper.DbMapper;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//
///**
// * @author geekerstar
// * @date 2023/4/9 12:29
// */
//@Service(value = "VipUserCart")
//@RequiredArgsConstructor
//public class VipUserCart extends NormalUserCart {
//
//    private final DbMapper dbMapper;
//
//    @Override
//    protected void processCouponPrice(long userId, Item item) {
//        if (item.getQuantity() > 2) {
//            item.setCouponPrice(item.getPrice()
//                    .multiply(BigDecimal.valueOf(100 - dbMapper.getUserCouponPercent(userId)).divide(new BigDecimal("100")))
//                    .multiply(BigDecimal.valueOf(item.getQuantity() - 2)));
//        } else {
//            item.setCouponPrice(BigDecimal.ZERO);
//        }
//    }
//}
