package com.wwt.stocktest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wwt.stocktest.mapper")
public class StockTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockTestApplication.class, args);
	}

}
