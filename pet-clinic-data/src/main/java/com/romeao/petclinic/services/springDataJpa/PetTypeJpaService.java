package com.romeao.petclinic.services.springDataJpa;

import com.romeao.petclinic.models.PetType;
import com.romeao.petclinic.repositories.PetTypeRepository;
import com.romeao.petclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("springDataJpa")
public class PetTypeJpaService extends AbstractJpaService<PetType> implements PetTypeService {

    public PetTypeJpaService(PetTypeRepository petTypeRepository) {
        super(petTypeRepository);
    }

    @Override
    public PetType findByName(String name) {
        return ((PetTypeRepository)repository).findByName(name);
    }
}
