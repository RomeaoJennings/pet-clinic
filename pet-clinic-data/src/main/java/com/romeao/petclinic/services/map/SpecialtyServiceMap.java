package com.romeao.petclinic.services.map;

import com.romeao.petclinic.models.Specialty;
import com.romeao.petclinic.services.SpecialtyService;
import org.springframework.stereotype.Service;

@Service
public class SpecialtyServiceMap extends AbstractServiceMap<Specialty> implements SpecialtyService {
}
