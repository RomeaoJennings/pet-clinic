package com.romeao.petclinic.services.map;

import com.romeao.petclinic.models.Pet;
import com.romeao.petclinic.services.PetService;
import com.romeao.petclinic.services.PetTypeService;
import org.springframework.stereotype.Service;

@Service
public class PetServiceMap extends AbstractServiceMap<Pet> implements PetService {

    private final PetTypeService petTypeService;

    public PetServiceMap(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public Pet save(Pet object) {
        super.save(object);
        petTypeService.save(object.getPetType());
        return object;
    }
}
