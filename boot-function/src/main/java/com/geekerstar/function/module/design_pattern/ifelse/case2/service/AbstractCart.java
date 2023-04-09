//package com.geekerstar.function.module.design_pattern.ifelse.case2.service;
//
//import com.geekerstar.function.module.design_pattern.ifelse.case2.entity.Cart;
//import com.geekerstar.function.module.design_pattern.ifelse.case2.entity.Item;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author geekerstar
// * @date 2023/4/9 12:28
// */
//public abstract class AbstractCart {
//
//    //处理购物车的大量重复逻辑在父类实现
//    public Cart process(long userId, Map<Long, Integer> items) {
//
//        Cart cart = new Cart();
//
//        List<Item> itemList = new ArrayList<>();
//        items.entrySet().stream().forEach(entry -> {
//            Item item = new Item();
//            item.setId(entry.getKey());
////            item.setPrice(dbMapper.getItemPrice(entry.getKey()));
//            item.setQuantity(entry.getValue());
//            itemList.add(item);
//        });
//        cart.setItems(itemList);
//        //让子类处理每一个商品的优惠
//        itemList.stream().forEach(item -> {
//            processCouponPrice(userId, item);
//            processDeliveryPrice(userId, item);
//        });
//        //计算商品总价
//        cart.setTotalItemPrice(cart.getItems().stream().map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal::add));
//        //计算总运费
//        cart.setTotalDeliveryPrice(cart.getItems().stream().map(Item::getDeliveryPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
//        //计算总折扣
//        cart.setTotalDiscount(cart.getItems().stream().map(Item::getCouponPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
//        //计算应付价格
//        cart.setPayPrice(cart.getTotalItemPrice().add(cart.getTotalDeliveryPrice()).subtract(cart.getTotalDiscount()));
//        return cart;
//    }
//
//    //处理商品优惠的逻辑留给子类实现
//    protected abstract void processCouponPrice(long userId, Item item);
//
//    //处理配送费的逻辑留给子类实现
//    protected abstract void processDeliveryPrice(long userId, Item item);
//}
