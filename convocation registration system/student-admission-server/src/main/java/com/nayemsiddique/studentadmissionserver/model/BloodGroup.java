package com.nayemsiddique.studentadmissionserver.model;

public enum BloodGroup {
    A_POSITIVE("A+"),
    A_NEGATIVE("A-"),
    B_NEGATIVE("B-"),
    B_POSITIVE("B+"),
    AB_NEGATIVE("AB-"),
    AB_POSITIVE("AB+"),
    O_NEGATIVE("O-"),
    O_POSITIVE("O+");
    private final String label;

    private BloodGroup(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
