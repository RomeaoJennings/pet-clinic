package com.romeao.petclinic.repositories;

import com.romeao.petclinic.models.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {}
