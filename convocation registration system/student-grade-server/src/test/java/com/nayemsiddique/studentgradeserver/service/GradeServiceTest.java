package com.nayemsiddique.studentgradeserver.service;

import com.nayemsiddique.studentgradeserver.model.Grade;
import com.nayemsiddique.studentgradeserver.model.ProgramTitle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GradeServiceTest {
    @Autowired
    private GradeService gradeService;
    @Test
    public void insert(){
        Grade grade1= new Grade(ProgramTitle.cse,"20199911","cse3011",3.5);
        gradeService.insert(grade1);
        System.out.println(gradeService.findAll(ProgramTitle.cse));
    }

}
