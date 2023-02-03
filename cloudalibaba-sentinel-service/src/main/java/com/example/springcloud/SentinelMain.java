package com.example.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author SHshuo
 * @data 2023/1/26--15:37
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SentinelMain {
    public static void main(String[] args) {
        SpringApplication.run(SentinelMain.class, args);
    }
}
