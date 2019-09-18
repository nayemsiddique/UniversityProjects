package com.nayemsiddique.studentgradeserver.repository;

import com.nayemsiddique.studentgradeserver.model.Program;
import com.nayemsiddique.studentgradeserver.model.ProgramTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepository extends JpaRepository<Program,Object> {
   // Program findAllBy(ProgramTitle i, int semesterId);
}
