package com.romeao.petclinic.bootstrap;

import com.romeao.petclinic.models.*;
import com.romeao.petclinic.services.OwnerService;
import com.romeao.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataInitializer(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType("Dog");
        PetType cat = new PetType("Cat");


        Owner romeao = new Owner("Romeao", "Jennings", "18 Wakonda", "Dove Canyon", "(555) 555-5555");
        romeao.getPets().add(new Pet("Sasha", dog, romeao, LocalDate.now()));
        ownerService.save(romeao);

        Owner caitlin = new Owner("Caitlin", "Jennings", "3 Carmel Valley", "Coto de Caza", "(555) 555-5556");
        romeao.getPets().add(new Pet("Cassie", cat, caitlin, LocalDate.now()));
        ownerService.save(caitlin);
        System.out.println("Loaded owners...");

        Specialty radiology = new Specialty("Radiology");
        Specialty dentistry = new Specialty("Dentistry");
        Specialty surgery = new Specialty("Surgery");

        Vet liam = new Vet("Liam", "Jennings");
        liam.getSpecialities().add(radiology);
        liam.getSpecialities().add(dentistry);
        Vet kennedy = new Vet("Kennedy", "Jennings");
        kennedy.getSpecialities().add(surgery);

        vetService.save(liam);
        vetService.save(kennedy);

        System.out.println("Loaded vets...");
    }
}
