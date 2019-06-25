package com.romeao.petclinic.services.map;

import com.romeao.petclinic.models.Visit;
import com.romeao.petclinic.services.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VisitMapServiceTest {

    private VisitService visitService;
    
    private Visit visit1;
    private Visit visit2;
    
    @BeforeEach
    void setUp() {
        visitService = new VisitMapService();
        visit1 = new Visit();
        visit2 = new Visit();
        
        visitService.save(visit1);
    }

    @Test
    void findAll() {
        Set<Visit> result = visitService.findAll();

        assertTrue(result.contains(visit1));
        assertFalse(result.contains(visit2));
        assertEquals(1, result.size());
    }

    @Test
    void findById() {
        assertNull(visitService.findById(visit2.getId()));
        assertEquals(visit1, visitService.findById(visit1.getId()));
    }

    @Test
    void save() {
        assertEquals(1, visitService.findAll().size());

        // saving existing object should not create more objects
        visitService.save(visit1);
        assertEquals(1, visitService.findAll().size());

        // add visit2 and confirm that it is saved
        visitService.save(visit2);
        assertEquals(2, visitService.findAll().size());
        assertTrue(visitService.findAll().contains(visit2));
    }

    @Test
    void deleteById() {
        // deleting item not in list should not affect it.
        assertEquals(1, visitService.findAll().size());
        visitService.deleteById(visit2.getId());
        assertEquals(1, visitService.findAll().size());

        // delete visit1 and confirm it is deleted
        visitService.deleteById(visit1.getId());
        assertNull(visitService.findById(visit1.getId()));
        assertEquals(0, visitService.findAll().size());
    }

    @Test
    void delete() {
        // deleting item not in list should not affect it.
        assertEquals(1, visitService.findAll().size());
        visitService.delete(visit2);
        assertEquals(1, visitService.findAll().size());

        // delete visit1 and confirm it is deleted
        visitService.delete(visit1);
        assertNull(visitService.findById(visit1.getId()));
        assertEquals(0, visitService.findAll().size());
    }
}