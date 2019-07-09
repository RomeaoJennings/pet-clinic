package com.romeao.petclinic.controllers;

import com.romeao.petclinic.models.Owner;
import com.romeao.petclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.collections.Sets;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class OwnerControllerTest {
    // Bob Jones Test Owner
    private static final Long BOB_ID = 1L;
    private static final String BOB_FIRST = "Bob";
    private static final String BOB_LAST = "Jones";
    private static final String BOB_ADDR = "123 Bob Circle";
    private static final String BOB_PHONE = "2023321234";
    private static final String BOB_CITY = "New York";
    //Steve Jobs Test Owner
    private static final Long STEVE_ID = 2L;
    private static final String STEVE_FIRST = "Steve";
    private static final String STEVE_LAST = "Jobs";
    private static final String STEVE_ADDR = "456 Apple Way";
    private static final String STEVE_PHONE = "9991234567";
    private static final String STEVE_CITY = "San Francisco";

    private static final String OWNER = "owner";
    private Set<Owner> owners;
    private Owner bob;

    @Mock
    private OwnerService ownerService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        OwnerController controller = new OwnerController(ownerService);

        owners = new HashSet<>();
        bob = new Owner(BOB_FIRST, BOB_LAST, BOB_ADDR, BOB_CITY, BOB_PHONE);
        bob.setId(BOB_ID);
        owners.add(bob);

        Owner steve = new Owner(STEVE_FIRST, STEVE_LAST, STEVE_ADDR, STEVE_CITY, STEVE_PHONE);
        steve.setId(STEVE_ID);
        owners.add(steve);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testProcessFindForm_noParams() throws Exception {
        when(ownerService.findAllByLastNameContains("")).thenReturn(owners);

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owners"))
                .andExpect(model().attribute("owners", hasSize(2)))
                .andExpect(view().name("owners/ownersList"));

        verify(ownerService, times(1)).findAllByLastNameContains("");
        verifyNoMoreInteractions(ownerService);
    }

    @Test
    void testProcessFindForm_oneResult() throws Exception {
        when(ownerService.findAllByLastNameContains(BOB_LAST)).thenReturn(Sets.newSet(bob));

        mockMvc.perform(get("/owners")
                .param("lastName", BOB_LAST)
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/" + BOB_ID));

        verify(ownerService, times(1)).findAllByLastNameContains(BOB_LAST);
        verifyNoMoreInteractions(ownerService);
    }

    @Test
    void testProcessFindForm_noResults() throws Exception {
        when(ownerService.findAllByLastNameContains(any())).thenReturn(new HashSet<>());

        mockMvc.perform(get("/owners")
                .param("lastName", "Unknown Surname")
        )
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrors("owner", "lastName"))
                .andExpect(model().attributeHasFieldErrorCode("owner", "lastName", "notFound"))
                .andExpect(view().name("owners/findOwners"));

        verify(ownerService, times(1)).findAllByLastNameContains(any());
        verifyNoMoreInteractions(ownerService);
    }

    @Test
    void testInitFindForm() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attribute("owner", instanceOf(Owner.class)))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/findOwners"));
    }

    @Test
    void testShowOwner() throws Exception {
        when(ownerService.findById(BOB_ID)).thenReturn(bob);

        mockMvc.perform(get("/owners/{ownerId}", BOB_ID))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(OWNER))
                .andExpect(model().attribute(OWNER, instanceOf(Owner.class)))
                .andExpect(model().attribute(OWNER, hasProperty("firstName", is(BOB_FIRST))))
                .andExpect(model().attribute(OWNER, hasProperty("lastName", is(BOB_LAST))))
                .andExpect(model().attribute(OWNER, hasProperty("address", is(BOB_ADDR))))
                .andExpect(model().attribute(OWNER, hasProperty("city", is(BOB_CITY))))
                .andExpect(model().attribute(OWNER, hasProperty("telephone", is(BOB_PHONE))))
                .andExpect(view().name("owners/ownerDetails"));

        verify(ownerService, times(1)).findById(BOB_ID);
        verifyNoMoreInteractions(ownerService);
    }

    @Test
    void testInitCreationForm() throws Exception {
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attribute("owner", instanceOf(Owner.class)))
                .andExpect(view().name("owners/createOrUpdateOwnerForm"));

        verifyZeroInteractions(ownerService);
    }

    @Test
    void testProcessCreationForm_success() throws Exception {
        when(ownerService.save(any())).thenReturn(bob);

        mockMvc.perform(post("/owners/new")
                .param("firstName", BOB_FIRST)
                .param("lastName", BOB_LAST)
                .param("address", BOB_ADDR)
                .param("city", BOB_CITY)
                .param("telephone", BOB_PHONE)
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/" + BOB_ID));

        verify(ownerService, times(1)).save(any());
        verifyNoMoreInteractions(ownerService);
    }

    @Test
    void testProcessCreationForm_errors() throws Exception {
        mockMvc.perform(post("/owners/new")
                .param("firstName", BOB_FIRST)
                .param("lastName", BOB_LAST)
                .param("city", BOB_CITY)
        )
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors(OWNER))
                .andExpect(model().attributeHasFieldErrors(OWNER, "address", "telephone"))
                .andExpect(view().name("owners/createOrUpdateOwnerForm"));

        verifyZeroInteractions(ownerService);
    }

    @Test
    void testProcessUpdateOwnerForm_success() throws Exception {
        mockMvc.perform(post("/owners/{ownerId}/edit", BOB_ID)
                .param("firstName", BOB_FIRST)
                .param("lastName", BOB_LAST)
                .param("address", BOB_ADDR)
                .param("city", BOB_CITY)
                .param("telephone", BOB_PHONE)
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/" + BOB_ID));
    }

    @Test
    void testProcessUpdateOwnerForm_errors() throws Exception {
        mockMvc.perform(post("/owners/{ownerId}/edit", BOB_ID)
                .param("firstName", BOB_FIRST)
                .param("lastName", BOB_LAST)
                .param("city", BOB_CITY)
        )
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors(OWNER))
                .andExpect(model().attributeHasFieldErrors(OWNER, "address", "telephone"))
                .andExpect(view().name("owners/createOrUpdateOwnerForm"));

        verifyZeroInteractions(ownerService);
    }
}