package com.nayemsiddique.studentgradeserver.repository;

import com.nayemsiddique.studentgradeserver.model.Course;
import com.nayemsiddique.studentgradeserver.model.Grade;
import com.nayemsiddique.studentgradeserver.model.ProgramTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade,String> {
    List<Grade> findAllByProgramTitle(ProgramTitle  programTitle);
    //List<Grade>findAllByCourse(ProgramTitle course);

}
