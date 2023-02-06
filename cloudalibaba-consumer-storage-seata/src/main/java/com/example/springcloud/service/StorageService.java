package com.example.springcloud.service;

/**
 * @author SHshuo
 * @data 2023/2/4--20:24
 */
public interface StorageService {
    /**
     * 扣减库存
     */
    void decrease(Long productId, Integer count);
}
