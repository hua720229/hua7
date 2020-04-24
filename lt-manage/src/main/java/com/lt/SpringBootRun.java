package com.lt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//启动时不加载数据源(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
@MapperScan("com.lt.mapper")//放开数据源
public class SpringBootRun {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootRun.class,args);
	}
}
