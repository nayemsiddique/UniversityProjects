package com.nayemsiddique.studentadmissionserver.controller;

import com.nayemsiddique.studentadmissionserver.model.Payment;
import com.nayemsiddique.studentadmissionserver.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/payments")
public class PaymentController {
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("")
    public ResponseEntity<List<Payment>> findAll() {
        return ResponseEntity.ok(paymentService.findAll());
    }

    @GetMapping("?sid={id}")
    public ResponseEntity<List<Payment>> findAllByStudentId(@PathVariable String id) {
        List<Payment> allByStudentID = paymentService.findAllByStudentID(id);
        if (allByStudentID.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(allByStudentID);

    }

    @GetMapping("?program={programTitle}")
    public ResponseEntity<List<Payment>> findAllByProgram(@PathVariable String programTitle) {
        List<Payment> allByProgramTitle = paymentService.findAllByProgramTitle(programTitle);
        if (allByProgramTitle.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(allByProgramTitle);
    }

    @GetMapping("?sid={id}&program={programTitle}")
    public ResponseEntity<List<Payment>> findAllByStudentIDAndProgramTitle(@PathVariable String id, @PathVariable String programTitle) {
        List<Payment> allByStudentIDAndProgramTitle = paymentService.findAllByStudentIDAndProgramTitle(id, programTitle);
        if (allByStudentIDAndProgramTitle.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(allByStudentIDAndProgramTitle);

    }

    @PostMapping("")
    public ResponseEntity<Payment> inset(@RequestBody Payment payment) {
        Payment insert = paymentService.insert(payment);
        if (insert == null) return ResponseEntity.badRequest().body(payment);
        return ResponseEntity.ok(insert);
    }

    @PutMapping("/{PaymentId}")
    public ResponseEntity<Payment> update(@RequestBody Payment payment, @PathVariable String PaymentId) {
        Payment update = paymentService.update(PaymentId, payment);
        if (update == null) return ResponseEntity.badRequest().body(payment);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{PaymentId}")
    public ResponseEntity<Boolean> delete(@PathVariable String PaymentId) {
        Boolean deleted = paymentService.delete(PaymentId);
        if (deleted) return ResponseEntity.ok(true);
        return ResponseEntity.badRequest().body(false);
    }

}
