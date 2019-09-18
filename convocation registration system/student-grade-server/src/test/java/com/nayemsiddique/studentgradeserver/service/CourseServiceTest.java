package com.nayemsiddique.studentgradeserver.service;

import com.nayemsiddique.studentgradeserver.model.Course;
import com.nayemsiddique.studentgradeserver.model.Program;
import com.nayemsiddique.studentgradeserver.model.ProgramPrimaryKey;
import com.nayemsiddique.studentgradeserver.model.ProgramTitle;
import com.nayemsiddique.studentgradeserver.repository.CourseRepository;
import com.nayemsiddique.studentgradeserver.repository.ProgramRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseServiceTest {
    @Autowired
    private CourseService courseService;

    @Test
    public void findAll(){
//       Program program1= new Program(new ProgramPrimaryKey(1,ProgramTitle.cse),2.5,144);
//       Course course= new Course(program1,"cse3011","c",3.0);
//       courseService.insert(course);


        System.out.println(courseService.findAll(ProgramTitle.cse));



        //courseService.findAll(ProgramTitle.cse);
    }

}
