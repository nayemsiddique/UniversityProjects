package com.nayemsiddique.studentgradeserver.service;

import com.nayemsiddique.studentgradeserver.exception.FileAlreadyExistsExceptions;
import com.nayemsiddique.studentgradeserver.exception.FileDoesnotExistsException;
import com.nayemsiddique.studentgradeserver.model.Course;
import com.nayemsiddique.studentgradeserver.model.Grade;
import com.nayemsiddique.studentgradeserver.model.ProgramTitle;
import com.nayemsiddique.studentgradeserver.repository.CourseRepository;
import com.nayemsiddique.studentgradeserver.repository.GradeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeService {
    private GradeRepository gradeRepository;
    private CourseRepository courseRepository;

    public GradeService(GradeRepository gradeRepository, CourseRepository courseRepository) {
        this.gradeRepository = gradeRepository;
        this.courseRepository = courseRepository;
    }

    public Grade insert(Grade grade){
        Optional<Grade> byId = gradeRepository.findById(grade.getId());
        if (byId.isPresent()) {
            try {
                throw new FileAlreadyExistsExceptions(grade.getId());
            } catch (FileAlreadyExistsExceptions fileAlreadyExistsExceptions) {
                fileAlreadyExistsExceptions.printStackTrace();
                return null;
            }
        }

        List<Course> allByCourseCode = courseRepository.findAllByCourseCode(grade.getCourseCode());
        if (allByCourseCode.isEmpty()) {
            try {
                throw  new FileDoesnotExistsException(grade.getCourseCode());
            } catch (FileDoesnotExistsException e) {
                e.printStackTrace();
                return  null;
            }
        }
       return gradeRepository.save(grade);
    }
    public List<Grade> findAll(ProgramTitle programTitle){
        return gradeRepository.findAllByProgramTitle(programTitle);
    }
    public  Grade findByID(String id){
        return gradeRepository.findById(id).get();
    }
    public Grade update(String id,Grade grade){
        Optional<Grade> byId = gradeRepository.findById(id);
        if (byId.isPresent()) {
            grade.setId(id);
            return gradeRepository.save(grade);
        }
        else{
            try {
                throw  new FileDoesnotExistsException(id);
            } catch (FileDoesnotExistsException e) {
                e.printStackTrace();
                return  null;
            }
        }


    }
    public boolean delete(String id){
        Optional<Grade> byId = gradeRepository.findById(id);
        if (byId.isPresent()) {
            gradeRepository.deleteById(id);
            return true;
        }
        else {
            try {
                throw new FileDoesnotExistsException(id);
            } catch (FileDoesnotExistsException e) {
                e.printStackTrace();
                return  false;
            }
        }
    }
}
