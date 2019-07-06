package com.romeao.petclinic.repositories;

import com.romeao.petclinic.models.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Owner findByLastName(String lastName);

    Set<Owner> findAllByLastNameContains(String lastName);
}
