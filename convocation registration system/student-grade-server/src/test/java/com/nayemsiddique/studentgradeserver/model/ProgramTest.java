package com.nayemsiddique.studentgradeserver.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProgramTest {
    @Test
    public void insert(){
        Program program1= new Program(new ProgramPrimaryKey(1,ProgramTitle.cse),2.5,144);
        System.out.println(program1);
    }
}
