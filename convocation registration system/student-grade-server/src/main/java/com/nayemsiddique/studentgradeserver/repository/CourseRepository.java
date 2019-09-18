package com.nayemsiddique.studentgradeserver.repository;

import com.nayemsiddique.studentgradeserver.model.Course;
import com.nayemsiddique.studentgradeserver.model.ProgramTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,String> {
   // List<Course> findAllByProgramTitle(ProgramTitle programTitle);

   // @Query("select u from User u where u.emailAddress = ?1")
//    @Query("select u from Course u where u.program_program_title = ?1")
//    List<Course> findAllByProgramTitle(int programTitle);
    //List<Course>findCourseByIdContaining(String id);

    List<Course>findAllByCourseCodeContaining(String code);
    List<Course>findAllByCourseCode(String code);
}
