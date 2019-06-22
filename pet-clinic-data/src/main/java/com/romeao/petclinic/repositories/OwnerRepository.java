package com.romeao.petclinic.repositories;

import com.romeao.petclinic.models.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {}
