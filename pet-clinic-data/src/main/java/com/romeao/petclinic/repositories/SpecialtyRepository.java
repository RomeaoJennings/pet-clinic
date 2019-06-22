package com.romeao.petclinic.repositories;

import com.romeao.petclinic.models.Specialty;
import org.springframework.data.repository.CrudRepository;

public interface SpecialtyRepository extends CrudRepository<Specialty, Long> { }
