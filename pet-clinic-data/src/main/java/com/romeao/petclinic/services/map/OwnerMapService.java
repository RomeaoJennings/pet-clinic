package com.romeao.petclinic.services.map;

import com.romeao.petclinic.models.Owner;
import com.romeao.petclinic.models.Pet;
import com.romeao.petclinic.services.OwnerService;
import com.romeao.petclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Profile({"default", "map"})
public class OwnerMapService extends AbstractMapService<Owner> implements OwnerService {

    private final PetService petService;

    public OwnerMapService(PetService petService) {
        this.petService = petService;
    }

    @Override
    public Owner findByLastName(String lastName) {
        for (Owner owner : map.values()) {
            if (owner.getLastName() == lastName) {
                return owner;
            }
        }
        return null;
    }

    @Override
    public Set<Owner> findAllByLastNameContains(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            return findAll();
        }
        return map.values().stream()
                .filter(owner -> owner.getLastName().contains(lastName))
                .collect(Collectors.toSet());
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
