package com.romeao.petclinic.services.map;

import com.romeao.petclinic.models.Specialty;
import com.romeao.petclinic.models.Vet;
import com.romeao.petclinic.services.SpecialtyService;
import com.romeao.petclinic.services.VetService;
import org.springframework.stereotype.Service;

@Service
public class VetServiceMap extends AbstractServiceMap<Vet> implements VetService {

    private final SpecialtyService specialtyService;

    public VetServiceMap(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Override
    public Vet save(Vet object) {
        super.save(object);
        for (Specialty specialty : object.getSpecialities()) {
            specialtyService.save(specialty);
        }
        return object;
    }
}
