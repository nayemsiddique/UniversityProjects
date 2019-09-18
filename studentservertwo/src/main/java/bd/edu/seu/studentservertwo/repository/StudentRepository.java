package bd.edu.seu.studentservertwo.repository;

import bd.edu.seu.studentservertwo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
