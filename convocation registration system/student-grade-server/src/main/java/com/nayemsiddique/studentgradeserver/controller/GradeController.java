package com.nayemsiddique.studentgradeserver.controller;

import com.nayemsiddique.studentgradeserver.model.Grade;
import com.nayemsiddique.studentgradeserver.model.ProgramTitle;

import com.nayemsiddique.studentgradeserver.service.GradeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/programs/")
public class GradeController {
    private GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping("/{programTitle}/grades")
    public ResponseEntity<List<Grade>> findAll(@PathVariable String programTitle){
        return ResponseEntity.ok(gradeService.findAll(ProgramTitle.valueOf(programTitle)));

    }
    @GetMapping("/{programTitle}/grades?sid={id}")
    public ResponseEntity<Grade>findByID(@PathVariable String programTitle ,@PathVariable String id){
        return ResponseEntity.ok(gradeService.findByID(id));

    }

    @PostMapping("/{programTitle}/grades")
    public ResponseEntity<Grade>insert(@PathVariable String programTitle,@RequestBody Grade grade){
        Grade insert = gradeService.insert(grade);
        if(insert==null) return ResponseEntity.badRequest().body(insert);
        return ResponseEntity.ok(insert);

    }
    @PutMapping("/{programTitle}/grades/{id}")
    public ResponseEntity<Grade> update(@PathVariable String programTitle ,@PathVariable String id,@RequestBody Grade grade){
        Grade update = gradeService.update(id, grade);
        if (update == null) return ResponseEntity.badRequest().body(update);
        return ResponseEntity.ok(update);

    }
    @DeleteMapping("/{programTitle}/grades/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String programTitle ,@PathVariable String id){
        boolean delete = gradeService.delete(id);
        if (delete) return ResponseEntity.ok(true);
        return ResponseEntity.noContent().build();
    }

}
