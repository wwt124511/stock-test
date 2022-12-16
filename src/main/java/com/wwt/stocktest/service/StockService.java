package com.wwt.stocktest.service;

import com.wwt.stocktest.domain.Stock;

public interface StockService {

    Stock selectById(int id);

    void lessStock();

    void lessStockByVersion(Stock stock);

    void addOneStock();
}
