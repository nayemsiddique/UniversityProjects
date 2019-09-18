package bd.edu.seu.studentservertwo.service;

import bd.edu.seu.studentservertwo.exception.ResourceAlreadyExistsException;
import bd.edu.seu.studentservertwo.exception.ResourceDoesnotExistsException;
import bd.edu.seu.studentservertwo.model.Student;
import bd.edu.seu.studentservertwo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements GenericService<Student, Long> {
    private StudentRepository studentRepository;
    @Value("${baseUrl}/students")
    String baseUrl;


    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student create(Student student) throws ResourceAlreadyExistsException {
//        Optional<Student> studentOptional= studentRepository.findById(student.getId());
//        if (studentOptional.isPresent()) {
//            throw new ResourceAlreadyExistsException(student.getId()+"");
//        }
//        else return studentRepository.save(student);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Student> requestEntity = new HttpEntity<>(student, requestHeaders);
        restTemplate.exchange(baseUrl, HttpMethod.POST, requestEntity, Student.class);

        //Student student1 = response.getBody();
        return student;
    }

    @Override
    public Student update(Long id, Student student) throws ResourceDoesnotExistsException {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            student.setId(id);
            return studentRepository.save(student);
        } else throw new ResourceDoesnotExistsException(id + "");
    }

    @Override
    public List<Student> findAll() {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Student>> response = restTemplate.exchange(
                baseUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Student>>() {
                });
        List<Student> studentList = response.getBody();
        return studentList;
        //        return studentRepository.findAll();

    }

    @Override
    public Student findById(Long aLong) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Student> response = restTemplate.exchange(
                baseUrl + "/" + aLong,
                HttpMethod.GET,
                null,
                Student.class);
        Student student = response.getBody();
        //return studentRepository.findById(aLong).get();
        return student;
    }

    @Override
    public boolean delete(Student student) /*throws ResourceDoesnotExistsException*/ {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(baseUrl + "/" + student.getId(), student);

//        Optional<Student> studentOptional= studentRepository.findById(student.getId());
//        if (studentOptional.isPresent()){
//            studentRepository.delete(student);
//            return true;
//        }
//        throw new ResourceDoesnotExistsException(student.getId()+"");
        return true;
    }
}
