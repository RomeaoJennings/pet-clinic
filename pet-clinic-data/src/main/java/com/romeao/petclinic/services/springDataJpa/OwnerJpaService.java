package com.romeao.petclinic.services.springDataJpa;

import com.romeao.petclinic.models.Owner;
import com.romeao.petclinic.repositories.OwnerRepository;
import com.romeao.petclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile("springDataJpa")
public class OwnerJpaService extends AbstractJpaService<Owner> implements OwnerService {

    public OwnerJpaService(OwnerRepository ownerRepository) {
        super(ownerRepository);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return ((OwnerRepository)repository).findByLastName(lastName);
    }

    @Override
    public Set<Owner> findAllByLastNameContains(String lastName) {
        return ((OwnerRepository)repository).findAllByLastNameContains(lastName);
    }
}
