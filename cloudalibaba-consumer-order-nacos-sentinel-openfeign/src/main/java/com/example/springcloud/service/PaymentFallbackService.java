package com.example.springcloud.service;

import com.example.springcloud.base.CommonResult;
import com.example.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * @author SHshuo
 * @data 2023/2/3--20:50
 * 参考：https://blog.nowcoder.net/n/6c9a075d86054fa1b2e42240254cb385
 */
@Component
public class PaymentFallbackService implements PaymentService {

    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444,"服务降级返回,没有该流水信息", new Payment(id, "errorSerial......"));
    }
}
