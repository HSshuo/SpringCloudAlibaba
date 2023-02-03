package com.example.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.springcloud.base.CommonResult;
import com.example.springcloud.entities.Payment;
import com.example.springcloud.handler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SHshuo
 * @data 2023/2/3--10:30
 */
@RestController
public class RateLimitController {


    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public CommonResult byResource() {
        return new CommonResult(200,"按资源名称限流测试OK", new Payment(2020L,"serial001"));
    }


    public CommonResult handleException(BlockException exception) {
        return new CommonResult(444,exception.getClass().getCanonicalName() + "服务不可用");
    }

    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl() {
        return new CommonResult(200,"按url限流测试OK", new Payment(2020L,"serial002"));
    }

    /**
     * 自定义通用的限流处理逻辑，
     * blockHandlerClass = CustomerBlockHandler.class
     * blockHandler = handleException2
     * 上述配置：找CustomerBlockHandler类里的handleException2方法进行兜底处理
     */
    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class, blockHandler = "handleException2")
    public CommonResult customerBlockHandler() {
        return new CommonResult(200,"按客户自定义限流处理逻辑");
    }

}
