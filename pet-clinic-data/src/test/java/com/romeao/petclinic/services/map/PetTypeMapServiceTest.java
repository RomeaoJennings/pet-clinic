package com.romeao.petclinic.services.map;

import com.romeao.petclinic.models.PetType;
import com.romeao.petclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PetTypeMapServiceTest {

    PetTypeService petTypeService;

    private PetType dog;
    private PetType cat;


    @BeforeEach
    void setUp() {
        petTypeService = new PetTypeMapService();

        dog = new PetType("Dog");
        cat = new PetType("Cat");
        petTypeService.save(dog);
    }

    @Test
    void findAll() {
        Set<PetType> result = petTypeService.findAll();

        assertTrue(result.contains(dog));
        assertFalse(result.contains(cat));
        assertEquals(1, result.size());
    }

    @Test
    void findById() {
        assertNull(petTypeService.findById(cat.getId()));
        assertEquals(dog, petTypeService.findById(dog.getId()));
    }

    @Test
    void save() {
        assertEquals(1, petTypeService.findAll().size());

        // saving existing object should not create more objects
        petTypeService.save(dog);
        assertEquals(1, petTypeService.findAll().size());

        // add cat and confirm that it is saved
        petTypeService.save(cat);
        assertEquals(2, petTypeService.findAll().size());
        assertTrue(petTypeService.findAll().contains(cat));
    }

    @Test
    void deleteById() {
        // deleting item not in list should not affect it.
        assertEquals(1, petTypeService.findAll().size());
        petTypeService.deleteById(cat.getId());
        assertEquals(1, petTypeService.findAll().size());

        // delete dog and confirm it is deleted
        petTypeService.deleteById(dog.getId());
        assertNull(petTypeService.findById(dog.getId()));
        assertEquals(0, petTypeService.findAll().size());
    }

    @Test
    void delete() {
        // deleting item not in list should not affect it.
        assertEquals(1, petTypeService.findAll().size());
        petTypeService.delete(cat);
        assertEquals(1, petTypeService.findAll().size());

        // delete dog and confirm it is deleted
        petTypeService.delete(dog);
        assertNull(petTypeService.findById(dog.getId()));
        assertEquals(0, petTypeService.findAll().size());
    }
}