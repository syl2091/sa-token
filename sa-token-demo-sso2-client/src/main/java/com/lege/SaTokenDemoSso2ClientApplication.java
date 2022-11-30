package com.lege;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SaTokenDemoSso2ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaTokenDemoSso2ClientApplication.class, args);
        System.out.println("\nSa-Token-SSO Client端启动成功");
    }
}
