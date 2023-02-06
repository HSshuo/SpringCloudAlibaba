package com.example.springcloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author SHshuo
 * @data 2023/2/4--12:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    private Integer code;
    private String  message;
    private T data;

    public CommonResult(Integer code, String message) {
        this(code,message,null);
    }
}
