package com.example.myshortlink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class MyShortLinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyShortLinkApplication.class, args);
    }

}
