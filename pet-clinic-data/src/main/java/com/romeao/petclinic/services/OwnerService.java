package com.romeao.petclinic.services;

import com.romeao.petclinic.models.Owner;

import java.util.Set;

public interface OwnerService {
    Owner findById(Long id);
    Owner findByLastName(String lastName);
    Owner save(Owner owner);
    Set<Owner> findAll();
}
