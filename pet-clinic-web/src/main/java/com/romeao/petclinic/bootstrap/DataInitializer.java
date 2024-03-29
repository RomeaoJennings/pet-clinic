package com.romeao.petclinic.bootstrap;

import com.romeao.petclinic.models.*;
import com.romeao.petclinic.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {


    private final Logger log = LoggerFactory.getLogger(DataInitializer.class);
    private final OwnerService ownerService;
    private final VetService vetService;
    private final VisitService visitService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;

    public DataInitializer(OwnerService ownerService, VetService vetService, VisitService visitService,
                           PetTypeService petTypeService, SpecialtyService specialtyService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.visitService = visitService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
    }

    @Override
    public void run(String... args) {
        log.info("Adding bootstrap data in DataInitializer");

        if (ownerService.findAll().size() > 0) {
            log.info("Found existing bootstrap objects.  Skipping addition of new objects.");
            return;
        }

        PetType dog = new PetType("Dog");
        PetType cat = new PetType("Cat");
        petTypeService.save(cat);
        petTypeService.save(dog);
        log.info("Loaded pet types...");

        Owner romeao = new Owner("Romeao", "Jennings", "18 Wakonda", "Dove Canyon", "5555555555");
        Owner caitlin = new Owner("Caitlin", "O'Neill", "3 Carmel Valley", "Coto de Caza", "5555555556");

        Pet sasha = new Pet("Sasha", dog, romeao, LocalDate.now());
        Pet cassie = new Pet("Cassie", cat, caitlin, LocalDate.now());

        romeao.getPets().add(sasha);
        caitlin.getPets().add(cassie);

        ownerService.save(romeao);
        ownerService.save(caitlin);
        log.info("Loaded owners...");

        Visit catVisit = new Visit();
        catVisit.setDate(LocalDate.now());
        catVisit.setPet(cassie);
        catVisit.setDescription("Sneezy Kitty");
        visitService.save(catVisit);
        log.info("Loaded visits...");

        Specialty radiology = new Specialty("Radiology");
        Specialty dentistry = new Specialty("Dentistry");
        Specialty surgery = new Specialty("Surgery");

        specialtyService.save(radiology);
        specialtyService.save(dentistry);
        specialtyService.save(surgery);
        log.info("Loaded specialties...");

        Vet liam = new Vet("Liam", "Jennings");
        liam.getSpecialties().add(radiology);
        liam.getSpecialties().add(dentistry);
        Vet kennedy = new Vet("Kennedy", "Jennings");
        kennedy.getSpecialties().add(surgery);

        vetService.save(liam);
        vetService.save(kennedy);

        log.info("Loaded vets...");
    }
}
