package com.example.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author SHshuo
 * @data 2023/2/3--20:04
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SentinelOpenFeignOrderMain {
    public static void main(String[] args) {
        SpringApplication.run(SentinelOpenFeignOrderMain.class, args);
    }
}
