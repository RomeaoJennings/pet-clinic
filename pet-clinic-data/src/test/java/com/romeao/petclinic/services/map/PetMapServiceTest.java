package com.romeao.petclinic.services.map;

import com.romeao.petclinic.models.Owner;
import com.romeao.petclinic.models.Pet;
import com.romeao.petclinic.models.PetType;
import com.romeao.petclinic.services.PetService;
import com.romeao.petclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class PetMapServiceTest {

    final long NON_EXISTENT_PET_ID = 1L;

    @Mock
    PetTypeService petTypeService;
    PetService petService;

    private Pet pet;
    private PetType petType;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        petService = new PetMapService(petTypeService);

        petType = new PetType();
        petType.setName("Dog");
        pet = new Pet("DogName", petType, new Owner(), LocalDate.now());
    }

    @Test
    void findAll() {
        assertEquals(0, petService.findAll().size());
        petService.save(pet);
        assertEquals(1, petService.findAll().size());
    }

    @Test
    void findById() {
        petService.save(pet);
        // search for non-existent pet
        assertNull(petService.findById(NON_EXISTENT_PET_ID));

        // search for existing pet
        assertEquals(pet, petService.findById(pet.getId()));
    }

    @Test
    void save() {

        // saving null pet should throw exception
        assertThrows(IllegalArgumentException.class, () -> petService.save(null));

        // confirm that pet properly saved into repository
        petService.save(pet);
        assertTrue(petService.findAll().contains(pet));

        // verify that petService saves the underlying petType
        verify(petTypeService, times(1)).save(petType);
    }

    @Test
    void deleteById() {
        petService.save(pet);

        // trying to delete non-existent pet should not throw error
        petService.deleteById(NON_EXISTENT_PET_ID);
        assertEquals(1, petService.findAll().size());

        // confirm deletion by ID works
        petService.deleteById(pet.getId());
        assertEquals(0, petService.findAll().size());
    }

    @Test
    void delete() {
        petService.save(pet);

        // trying to delete non-existent pet should not throw error
        petService.delete(null);
        assertEquals(1, petService.findAll().size());

        // confirm deletion works
        petService.delete(pet);
        assertEquals(0, petService.findAll().size());
    }
}