package com.nayemsiddique.studentadmissionserver.repository;

import com.nayemsiddique.studentadmissionserver.model.Payment;
import com.nayemsiddique.studentadmissionserver.model.ProgramTitle;
import com.nayemsiddique.studentadmissionserver.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, String> {
    List<Payment> findAllByProgramTitle(ProgramTitle programTitle);

    List<Payment> findAllByStudentId(String id);

    List<Payment> findAllByStudentIdAndProgramTitle(String sid, ProgramTitle programTitle);

}
