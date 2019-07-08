package com.romeao.petclinic.converters;

import com.romeao.petclinic.models.PetType;
import com.romeao.petclinic.services.PetTypeService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToPetTypeConverter implements Converter<String, PetType> {
    private final PetTypeService petTypeService;

    public StringToPetTypeConverter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public PetType convert(String name) {
        PetType petType = petTypeService.findByName(name);
        if (petType == null) {
            throw new RuntimeException(String.format("Unable to convert name '%s' to PetType", name));
        }
        return petType;
    }
}
