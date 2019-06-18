package com.romeao.petclinic.services.map;

import com.romeao.petclinic.models.Owner;
import com.romeao.petclinic.services.OwnerService;

public class OwnerServiceMap extends AbstractMapService<Owner> implements OwnerService
{
    @Override
    public Owner findByLastName(String lastName) {
        throw new RuntimeException("Not implemented");
    }
}
