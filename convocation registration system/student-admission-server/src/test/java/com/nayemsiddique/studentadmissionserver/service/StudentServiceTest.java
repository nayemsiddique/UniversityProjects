package com.nayemsiddique.studentadmissionserver.service;

import com.nayemsiddique.studentadmissionserver.model.*;
import com.nayemsiddique.studentadmissionserver.repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest {
    @Autowired
    private StudentService studentService;

    @Test
    public void findByProgramID() {
        //   List<Student> cse = studentService.findAllByProgramTitle("cse");
        Student student = new Student("2016000000159", LocalDate.of(1996, Month.DECEMBER, 21), new Name("Nayem", "Siddique"),
                Gender.male, ProgramTitle.cse, 1, "n@gmail.com", BloodGroup.A_POSITIVE);
        studentService.insert(student);
        //Student byProgramTitleAndStudentId = studentRepository.findByProgramTitleAndStudentId(ProgramTitle.cse, "2016000000159");
        System.out.println(studentService.insert(student));
    }

}
