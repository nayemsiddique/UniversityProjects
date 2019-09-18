package com.nayemsiddique.studentgradeserver.service;

import com.nayemsiddique.studentgradeserver.model.Program;
import com.nayemsiddique.studentgradeserver.model.ProgramTitle;
import com.nayemsiddique.studentgradeserver.model.ProgramPrimaryKey;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProgramServiceTest {
    @Autowired
    private ProgramService programService;

    @Test
    public void insert(){
        Program program1= new Program(new ProgramPrimaryKey(1,
                ProgramTitle.cse),2.5,144);

        programService.insert(program1);

       System.out.println( programService.findById(new ProgramPrimaryKey(1,
               ProgramTitle.cse)));


    }
}
