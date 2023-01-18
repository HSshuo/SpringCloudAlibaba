package com.example.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author SHshuo
 * @data 2023/1/18--21:09
 * Nacos Server 使用外部运行
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentSlaveMain {
    public static void main(String[] args) {
        SpringApplication.run(PaymentSlaveMain.class, args);
    }
}
