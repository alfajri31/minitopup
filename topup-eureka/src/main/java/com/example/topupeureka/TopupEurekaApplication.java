package com.example.topupeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TopupEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TopupEurekaApplication.class, args);
    }

}
