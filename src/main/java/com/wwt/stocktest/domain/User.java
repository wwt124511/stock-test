package com.wwt.stocktest.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wwt
 * @ClassName User.java
 * @Description TODO
 * @createTime 2022-12-11 00:05
 */
@Data
public class User implements Serializable {

    private Long id;

    private String name;

    private Integer age;

    private String email;

}
