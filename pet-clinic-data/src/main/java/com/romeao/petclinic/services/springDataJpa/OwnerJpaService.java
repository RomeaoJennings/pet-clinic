package com.romeao.petclinic.services.springDataJpa;

import com.romeao.petclinic.models.Owner;
import com.romeao.petclinic.repositories.OwnerRepository;
import com.romeao.petclinic.services.OwnerService;
import org.springframework.stereotype.Service;

@Service
public class OwnerJpaService extends AbstractJpaService<Owner> implements OwnerService {

    public OwnerJpaService(OwnerRepository ownerRepository) {
        super(ownerRepository);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return ((OwnerRepository)repository).findByLastName(lastName);
    }
}
