package dev.spring.petclinic.visit;

import dev.spring.petclinic.owner.Owner;
import dev.spring.petclinic.owner.OwnerService;
import dev.spring.petclinic.pet.Pet;
import dev.spring.petclinic.pet.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("/owners/{ownerId}/pets/{petId}/visits")
@RequiredArgsConstructor
public class VisitController {

    private final OwnerService ownerService;
    private final PetService petService;
    private final VisitService visitService;

    @GetMapping("/new")
    public ModelAndView addVisitForm(@PathVariable int ownerId, @PathVariable int petId) {
        ModelAndView mav = new ModelAndView("pets/createOrUpdateVisitForm");
        Owner owner = ownerService.findById(ownerId);
        Pet pet = petService.findById(petId);

        Visit visit = new Visit();
        mav.addObject("owner", owner);
        mav.addObject("pet", pet);
        mav.addObject("visit", visit);

        return mav;
    }

    @PostMapping("/new")
    public ModelAndView addVisit(
            @PathVariable int ownerId,
            @PathVariable int petId,
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate visitDate,
            @Valid @ModelAttribute Visit visit,
            BindingResult result) {

        visit.setVisitDate(visitDate);

        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView("pets/createOrUpdateVisitForm");
            Owner owner = ownerService.findById(ownerId);
            Pet pet = petService.findById(petId);
            mav.addObject("owner", owner);
            mav.addObject("pet", pet);
            mav.addObject("visit", visit);
            return mav;
        }

        Pet pet = petService.findById(petId);
        visit.setPet(pet);

        visitService.save(visit);

        return new ModelAndView("redirect:/owners/" + ownerId);
    }
}
