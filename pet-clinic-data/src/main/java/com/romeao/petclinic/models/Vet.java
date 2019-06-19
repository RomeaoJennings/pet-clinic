package com.romeao.petclinic.models;

import java.util.Set;

public class Vet extends Person {

    private Set<Specialty> specialization;

    public Vet() {
    }

    public Vet(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public Set<Specialty> getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Set<Specialty> specialization) {
        this.specialization = specialization;
    }
}
