package com.nayemsiddique.studentadmissionserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class StudentAdmissionServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentAdmissionServerApplication.class, args);
    }

}
