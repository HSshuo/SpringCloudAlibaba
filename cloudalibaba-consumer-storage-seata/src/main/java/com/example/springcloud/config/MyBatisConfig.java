package com.example.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author SHshuo
 * @data 2023/2/4--20:26
 */
@Configuration
@MapperScan({"com.example.springcloud.dao"})
public class MyBatisConfig {
}
