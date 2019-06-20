package com.romeao.petclinic.services.map;

import com.romeao.petclinic.models.Owner;
import com.romeao.petclinic.models.Pet;
import com.romeao.petclinic.services.OwnerService;
import com.romeao.petclinic.services.PetService;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceMap extends AbstractServiceMap<Owner> implements OwnerService {

    private final PetService petService;

    public OwnerServiceMap(PetService petService) {
        this.petService = petService;
    }

    @Override
    public Owner findByLastName(String lastName) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public Owner save(Owner object) {
        super.save(object);
        for (Pet pet : object.getPets()) {
            petService.save(pet);
        }
        return object;
    }
}
