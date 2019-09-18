package com.nayemsiddique.studentgradeserver.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
public class Grade {

    @Id
    @ToString.Exclude
    private String id;
    @Enumerated(EnumType.STRING)
    private ProgramTitle programTitle;
    @NotNull
    private String studentID;
    private String courseCode;
    private double grade;

    public Grade(ProgramTitle programTitle, @NotNull String studentID, String courseCode, double grade) {
        this.programTitle = programTitle;
        this.studentID = studentID;
        this.courseCode = courseCode;
        this.grade = grade;
        id=courseCode+"."+studentID;
    }
}
