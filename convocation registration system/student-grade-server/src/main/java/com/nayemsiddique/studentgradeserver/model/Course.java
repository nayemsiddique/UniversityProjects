package com.nayemsiddique.studentgradeserver.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Course {
    private ProgramTitle programTitle;
    @Id
    private String Id;

    private String courseCode;

    private String courseTitle;

    private double creditHours;
    private int semesterId;

    public Course(ProgramTitle programTitle,  String courseCode,  String courseTitle, double creditHours) {
        this.programTitle= programTitle;
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.creditHours = creditHours;
        Id=courseCode+"."+semesterId;
    }

//      @ToString.Include
//      @org.springframework.data.annotation.Id
//    private String getLabel() {
//        return courseCode + "." + program.getProgramPrimaryKey().getSemesterID();
//    }


}
