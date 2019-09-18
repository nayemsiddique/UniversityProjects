package bd.edu.seu.studentserverone.controller;

import bd.edu.seu.studentserverone.model.Student;
import bd.edu.seu.studentserverone.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
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
    public Student findByID(@PathVariable long id) {
        return studentService.findById(id);

    }

    @DeleteMapping("")
    public void delete(Long id) {
        studentService.delete(id);
    }

    @PostMapping("")
    public ResponseEntity<Student> save(@RequestBody Student student) {
        if (studentService.add(student)) {
            return ResponseEntity.ok(student);
        }
        return ResponseEntity.badRequest().body(student);
    }


}
