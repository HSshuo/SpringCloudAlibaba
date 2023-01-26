package com.example.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SHshuo
 * @data 2023/1/20--11:47
 */
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Integer id) {
        System.out.println("serverPort: " + serverPort + "id: " + id);
        return "nacos registry, serverPort: " + serverPort + "\t id" + id;
    }
}
