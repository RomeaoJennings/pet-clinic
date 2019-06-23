package com.romeao.petclinic.services.springDataJpa;

import com.romeao.petclinic.models.Visit;
import com.romeao.petclinic.repositories.VisitRepository;
import com.romeao.petclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("springDataJpa")
public class VisitJpaService extends AbstractJpaService<Visit> implements VisitService {

    public VisitJpaService(VisitRepository visitRepository) {
        super(visitRepository);
    }
}
