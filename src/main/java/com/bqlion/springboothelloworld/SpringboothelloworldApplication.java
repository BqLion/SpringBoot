package com.bqlion.springboothelloworld;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bqlion.springboothelloworld.mapper")
public class SpringboothelloworldApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringboothelloworldApplication.class, args);
    }

}
