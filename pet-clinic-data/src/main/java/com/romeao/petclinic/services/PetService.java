package com.romeao.petclinic.services;

import com.romeao.petclinic.models.Pet;

import java.util.Set;

public interface PetService {
    Pet findById(Long id);
    Pet save(Pet pet);
    Set<Pet> findAll();
}
