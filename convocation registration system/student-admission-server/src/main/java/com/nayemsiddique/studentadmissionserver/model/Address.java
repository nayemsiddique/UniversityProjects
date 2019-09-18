package com.nayemsiddique.studentadmissionserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {
    private String city;
    private String village;
    private String postOffice;
    private String postalCode;
    private String district;
    private String streetAddress;
    private String phoneNumber;
}
