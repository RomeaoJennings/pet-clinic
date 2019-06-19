package com.romeao.petclinic.services.map;

import com.romeao.petclinic.models.PetType;
import com.romeao.petclinic.services.PetTypeService;
import org.springframework.stereotype.Service;

@Service
public class PetTypeServiceMap extends AbstractMapService<PetType> implements PetTypeService {
}
