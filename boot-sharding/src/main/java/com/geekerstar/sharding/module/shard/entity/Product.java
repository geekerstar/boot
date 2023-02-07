package com.geekerstar.sharding.module.shard.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author geekerstar
 * @date 2023/2/7 20:03
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long productId;
    private String name;
    private Long price;
    private Long shopId;
    private String originAddress;
    private String content;
}
