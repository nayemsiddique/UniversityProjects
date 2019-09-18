package com.nayemsiddique.studentadmissionserver.controller;

import com.nayemsiddique.studentadmissionserver.model.Student;
import com.nayemsiddique.studentadmissionserver.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/students")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("")
    public ResponseEntity<List<Student>> findAll() {
        List<Student> all = studentService.findAll();
        if (all.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(all);
    }

    @GetMapping("?program={programTitle}")
    public ResponseEntity<List<Student>> findAllByProgram(@PathVariable String programTitle) {
        List<Student> allByProgramTitle = studentService.findAllByProgramTitle(programTitle);
        if (allByProgramTitle.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(allByProgramTitle);

    }

    //    @GetMapping("?program={programTitle}&id={id}")
//    public ResponseEntity<Student> findByProgramTitleAndID(@PathVariable String programTitle,@PathVariable String id){
//        Student byProgramTitleAndID = studentService.findByProgramTitleAndID(programTitle, id);
//        if (byProgramTitleAndID ==null) return ResponseEntity.noContent().build();
//        return ResponseEntity.ok(byProgramTitleAndID);
//    }
    @GetMapping("?id={id}")
    public ResponseEntity<Student> findByID(@PathVariable String id) {
        Student byId = studentService.findById(id);
        if (byId == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(byId);
    }

    @PostMapping("")
    public ResponseEntity<Student> insert(@RequestBody Student student) {
        Student insert = studentService.insert(student);
        if (insert == null) return ResponseEntity.badRequest().body(student);
        return ResponseEntity.ok(insert);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable String id, @RequestBody Student student) {
        Student update = studentService.update(id, student);
        if (update == null) return ResponseEntity.badRequest().body(student);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        Boolean delete = studentService.delete(id);
        if (delete) return ResponseEntity.ok(true);
        return ResponseEntity.badRequest().body(false);

    }

}
