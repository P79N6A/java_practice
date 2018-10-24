package com.jiao.byagent;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jiao.byagent.dao")
public class ByagentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ByagentApplication.class, args);
	}
}
