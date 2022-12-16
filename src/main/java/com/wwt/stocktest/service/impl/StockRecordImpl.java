package com.wwt.stocktest.service.impl;

import com.wwt.stocktest.domain.StockRecord;
import com.wwt.stocktest.mapper.StockRecordMapper;
import com.wwt.stocktest.service.StockRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wwt
 * @ClassName StockRecordImpl.java
 * @Description TODO
 * @createTime 2022-12-11 01:06
 */
@Service
public class StockRecordImpl implements StockRecordService {

    @Autowired
    private StockRecordMapper stockRecordMapper;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer insert(StockRecord stockRecord) {
        return stockRecordMapper.insert(stockRecord);
    }
}
