package com.nayemsiddique.studentgradeserver.service;

import com.nayemsiddique.studentgradeserver.exception.FileAlreadyExistsExceptions;
import com.nayemsiddique.studentgradeserver.exception.FileDoesnotExistsException;
import com.nayemsiddique.studentgradeserver.model.Course;
import com.nayemsiddique.studentgradeserver.model.Program;
import com.nayemsiddique.studentgradeserver.model.ProgramTitle;
import com.nayemsiddique.studentgradeserver.repository.CourseRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    public List<Course> findAll(ProgramTitle programTitle){

       //return courseRepository.findAllByProgramTitle(ProgramTitle.valueOf(p));
      return courseRepository.findAllByCourseCodeContaining(programTitle+"");
       // return  new ArrayList<>();
    }
    public Course insert(Course course){
        Optional<Course> byId = courseRepository.findById(course.getId());
        if (byId.isPresent()) {
            try {
                throw  new FileAlreadyExistsExceptions(course.getId());
            } catch (FileAlreadyExistsExceptions fileAlreadyExistsExceptions) {
                fileAlreadyExistsExceptions.printStackTrace();
                return null;
            }

        }
        return courseRepository.save(course);
    }

    public Course findById(String id){
        return courseRepository.findById(id).get();
    }
    public Course update(String id,Course course){
        Optional<Course> byId = courseRepository.findById(id);
        if (byId.isPresent()) {
            course.setId(id);
            return courseRepository.save(course);
        }
        else {
            try {
                throw new FileDoesnotExistsException(id);
            } catch (FileDoesnotExistsException e) {
                e.printStackTrace();
                return null;
            }
        }

    }
    public boolean delete(String id){
        Optional<Course> byId = courseRepository.findById(id);
        if (byId.isPresent()) {
            courseRepository.deleteById(id);
            return true;
        }
        else {
            try {
                throw new FileDoesnotExistsException(id);
            } catch (FileDoesnotExistsException e) {
                e.printStackTrace();
                return false;
            }
        }
    }


}
