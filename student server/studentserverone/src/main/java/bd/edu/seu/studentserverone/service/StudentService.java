package bd.edu.seu.studentserverone.service;

import bd.edu.seu.studentserverone.model.Student;
import bd.edu.seu.studentserverone.repository.StudentRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements GenericService<Student, Long> {
    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(Long aLong) {
        return studentRepository.findById(aLong).get();
    }

    @Override
    public boolean delete(Long id) {
        Optional<Student> byId = studentRepository.findById(id);
        byId.ifPresent(student -> studentRepository.deleteById(id));
        return byId.isPresent();

    }

    @Override
    public boolean add(Student student) {
        Optional<Student> studentOptional = studentRepository.findById(student.getId());
        System.out.println(studentOptional.isPresent());
        if (studentOptional.isPresent()) return false;
        studentRepository.save(student);
        return true;
    }
}
