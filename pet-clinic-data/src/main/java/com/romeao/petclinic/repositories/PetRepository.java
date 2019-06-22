package com.romeao.petclinic.repositories;

import com.romeao.petclinic.models.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> { }
