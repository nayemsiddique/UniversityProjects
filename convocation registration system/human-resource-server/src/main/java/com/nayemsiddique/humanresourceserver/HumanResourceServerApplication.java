package com.nayemsiddique.humanresourceserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class HumanResourceServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HumanResourceServerApplication.class, args);
    }

}
