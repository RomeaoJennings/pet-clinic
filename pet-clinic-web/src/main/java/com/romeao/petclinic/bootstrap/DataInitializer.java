package com.romeao.petclinic.bootstrap;

import com.romeao.petclinic.models.Owner;
import com.romeao.petclinic.models.Vet;
import com.romeao.petclinic.services.OwnerService;
import com.romeao.petclinic.services.VetService;
import com.romeao.petclinic.services.map.OwnerServiceMap;
import com.romeao.petclinic.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataInitializer() {
        ownerService = new OwnerServiceMap();
        vetService = new VetServiceMap();
    }

    @Override
    public void run(String... args) throws Exception {

        ownerService.save(new Owner(1L, "Romeao", "Jennings"));
        ownerService.save(new Owner(2L, "Caitlin", "Jennings"));
        System.out.println("Loaded owners...");

        vetService.save(new Vet(1L, "Liam", "Jennings"));
        vetService.save(new Vet(2L, "Kennedy", "Jennings"));
        System.out.println("Loaded vets...");

    }
}
