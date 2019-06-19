package com.romeao.petclinic.bootstrap;

import com.romeao.petclinic.models.Owner;
import com.romeao.petclinic.models.PetType;
import com.romeao.petclinic.models.Vet;
import com.romeao.petclinic.services.OwnerService;
import com.romeao.petclinic.services.PetTypeService;
import com.romeao.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataInitializer(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        petTypeService.save(new PetType("Dog"));
        petTypeService.save(new PetType("Cat"));


        ownerService.save(new Owner("Romeao", "Jennings"));
        ownerService.save(new Owner("Caitlin", "Jennings"));
        System.out.println("Loaded owners...");

        vetService.save(new Vet("Liam", "Jennings"));
        vetService.save(new Vet("Kennedy", "Jennings"));
        System.out.println("Loaded vets...");

    }
}
