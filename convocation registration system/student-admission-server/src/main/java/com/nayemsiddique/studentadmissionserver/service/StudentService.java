package com.nayemsiddique.studentadmissionserver.service;

import com.nayemsiddique.studentadmissionserver.exception.FileAlreadyExistsExceptions;
import com.nayemsiddique.studentadmissionserver.exception.FileDoesnotExistsException;
import com.nayemsiddique.studentadmissionserver.model.ProgramTitle;
import com.nayemsiddique.studentadmissionserver.model.Student;
import com.nayemsiddique.studentadmissionserver.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(String id) {
        return studentRepository.findById(id).get();
    }

    public Student insert(Student student) {
        Optional<Student> byId = studentRepository.findById(student.getStudentId());
        if (byId.isPresent()) {
            try {
                throw new FileAlreadyExistsExceptions(student.getStudentId());
            } catch (FileAlreadyExistsExceptions fileAlreadyExistsExceptions) {
                fileAlreadyExistsExceptions.printStackTrace();
                return null;
            }
        }
        return studentRepository.save(student);
    }

    public Student update(String id, Student student) {
        Optional<Student> byId = studentRepository.findById(id);
        if (byId.isPresent()) {
            student.setStudentId(id);
            return studentRepository.save(student);
        }
        try {
            throw new FileDoesnotExistsException(id);
        } catch (FileDoesnotExistsException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Boolean delete(String id) {
        Optional<Student> byId = studentRepository.findById(id);
        if (byId.isPresent()) {
            studentRepository.deleteById(id);
            return true;
        }
        try {
            throw new FileDoesnotExistsException(id);
        } catch (FileDoesnotExistsException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Student> findAllByProgramTitle(String programTitle) {
        return studentRepository.findAllByProgramTitle(ProgramTitle.valueOf(programTitle.toLowerCase()));
    }

    public Student findByProgramTitleAndID(String programTitle, String id) {
        return studentRepository.findByProgramTitleAndStudentId(ProgramTitle.valueOf(programTitle), id);
    }

}
