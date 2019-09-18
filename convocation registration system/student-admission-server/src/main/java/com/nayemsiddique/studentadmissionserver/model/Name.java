package com.nayemsiddique.studentadmissionserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Name {
    @NotNull
    private String firstName;
    @NotNull
    private String lastname;

}
