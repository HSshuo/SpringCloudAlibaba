package com.example.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.springcloud.base.CommonResult;

/**
 * @author SHshuo
 * @data 2023/2/3--10:32
 */
public class CustomerBlockHandler {

    public static CommonResult handleException(BlockException exception){
        return new CommonResult(2020,"自定义的限流处理信息......CustomerBlockHandler");
    }
}
