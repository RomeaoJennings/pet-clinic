package com.romeao.petclinic.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class BaseEntityTest {

    private BaseEntity baseEntity;

    @BeforeEach
    private void setUp() {
        baseEntity = new BaseEntity();
    }

    @Test
    void getAndSetId() {
        assertNull(baseEntity.getId());
        baseEntity.setId(5L);
        assertEquals(Long.valueOf(5), baseEntity.getId());
    }
}