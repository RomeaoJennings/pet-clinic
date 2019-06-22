package com.romeao.petclinic.repositories;

import com.romeao.petclinic.models.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> { }
