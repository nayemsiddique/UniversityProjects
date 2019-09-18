package com.nayemsiddique.studentadmissionserver.service;

import com.nayemsiddique.studentadmissionserver.model.Payment;
import com.nayemsiddique.studentadmissionserver.model.ProgramTitle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentServiceTest {
    @Autowired
    private PaymentService paymentService;

    @Test
    public void insert() {
        Payment payment = new Payment("2016000000159", ProgramTitle.cse, 12, 5000);
        paymentService.insert(payment);
    }
}
