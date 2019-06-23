package com.romeao.petclinic.services.map;

import com.romeao.petclinic.models.Specialty;
import com.romeao.petclinic.services.SpecialtyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "map"})
public class SpecialtyMapService extends AbstractMapService<Specialty> implements SpecialtyService {
}
