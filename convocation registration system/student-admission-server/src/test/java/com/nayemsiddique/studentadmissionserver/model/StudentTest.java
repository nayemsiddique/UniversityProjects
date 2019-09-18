package com.nayemsiddique.studentadmissionserver.model;

import com.nayemsiddique.studentadmissionserver.repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentTest {

    @Test
    public void insert() {
        //    Student student= new Student("2016000000159",new Name("Nayem","Siddique"),Gender.male,ProgramTitle.cse,1,"n@gmail.com",BloodGroup.A_POSITIVE);
        //   studentRepository.save(student);
    }
}
