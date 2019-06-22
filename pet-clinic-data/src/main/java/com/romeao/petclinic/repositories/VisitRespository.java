package com.romeao.petclinic.repositories;

import com.romeao.petclinic.models.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRespository extends CrudRepository<Visit, Long> { }
