package com.geekerstar.sharding.test1;

import com.geekerstar.sharding.module.shard.entity.Product;
import com.geekerstar.sharding.module.shard.mapper.ProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

/**
 * @author geekerstar
 * @date 2023/2/7 20:01
 * https://mp.weixin.qq.com/s/VE4Uwab5QJHW9HpglI1yIg
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CommonTest {

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void test1(){
        Product product = Product.builder()
                .name("ShardingJdbc")
                .price(15L)
                .originAddress("Geek")
                .shopId(1L)
                .content("测试垂直分库")
                .build();
        productMapper.insertProductBase(product);
    }

    @Test
    public void test2(){
        for (int i = 0; i < 10; i++) {
            Product product = Product.builder()
                    .name("ShardingJdbc")
                    .price(159L)
                    .originAddress("Geek")
                    .shopId(1L)
                    .build();
            productMapper.insertProductBase(product);
        }
    }

    @Test
    public void test3(){
        for (int i = 0; i < 10; i++) {
            Product product = Product.builder()
                    .name("ShardingJdbc")
                    .price(159L)
                    .originAddress("Geek")
                    .shopId((long) new Random().nextInt(100))
                    .build();
            productMapper.insertProductBase(product);
        }
    }

    @Test
    public void test4(){
        for (int i = 0; i < 10; i++) {

            Product product = Product.builder()
                    .name("ShardingJdbc")
                    .price(159L)
                    .originAddress("Geek")
                    .shopId((long)(new Random().nextInt(100)+1))
                    .build();
            productMapper.insertProductBase(product);
            productMapper.insertProductDescribe(product.getProductId(),"内容",product.getShopId());
        }
    }
}
