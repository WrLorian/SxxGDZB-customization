package com.kiwihouse.web;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class Startup implements CommandLineRunner{
@Override
public void run(String... strings) throws Exception {
    System.out.println("##################初始化admin权限##################");
    }
}
