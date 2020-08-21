package com.kiwihouse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author xin
 * @date 2020/7/13
 */
@EnableScheduling
@EnableSwagger2
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
@EnableCaching
@MapperScan("com.kiwihouse.dao.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
