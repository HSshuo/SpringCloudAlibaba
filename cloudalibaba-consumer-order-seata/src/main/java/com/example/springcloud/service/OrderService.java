package com.example.springcloud.service;

import com.example.springcloud.domain.Order;

/**
 * @author SHshuo
 * @data 2023/2/4--12:54
 */
public interface OrderService {

    /**
     * 创建订单
     */
    void create(Order order);
}
