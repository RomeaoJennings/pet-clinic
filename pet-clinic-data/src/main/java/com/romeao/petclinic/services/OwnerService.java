package com.romeao.petclinic.services;

import com.romeao.petclinic.models.Owner;

import java.util.Set;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

    Set<Owner> findAllByLastNameContains(String lastName);
}
