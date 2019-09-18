package com.nayemsiddique.studentgradeserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class StudentGradeServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentGradeServerApplication.class, args);
    }

}
