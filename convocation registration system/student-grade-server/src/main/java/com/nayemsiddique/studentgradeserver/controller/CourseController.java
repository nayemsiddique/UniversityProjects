package com.nayemsiddique.studentgradeserver.controller;

import com.nayemsiddique.studentgradeserver.model.Course;
import com.nayemsiddique.studentgradeserver.model.ProgramTitle;
import com.nayemsiddique.studentgradeserver.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/programs/")
public class CourseController {
    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/{programTitle}/courses")
    public ResponseEntity<List<Course>>findAll(@PathVariable String programTitle){
        return ResponseEntity.ok(courseService.findAll(ProgramTitle.valueOf(programTitle)));
    }
    @GetMapping("/{programTitle}/courses?cid={id}")
    public ResponseEntity<Course>findbyID(@PathVariable String programTitle,@PathVariable String id){
        return ResponseEntity.ok(courseService.findById(id));
    }
    @PostMapping("/{programTitle}/courses")
    public  ResponseEntity<Course> insert(@RequestBody Course course,@PathVariable String programTitle){
        return ResponseEntity.ok(courseService.insert(course));

    }
    @DeleteMapping("/{programTitle}/courses/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String programTitle, @PathVariable String id){
        boolean delete = courseService.delete(id);
        if (delete)
            return ResponseEntity.ok(delete);
        return ResponseEntity.badRequest().body(false);

    }
    @PutMapping(("/{programTitle}/courses/{id}"))
    public ResponseEntity<Course> update(@PathVariable String programTitle, @PathVariable String id,@RequestBody Course course){
        Course update = courseService.update(id, course);
        if (update==null)
            return ResponseEntity.badRequest().body(update);
        return ResponseEntity.ok(update);

    }
}
