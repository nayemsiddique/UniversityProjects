package com.nayemsiddique.studentadmissionserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;

//@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Payment {
    @Id
    private String id;
    private String studentId;
    private ProgramTitle programTitle;
    private int semesterId;
    private double amount;

    public Payment(String studentId, ProgramTitle programTitle, int semesterId, double amount) {
        this.studentId = studentId;
        this.programTitle = programTitle;
        this.semesterId = semesterId;
        this.amount = amount;
        id = studentId + "." + studentId;
    }
}
