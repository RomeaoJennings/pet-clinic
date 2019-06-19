package com.romeao.petclinic.bootstrap;

import com.romeao.petclinic.models.Owner;
import com.romeao.petclinic.models.Pet;
import com.romeao.petclinic.models.PetType;
import com.romeao.petclinic.models.Vet;
import com.romeao.petclinic.services.OwnerService;
import com.romeao.petclinic.services.PetTypeService;
import com.romeao.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

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

        PetType dog = petTypeService.save(new PetType("Dog"));
        PetType cat = petTypeService.save(new PetType("Cat"));


        Owner romeao = new Owner("Romeao", "Jennings", "18 Wakonda", "Dove Canyon", "(555) 555-5555");
        romeao.getPets().add(new Pet("Sasha", dog, romeao, LocalDate.now()));
        ownerService.save(romeao);

        Owner caitlin = new Owner("Caitlin", "Jennings", "3 Carmel Valley", "Coto de Caza", "(555) 555-5556");
        romeao.getPets().add(new Pet("Cassie", cat, caitlin, LocalDate.now()));
        ownerService.save(caitlin);
        System.out.println("Loaded owners...");

        vetService.save(new Vet("Liam", "Jennings"));
        vetService.save(new Vet("Kennedy", "Jennings"));
        System.out.println("Loaded vets...");
    }
}
