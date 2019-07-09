package com.romeao.petclinic.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Owner extends Person {

    @NotBlank
    @Size(min = 1, max = 50)
    private String address;

    @NotBlank
    @Size(min = 1, max = 50)
    private String city;

    @NotBlank
    @Digits(fraction = 0, integer = 10)
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();

    public Owner() { }

    public Owner(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public Owner(String firstName, String lastName, String address, String city, String telephone) {
        super(firstName, lastName);
        this.address = address;
        this.city = city;
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }

    public void addPet(Pet... newPets) {
        for (Pet pet : newPets) {
            pets.add(pet);
            pet.setOwner(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        Owner owner = (Owner) o;
        if (getId() != null) {
            return getId() == owner.getId();
        }
        return Objects.equals(address, owner.address) &&
                Objects.equals(city, owner.city) &&
                Objects.equals(telephone, owner.telephone) &&
                Objects.equals(pets, owner.pets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, city, telephone, pets);
    }
}
