package com.romeao.petclinic.services.springDataJpa;

import com.romeao.petclinic.models.Vet;
import com.romeao.petclinic.repositories.VetRepository;
import com.romeao.petclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("springDataJpa")
public class VetJpaService extends AbstractJpaService<Vet> implements VetService {

    public VetJpaService(VetRepository vetRepository) {
        super(vetRepository);
    }
}
