package com.guoguo.warehouse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author GC
 */
@SpringBootApplication
@MapperScan("com.guoguo.warehouse.dao")
public class WarehouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarehouseApplication.class, args);
	}
}
