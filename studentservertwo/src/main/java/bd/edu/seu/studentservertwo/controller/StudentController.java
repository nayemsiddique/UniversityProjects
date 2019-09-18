package bd.edu.seu.studentservertwo.controller;

import bd.edu.seu.studentservertwo.exception.ResourceAlreadyExistsException;
import bd.edu.seu.studentservertwo.exception.ResourceDoesnotExistsException;
import bd.edu.seu.studentservertwo.model.Student;
import bd.edu.seu.studentservertwo.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/students")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;

    }

    @GetMapping("")
    public ResponseEntity<List<Student>> findAll() {
        return ResponseEntity.ok(studentService.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable long id) {

        return ResponseEntity.ok(studentService.findById(id));

    }

    @PostMapping("")
    public ResponseEntity<Student> save(@RequestBody Student student) {
        try {
            return ResponseEntity.created(null).body(studentService.create(student));
        } catch (ResourceAlreadyExistsException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(student);
        }
    }

    @DeleteMapping("")
    public ResponseEntity<Boolean> delete(@RequestBody Student student) {
//        boolean isDeleted= false;
//        try {
//            isDeleted = studentService.delete(student);
//            return ResponseEntity.ok(isDeleted);
//        } catch (ResourceDoesnotExistsException e) {
//            e.printStackTrace();
//            return ResponseEntity.noContent().build();
//        }
        return ResponseEntity.ok(studentService.delete(student));

    }


}
