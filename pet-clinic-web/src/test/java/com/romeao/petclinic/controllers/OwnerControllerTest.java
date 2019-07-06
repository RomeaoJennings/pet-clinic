package com.romeao.petclinic.controllers;

import com.romeao.petclinic.models.Owner;
import com.romeao.petclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class OwnerControllerTest {
    // Bob Jones Test Owner
    public static final Long BOB_ID = 1L;
    public static final String BOB_FIRST = "Bob";
    public static final String BOB_LAST = "Jones";
    public static final String BOB_ADDR = "123 Bob Circle";
    public static final String BOB_PHONE = "(202) 332-1234";
    public static final String BOB_CITY = "New York";

    //Steve Jobs Test Owner
    public static final Long STEVE_ID = 2L;
    public static final String STEVE_FIRST = "Steve";
    public static final String STEVE_LAST = "Jobs";
    public static final String STEVE_ADDR = "456 Apple Way";
    public static final String STEVE_PHONE = "(999) 123-4567";
    public static final String STEVE_CITY = "San Francisco";
    public static final String OWNER = "owner";

    @Mock
    private OwnerService ownerService;

    private OwnerController controller;

    private Set<Owner> owners;
    private Owner bob;
    private Owner steve;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new OwnerController(ownerService);

        owners = new HashSet<>();
        bob = new Owner(BOB_FIRST, BOB_LAST, BOB_ADDR, BOB_CITY, BOB_PHONE);
        bob.setId(BOB_ID);
        owners.add(bob);
        steve = new Owner(STEVE_FIRST, STEVE_LAST, STEVE_ADDR, STEVE_CITY, STEVE_PHONE);
        steve.setId(STEVE_ID);
        owners.add(steve);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void listOwners() throws Exception {
        when(ownerService.findAll()).thenReturn(owners);

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));
    }

    @Test
    void listOwnersByIndex() throws Exception {
        when(ownerService.findAll()).thenReturn(owners);

        mockMvc.perform(get("/owners/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));
    }

    @Test
    void findOwners() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("notImplemented"));
        verifyZeroInteractions(ownerService);
    }

    @Test

    void showOwners() throws Exception {
        when(ownerService.findById(BOB_ID)).thenReturn(bob);

        mockMvc.perform(get("/owners/{ownerId}", BOB_ID))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(OWNER))
                .andExpect(model().attribute(OWNER, hasProperty("firstName", is(BOB_FIRST))))
                .andExpect(model().attribute(OWNER, hasProperty("lastName", is(BOB_LAST))))
                .andExpect(model().attribute(OWNER, hasProperty("address", is(BOB_ADDR))))
                .andExpect(model().attribute(OWNER, hasProperty("city", is(BOB_CITY))))
                .andExpect(model().attribute(OWNER, hasProperty("telephone", is(BOB_PHONE))))
                .andExpect(view().name("owners/ownerDetails"));

        verify(ownerService, times(1)).findById(BOB_ID);
        verifyNoMoreInteractions(ownerService);
    }
}