package com.romeao.petclinic.services.map;

import com.romeao.petclinic.models.Pet;
import com.romeao.petclinic.services.PetService;
import org.springframework.stereotype.Service;

@Service
public class PetServiceMap extends AbstractServiceMap<Pet> implements PetService {
}
