package com.romeao.petclinic.services.springDataJpa;

import com.romeao.petclinic.models.Pet;
import com.romeao.petclinic.repositories.PetRepository;
import com.romeao.petclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("springDataJpa")
public class PetJpaService extends AbstractJpaService<Pet> implements PetService {

    public PetJpaService(PetRepository petRepository) {
        super(petRepository);
    }
}
