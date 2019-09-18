package com.nayemsiddique.studentgradeserver.controller;

import com.nayemsiddique.studentgradeserver.model.Program;
import com.nayemsiddique.studentgradeserver.service.ProgramService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nayemsiddique.studentgradeserver.model.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/programs")
public class ProgramController {
    private ProgramService programService;

    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping("")
    public ResponseEntity<List<Program>> findAll(){
        return ResponseEntity.ok(programService.findAll());
    }

    @GetMapping("?program={programTitle}&semester={semesterId}")
    public ResponseEntity<Program> findById(@PathVariable String programTitle,@PathVariable int semesterId){
        ProgramPrimaryKey programPrimaryKey= new ProgramPrimaryKey(semesterId,
                ProgramTitle.valueOf(programTitle.toLowerCase()));

        return  ResponseEntity.ok(programService.findById(programPrimaryKey));
       // return ResponseEntity.ok(programService.findById(programPrimaryKey));
    }
    @PostMapping("")
    public ResponseEntity<Program> insert(@RequestBody Program program){
        Program insert = programService.insert(program);
        if (insert == null)
            return ResponseEntity.badRequest().body(program);
        return ResponseEntity.ok(insert);
    }
    @DeleteMapping("")
    public ResponseEntity<Boolean> delete(@RequestBody Program program){
        boolean delete = programService.delete(program);
        if (delete) return ResponseEntity.ok(delete);

        return ResponseEntity.badRequest().body(delete);
    }
    @PutMapping("/{programTitle}/{semesterId}")
    public ResponseEntity<Program> Update(@PathVariable String programTitle,@PathVariable int semesterId,
                                          @RequestBody Program program){

        ProgramPrimaryKey programPrimaryKey= new ProgramPrimaryKey(semesterId,
                ProgramTitle.valueOf(programTitle.toLowerCase()));
        Program update = programService.update(programPrimaryKey, program);
        if (update == null){
            return ResponseEntity.badRequest().body(program);
        }
        return ResponseEntity.ok(update);

    }
}
