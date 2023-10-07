package com.example.topupadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class TopupAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(TopupAdminApplication.class, args);
    }

}
