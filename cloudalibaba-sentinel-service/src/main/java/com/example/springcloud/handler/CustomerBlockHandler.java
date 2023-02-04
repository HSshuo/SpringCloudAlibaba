package com.example.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.springcloud.base.CommonResult;

/**
 * @author SHshuo
 * @data 2023/2/3--10:32
 * 自定义通用的限流处理逻辑
 */
public class CustomerBlockHandler {

    public static CommonResult handleException2(BlockException exception){
        return new CommonResult(2022,"自定义的限流处理信息......handleException2");
    }

    public static CommonResult handleException(BlockException exception){
        return new CommonResult(2022,"自定义的限流处理信息......handleException");
    }
}
