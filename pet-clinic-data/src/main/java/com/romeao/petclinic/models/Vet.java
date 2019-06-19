package com.romeao.petclinic.models;

import java.util.HashSet;
import java.util.Set;

public class Vet extends Person {

    private Set<Specialty> specialities = new HashSet<>();

    public Vet() {
    }

    public Vet(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public Set<Specialty> getSpecialities() {
        return specialities;
    }

    public void setSpecialization(Set<Specialty> specialties) {
        this.specialities = specialties;
    }
}
