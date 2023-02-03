package com.geekerstar.function.module.distributed_lock.service;

/**
 * @author geekerstar
 * @date 2023/2/2 17:42
 */
public interface StockService {
    void deductOptimisticLock();

    void deductByRedission();

    void testSemaphore();

}
