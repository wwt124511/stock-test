package com.wwt.stocktest.controller;


import com.wwt.stocktest.domain.Stock;
import com.wwt.stocktest.domain.User;
import com.wwt.stocktest.service.StockService;
import com.wwt.stocktest.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wwt
 * @ClassName StockController.java
 * @Description TODO
 * @createTime 2022-12-10 22:21
 */
@RestController
@RequestMapping("/stock")
@Api(tags = "库存测试接口")
public class StockController {

    @Autowired
    private UserService userService;

    @Autowired
    private StockService stockService;


    @PostMapping("test")
    @ApiOperation(value = "测试1")
    public Object test(User user, String a1, String a2){

        Map<String, String> map = new HashMap();
        map.put("msg", "成功");

        return map;
    }


    @GetMapping("selectAllUser")
    public Object selectAllUser(){
        return userService.selectAll();
    }


    /**
     * @Description 模拟多线程下线程安全问题。库存超卖场景
     * @param
     * @return : java.lang.Object
    */
    @PostMapping("sell")
    @ResponseBody
    public Object sell(){

        Map<String, String> result = new HashMap<>();
        Stock stock = stockService.selectById(1);

        if(stock.getStock() < 1){
            result.put("msg", "库存不足");
        }else{
            stockService.lessStock();
            result.put("msg", "扣减库存成功");
        }
        return result;
    }




    /**
     * @Description 通过version方案解决线程安全问题  但是失败率高
     * @param
     * @return : java.lang.Object
     */
    @PostMapping("sellVersion")
    @ResponseBody
    public Object sellVersion(){

        Map<String, String> result = new HashMap<>();
        Stock stock = stockService.selectById(1);

        if(stock.getStock() < 1){
            result.put("msg", "库存不足");
        }else{
            stockService.lessStockByVersion(stock);
            result.put("msg", "扣减库存成功");
        }
        return result;
    }


    /**
     * @Description 测试增加库存线程安全问题
     * @param
     * @return : java.lang.Object
    */
    @PostMapping("addStock")
    @ResponseBody
    public Object addStock(){

        Map<String, String> result = new HashMap<>();
        stockService.addOneStock();
        result.put("msg", "库存增加成功");
        return result;
    }



}
