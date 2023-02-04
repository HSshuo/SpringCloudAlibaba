package com.example.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.springcloud.base.CommonResult;
import com.example.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author SHshuo
 * @data 2023/2/3--10:47
 */
@RestController
@Slf4j
public class CircleBreakerController {

    public static final String SERVICE_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;


    /**
     * id = 4, 抛出运行时异常，会走 fallback 配置的方法，只负责业务异常
     * blockHandler 负责在 sentinel 里面配置的降级限流
     * 同时配置，blockHandler > fallback
     * @param id
     * @return
     */
    @RequestMapping("/consumer/fallback/{id}")
    @SentinelResource(value = "fallback", blockHandler = "blockHandler", fallback = "blockFallBack")
    public CommonResult<Payment> fallback(@PathVariable Long id) {
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class, id);

        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常....");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException,该ID没有对应记录,空指针异常");
        }

        return result;
    }

    /**
     * 业务类异常
     * @param id
     * @param throwable
     * @return
     */
    public CommonResult blockFallBack(@PathVariable Long id, Throwable throwable) {
        return new CommonResult(4444, "运行时异常，fallback跳转，blockException: " + throwable.getMessage(), new Payment(id, "null"));
    }


    /**
     * sentinel控制台配置异常
     * @param id
     * @param blockException
     * @return
     */
    public CommonResult blockHandler(@PathVariable Long id, BlockException blockException) {
        return new CommonResult<>(4445, "sentinel里面配置异常，blockHandler-sentinel限流, 无此流水: blockException  " + blockException.getMessage(), new Payment(id, "null"));
    }
}
