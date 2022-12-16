package com.wwt.stocktest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wwt.stocktest.domain.Stock;
import org.springframework.stereotype.Repository;

@Repository
public interface StockMapper extends BaseMapper<Stock> {
}
