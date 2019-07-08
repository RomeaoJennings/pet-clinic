package com.romeao.petclinic.controllers;

import com.romeao.petclinic.models.Owner;
import com.romeao.petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping("/owners")
public class OwnerController {
    private static final String VIEWS_OWNER_CREATE_FORM = "owners/createOrUpdateOwnerForm";
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setDisallowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult bindingResult, Model model) {

        // allow parameter-less GET request for /owners to return all records
        if (owner.getLastName() == null) {
            owner.setLastName(""); // empty string signifies broadest possible search
        }

        // find owners by last name
        Set<Owner> result = this.ownerService.findAllByLastNameContains(owner.getLastName());
        if (result.isEmpty()) {
            // no owners found
            bindingResult.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        } else if (result.size() == 1) {
            // 1 owner found
            owner = result.iterator().next();
            return "redirect:/owners/" + owner.getId();
        } else {
            // multiple owners found
            model.addAttribute("owners", result);
            return "owners/ownersList";
        }
    }

    @GetMapping("/find")
    public String initFindForm(Model model) {
        model.addAttribute(new Owner());
        return "owners/findOwners";
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable Long ownerId) {
        ModelAndView result = new ModelAndView("owners/ownerDetails");
        result.addObject(ownerService.findById(ownerId));
        return result;
    }

    @GetMapping("/new")
    public String initCreationForm(Model model) {
        model.addAttribute(new Owner());
        return VIEWS_OWNER_CREATE_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_OWNER_CREATE_FORM;
        } else {
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }

    @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model) {
        model.addAttribute(ownerService.findById(ownerId));
        return VIEWS_OWNER_CREATE_FORM;
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm(@Valid Owner owner,
                                         BindingResult result,
                                         @PathVariable Long ownerId) {
        if (result.hasErrors()) {
            return VIEWS_OWNER_CREATE_FORM;
        } else {
            owner.setId(ownerId);
            ownerService.save(owner);
            return "redirect:/owners/" + owner.getId();
        }
    }
}
