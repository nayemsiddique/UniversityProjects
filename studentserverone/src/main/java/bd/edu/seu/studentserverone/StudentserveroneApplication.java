package bd.edu.seu.studentserverone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "bd.edu.seu.studentserverone", "bd.edu.seu.studentserverone.controller", "bd.edu.seu.studentserverone.model",
        "bd.edu.seu.studentserverone.service", "bd.edu.seu.studentserverone.repository"})
public class StudentserveroneApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentserveroneApplication.class, args);
    }

}
