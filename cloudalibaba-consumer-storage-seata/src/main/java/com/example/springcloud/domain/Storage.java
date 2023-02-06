package com.example.springcloud.domain;

import lombok.Data;

/**
 * @author SHshuo
 * @data 2023/2/4--20:20
 */
@Data
public class Storage {

    private Long id;

    /**
     * 产品id
     */
    private Long productId;

    /**
     * 总库存
     */
    private Integer total;

    /**
     * 已用库存
     */
    private Integer used;

    /**
     * 剩余库存
     */
    private Integer residue;
}
