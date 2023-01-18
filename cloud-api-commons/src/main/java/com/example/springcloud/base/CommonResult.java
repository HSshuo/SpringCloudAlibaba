package com.example.springcloud.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author SHshuo
 * @data 2022/12/3--13:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    private Integer code;
    private String  message;
    private T data;

    public CommonResult (Integer code, String message) {
        this(code, message,null);
    }
}