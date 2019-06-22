package com.romeao.petclinic.models;

import javax.persistence.Entity;

@Entity
public class Specialty extends BaseEntity {

    private String description;

    public Specialty(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
