package com.romeao.petclinic.services.map;

import com.romeao.petclinic.models.Specialty;
import com.romeao.petclinic.services.SpecialtyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SpecialtyMapServiceTest {

    private SpecialtyService specialtyService;
    
    private Specialty dentistry;
    private Specialty surgery;
    
    @BeforeEach
    void setUp() {
        specialtyService = new SpecialtyMapService();
        
        dentistry = new Specialty("Dentistry");
        specialtyService.save(dentistry);
        
        surgery = new Specialty("Surgery");
    }

    @Test
    void findAll() {
        Set<Specialty> result = specialtyService.findAll();

        assertTrue(result.contains(dentistry));
        assertFalse(result.contains(surgery));
        assertEquals(1, result.size());
    }

    @Test
    void findById() {
        assertNull(specialtyService.findById(surgery.getId()));
        assertEquals(dentistry, specialtyService.findById(dentistry.getId()));
    }

    @Test
    void save() {
        assertEquals(1, specialtyService.findAll().size());

        // saving existing object should not create more objects
        specialtyService.save(dentistry);
        assertEquals(1, specialtyService.findAll().size());

        // add surgery and confirm that it is saved
        specialtyService.save(surgery);
        assertEquals(2, specialtyService.findAll().size());
        assertTrue(specialtyService.findAll().contains(surgery));
    }

    @Test
    void deleteById() {
        // deleting item not in list should not affect it.
        assertEquals(1, specialtyService.findAll().size());
        specialtyService.deleteById(surgery.getId());
        assertEquals(1, specialtyService.findAll().size());

        // delete dentistry and confirm it is deleted
        specialtyService.deleteById(dentistry.getId());
        assertNull(specialtyService.findById(dentistry.getId()));
        assertEquals(0, specialtyService.findAll().size());
    }

    @Test
    void delete() {
        // deleting item not in list should not affect it.
        assertEquals(1, specialtyService.findAll().size());
        specialtyService.delete(surgery);
        assertEquals(1, specialtyService.findAll().size());

        // delete dentistry and confirm it is deleted
        specialtyService.delete(dentistry);
        assertNull(specialtyService.findById(dentistry.getId()));
        assertEquals(0, specialtyService.findAll().size());
    }
}