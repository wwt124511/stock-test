package com.wwt.stocktest.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wwt
 * @ClassName StockRecord.java
 * @Description TODO
 * @createTime 2022-12-11 00:51
 */
@Data
@TableName("stock_record")
public class StockRecord implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer recordId;

    private Integer stockId;

    private Integer userId;

    private Date createTime;
}
