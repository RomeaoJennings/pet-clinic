package com.romeao.petclinic.repositories;

import com.romeao.petclinic.models.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> { }
