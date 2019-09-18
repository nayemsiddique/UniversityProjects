package com.nayemsiddique.studentadmissionserver.repository;

import com.nayemsiddique.studentadmissionserver.model.ProgramTitle;
import com.nayemsiddique.studentadmissionserver.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {

    List<Student> findAllByProgramTitle(ProgramTitle programTitle);

    Student findByProgramTitleAndStudentId(ProgramTitle programTitle, String id);
}
