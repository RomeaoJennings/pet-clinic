package com.romeao.petclinic.services.map;

import com.romeao.petclinic.models.Specialty;
import com.romeao.petclinic.models.Vet;
import com.romeao.petclinic.services.SpecialtyService;
import com.romeao.petclinic.services.VetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class VetMapServiceTest {

    private VetService vetService;
    
    @Mock 
    private SpecialtyService specialtyService;

    private Specialty dentistry = new Specialty("Dentistry");
    private Specialty surgery = new Specialty("Surgery");
    private Specialty radiology = new Specialty("Radiology");

    private Vet dogVet;

    private Vet catVet;
    
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        vetService = new VetMapService(specialtyService);

        dentistry = new Specialty("Dentistry");
        surgery = new Specialty("Surgery");
        radiology = new Specialty("Radiology");

        dogVet = new Vet("Vet", "ForDogs");
        dogVet.getSpecialities().add(dentistry);
        dogVet.getSpecialities().add(surgery);

        vetService.save(dogVet);
        
        catVet = new Vet("Vet", "ForCats");
        catVet.getSpecialities().add(radiology);
    }

    @Test
    void findAll() {
        Set<Vet> result = vetService.findAll();

        assertTrue(result.contains(dogVet));
        assertFalse(result.contains(catVet));
        assertEquals(1, result.size());
    }

    @Test
    void findById() {
        assertNull(vetService.findById(catVet.getId()));
        assertEquals(dogVet, vetService.findById(dogVet.getId()));
    }

    @Test
    void save() {
        assertEquals(1, vetService.findAll().size());

        // saving existing object should not create more objects
        vetService.save(dogVet);
        assertEquals(1, vetService.findAll().size());
        // Save gets called twice in setUp, and twice more here
        verify(specialtyService, times(4)).save(any());

        // add catVet and confirm that it is saved
        vetService.save(catVet);
        assertEquals(2, vetService.findAll().size());
        assertTrue(vetService.findAll().contains(catVet));
        verify(specialtyService, times(5)).save(any());
    }

    @Test
    void deleteById() {
        // deleting item not in list should not affect it.
        assertEquals(1, vetService.findAll().size());
        vetService.deleteById(catVet.getId());
        assertEquals(1, vetService.findAll().size());

        // delete dogVet and confirm it is deleted
        vetService.deleteById(dogVet.getId());
        assertNull(vetService.findById(dogVet.getId()));
        assertEquals(0, vetService.findAll().size());
    }

    @Test
    void delete() {
        // deleting item not in list should not affect it.
        assertEquals(1, vetService.findAll().size());
        vetService.delete(catVet);
        assertEquals(1, vetService.findAll().size());

        // delete dogVet and confirm it is deleted
        vetService.delete(dogVet);
        assertNull(vetService.findById(dogVet.getId()));
        assertEquals(0, vetService.findAll().size());
    }
}