package com.nayemsiddique.studentgradeserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import java.io.Serializable;
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgramPrimaryKey implements Serializable {
    private int semesterID;
    @Enumerated(EnumType.STRING)
    private ProgramTitle programTitle;


}
