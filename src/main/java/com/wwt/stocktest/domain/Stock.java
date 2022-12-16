package com.wwt.stocktest.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wwt
 * @ClassName Stock.java
 * @Description TODO
 * @createTime 2022-12-11 00:38
 */
@Data
public class Stock implements Serializable {

    private Integer id;
    private Integer stock;
}
