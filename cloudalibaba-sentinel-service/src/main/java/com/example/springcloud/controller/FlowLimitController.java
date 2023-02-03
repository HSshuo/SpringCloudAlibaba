package com.example.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SHshuo
 * @data 2023/1/26--15:37
 */
@RestController
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        return "------testB";
    }

    /**
     * sentinel系统默认的提示：Blocked by Sentinel (flow limiting)
     * 自定义限流异常，testHotKey为资源名；blockHandler为fallback方法
     *
     * @SentinelResource 处理的是 sentinel 控制台配置的违规情况，有 blockHandler 方法配置的兜底处理
     * 对于 RuntimeException 异常，例如 int age = 10 / 0，会走异常，@SentinelResource 注解不管
     * @param p1
     * @param p2
     * @return
     */
    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "dealHandler_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1, @RequestParam(value = "p2",required = false) String p2){
        return "------testHotKey";
    }

    public String dealHandler_testHotKey(String p1, String p2, BlockException exception) {
        return "-----dealHandler_testHotKey";
    }


}
