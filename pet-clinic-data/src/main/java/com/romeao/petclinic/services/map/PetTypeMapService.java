package com.romeao.petclinic.services.map;

import com.romeao.petclinic.models.PetType;
import com.romeao.petclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "map"})
public class PetTypeMapService extends AbstractMapService<PetType> implements PetTypeService {
}
