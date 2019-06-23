package com.romeao.petclinic.services.springDataJpa;

import com.romeao.petclinic.models.Specialty;
import com.romeao.petclinic.repositories.SpecialtyRepository;
import com.romeao.petclinic.services.SpecialtyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("springDataJpa")
public class SpecialtyJpaService extends AbstractJpaService<Specialty> implements SpecialtyService {

    public SpecialtyJpaService(SpecialtyRepository specialtyRepository) {
        super(specialtyRepository);
    }
}
