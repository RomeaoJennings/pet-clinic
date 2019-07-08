package com.romeao.petclinic.services;

import com.romeao.petclinic.models.PetType;

public interface PetTypeService extends CrudService<PetType, Long> {

    PetType findByName(String name);
}
