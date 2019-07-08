package com.romeao.petclinic.models;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class PetType extends BaseEntity {
    private String name;

    public PetType() {}

    public PetType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        PetType petType = (PetType) o;
        if (getId() != null) {
            return getId() == petType.getId();
        }
        return Objects.equals(name, petType.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
