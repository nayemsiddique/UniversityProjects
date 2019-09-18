package com.nayemsiddique.studentadmissionserver.service;

import com.nayemsiddique.studentadmissionserver.exception.FileAlreadyExistsExceptions;
import com.nayemsiddique.studentadmissionserver.exception.FileDoesnotExistsException;
import com.nayemsiddique.studentadmissionserver.model.Payment;
import com.nayemsiddique.studentadmissionserver.model.ProgramTitle;
import com.nayemsiddique.studentadmissionserver.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    private PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment insert(Payment payment) {
        Optional<Payment> byId = paymentRepository.findById(payment.getId());
        if (byId.isPresent()) {
            try {
                throw new FileAlreadyExistsExceptions(payment.getId());
            } catch (FileAlreadyExistsExceptions fileAlreadyExistsExceptions) {
                fileAlreadyExistsExceptions.printStackTrace();
                return null;
            }
        }
        return paymentRepository.save(payment);

    }

    public Payment update(String id, Payment payment) {
        Optional<Payment> byId = paymentRepository.findById(id);
        if (byId.isPresent()) {
            payment.setId(id);
            return paymentRepository.save(payment);
        }
        try {
            throw new FileDoesnotExistsException(id);
        } catch (FileDoesnotExistsException e) {
            e.printStackTrace();
            return null;
        }

    }

    public Boolean delete(String id) {
        Optional<Payment> byId = paymentRepository.findById(id);
        if (byId.isPresent()) {
            paymentRepository.deleteById(id);
            return true;
        }
        try {
            throw new FileDoesnotExistsException(id);
        } catch (FileDoesnotExistsException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public List<Payment> findAllByProgramTitle(String programTitle) {
        return paymentRepository.findAllByProgramTitle(ProgramTitle.valueOf(programTitle));
    }

    public List<Payment> findAllByStudentID(String id) {
        return paymentRepository.findAllByStudentId(id);
    }

    public Payment findById(String id) {
        return paymentRepository.findById(id).get();
    }

    public List<Payment> findAllByStudentIDAndProgramTitle(String sid, String programTitle) {
        return paymentRepository.findAllByStudentIdAndProgramTitle(sid, ProgramTitle.valueOf(programTitle));
    }

}
