package com.romeao.petclinic.controllers;

import com.romeao.petclinic.models.Pet;
import com.romeao.petclinic.models.Visit;
import com.romeao.petclinic.services.PetService;
import com.romeao.petclinic.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/owners/{ownerId}/pets/{petId}/visits/new")
public class VisitController {
    private final VisitService visitService;
    private final PetService petService;

    public VisitController(VisitService visitService, PetService petService) {
        this.visitService = visitService;
        this.petService = petService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    /**
     * Called before each and every @RequestMapping annotated method.
     * 2 goals:
     * - Make sure we always have fresh data
     * - Since we do not use the session scope, make sure that Pet object always has an id
     * (Even though id is not part of the form fields)
     *
     * @param petId
     * @return Pet
     */
    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable Long petId, Model model) {
        Pet pet = petService.findById(petId);
        model.addAttribute(pet);
        Visit visit = new Visit();
        pet.getVisits().add(visit);
        visit.setPet(pet);
        return visit;
    }

    @GetMapping
    public String initNewVisitForm() {
        return "pets/createOrUpdateVisitForm";
    }

    @PostMapping
    public String processNewVisitForm(@Valid Visit visit, BindingResult result) {
        if (result.hasErrors()) {
            return "pets/createOrUpdateVisitForm";
        } else {
            visitService.save(visit);
            return "redirect:/owners/{ownerId}";
        }
    }

}
