package com.romeao.petclinic.services.springDataJpa;

import com.romeao.petclinic.models.Owner;
import com.romeao.petclinic.repositories.OwnerRepository;
import com.romeao.petclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OwnerJpaServiceTest {

    private OwnerService ownerService;

    private Owner dogOwner;
    private Owner catOwner;

    private final Long DOG_OWNER_ID = 1L;
    private final Long CAT_OWNER_ID = 2L;

    @Mock
    private OwnerRepository ownerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ownerService = new OwnerJpaService(ownerRepository);

        dogOwner = new Owner("Owner", "OfDogs");
        dogOwner.setId(DOG_OWNER_ID);
        catOwner = new Owner("Owner", "OfCats");
        catOwner.setId(CAT_OWNER_ID);
    }

    @Test
    void findAll() {
        Set<Owner> expectedResult = new HashSet<>();
        expectedResult.add(dogOwner);
        expectedResult.add(catOwner);
        when(ownerRepository.findAll()).thenReturn(expectedResult);

        assertEquals(expectedResult, ownerService.findAll());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(DOG_OWNER_ID)).thenReturn(Optional.of(dogOwner));
        when(ownerRepository.findById(CAT_OWNER_ID)).thenReturn(Optional.empty());

        assertEquals(dogOwner, ownerService.findById(DOG_OWNER_ID));
        assertNull(ownerService.findById(CAT_OWNER_ID));
        assertNull(ownerService.findById(null));
    }

    @Test
    void save() {
        // Trying to save a null object should throw an exception
        assertThrows(IllegalArgumentException.class, () -> ownerService.save(null));

        ownerService.save(dogOwner);
        ownerService.save(catOwner);

        verify(ownerRepository, times(1)).save(dogOwner);
        verify(ownerRepository, times(1)).save(catOwner);
    }

    @Test
    void delete() {
        ownerService.delete(dogOwner);
        verify(ownerRepository, times(1)).delete(dogOwner);
    }

    @Test
    void deleteById() {
        ownerService.deleteById(DOG_OWNER_ID);
        verify(ownerRepository, times(1)).deleteById(DOG_OWNER_ID);
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(dogOwner.getLastName())).thenReturn(dogOwner);
        when(ownerRepository.findByLastName(catOwner.getLastName())).thenReturn(null);

        assertEquals(dogOwner, ownerService.findByLastName(dogOwner.getLastName()));
        assertNull(ownerService.findByLastName(catOwner.getLastName()));
    }
}