package com.romeao.petclinic.services;

import com.romeao.petclinic.models.Vet;

import java.util.Set;

public interface VetService {

    Vet findById(Long id);
    Vet save(Vet vet);
    Set<Vet> findAll();
}
