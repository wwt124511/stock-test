package com.wwt.stocktest.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.wwt.stocktest.domain.Stock;
import com.wwt.stocktest.domain.StockRecord;
import com.wwt.stocktest.mapper.StockMapper;
import com.wwt.stocktest.mapper.StockRecordMapper;
import com.wwt.stocktest.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * @author wwt
 * @ClassName StockServiceImpl.java
 * @Description TODO
 * @createTime 2022-12-11 00:39
 */
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private StockRecordMapper stockRecordMapper;


    @Override
    public Stock selectById(int id) {
        return stockMapper.selectById(id);
    }

    /**
     * @Description 模拟多线程下线程安全问题。库存超卖场景
     * @param
     * @return : void
    */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void lessStock() {

        //1. 库存减1
        UpdateWrapper<Stock> updateWrapper = new UpdateWrapper<>();
        updateWrapper.setSql("stock = stock - 1");
        updateWrapper.eq("id",1);
        int row = stockMapper.update(null, updateWrapper);

        if(row == 1){
            //2.增加一条库存购买记录
            StockRecord record = new StockRecord();
            record.setUserId(1003);
            record.setStockId(1);
            stockRecordMapper.insert(record);
        }


    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void lessStockByVersion(Stock stock) {

        //1. 库存减1
        UpdateWrapper<Stock> updateWrapper = new UpdateWrapper<>();
        updateWrapper.setSql("stock = stock - 1");
        updateWrapper.eq("id",1);
        //updateWrapper.eq("stock", stock.getStock()); 这样写会有少卖情况，失败率高
        updateWrapper.gt("stock", 0);
        int row = stockMapper.update(null, updateWrapper);

        if(row == 1){
            //2.增加一条库存购买记录
            StockRecord record = new StockRecord();
            record.setUserId(1003);
            record.setStockId(1);
            stockRecordMapper.insert(record);
        }

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addOneStock() {
        UpdateWrapper<Stock> updateWrapper = new UpdateWrapper<>();
        updateWrapper.setSql("stock = stock + 1");
        updateWrapper.eq("id",1);
        stockMapper.update(null, updateWrapper);
    }
}
