package com.nayemsiddique.studentadmissionserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
//@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
    @Id
    private String studentId;
    private LocalDate dob;
    @Embedded
    private Name name;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private ProgramTitle programTitle;
    private int batch;
    @Email
    private String email;
    @Enumerated(EnumType.STRING)
    //  @Convert(converter = BloodGroup.class)
    private BloodGroup bloodGroup;
    @Embedded
    private Address address;
    //private


    public Student(String studentId, LocalDate dob, Name name, Gender gender, ProgramTitle programTitle, int batch, @Email String email, BloodGroup bloodGroup) {
        this.studentId = studentId;
        this.name = name;
        this.gender = gender;
        this.programTitle = programTitle;
        this.batch = batch;
        this.email = email;
        this.bloodGroup = bloodGroup;
        this.dob = dob;
    }
}
