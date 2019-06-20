package com.romeao.petclinic.services.map;

import com.romeao.petclinic.models.Vet;
import com.romeao.petclinic.services.VetService;
import org.springframework.stereotype.Service;

@Service
public class VetServiceMap extends AbstractServiceMap<Vet> implements VetService {
}
