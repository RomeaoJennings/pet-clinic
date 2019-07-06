package com.romeao.petclinic.services.map;

import com.romeao.petclinic.models.Owner;
import com.romeao.petclinic.models.Pet;
import com.romeao.petclinic.models.PetType;
import com.romeao.petclinic.services.OwnerService;
import com.romeao.petclinic.services.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class OwnerMapServiceTest {

    @Mock
    private PetService petService;

    private OwnerService ownerService;
    private Owner owner;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ownerService = new OwnerMapService(petService);
    }

    private void addOwner() {
        owner = new Owner("test", "person");
        Pet pet1 = new Pet("pet1", new PetType(), owner, LocalDate.now());
        Pet pet2 = new Pet("pet2", new PetType(), owner, LocalDate.now());
        owner.getPets().add(pet1);
        owner.getPets().add(pet2);

        ownerService.save(owner);
    }

    @Test
    void findByLastName() {
        addOwner();

        assertNull(ownerService.findByLastName("test"));
        assertEquals(owner, ownerService.findByLastName("person"));
    }

    @Test
    void findAllByLastNameLike() {
        addOwner();
        ownerService.save(new Owner("test", "person2"));
        ownerService.save(new Owner("test", "perfume"));

        assertEquals(2, ownerService.findAllByLastNameContains("person").size());
        assertEquals(3, ownerService.findAllByLastNameContains("per").size());
        assertEquals(1, ownerService.findAllByLastNameContains("perf").size());
    }

    @Test
    void save() {
        addOwner();

        assertThrows(IllegalArgumentException.class, () -> ownerService.save(null));

        assertTrue(ownerService.findAll().contains(owner));
        verify(petService, times(2)).save(any());
    }

    @Test
    void findAll() {
        assertEquals(0, ownerService.findAll().size());
        addOwner();
        assertEquals(1, ownerService.findAll().size());
    }

    @Test
    void findById() {
        addOwner();
        assertNull(ownerService.findById(1L));
        assertEquals(owner, ownerService.findById(0L));
    }

    @Test
    void deleteById() {
        addOwner();

        //try to delete owner that does not exist in the set
        ownerService.deleteById(1L);
        assertEquals(1, ownerService.findAll().size());

        //delete item that does exist
        ownerService.deleteById(0L);
        assertEquals(0, ownerService.findAll().size());

    }

    @Test
    void delete() {
        addOwner();

        //try to delete owner that does not exist in the set
        ownerService.delete(new Owner());
        assertEquals(1, ownerService.findAll().size());

        //delete item that does exist
        ownerService.delete(owner);
        assertEquals(0, ownerService.findAll().size());
    }
}