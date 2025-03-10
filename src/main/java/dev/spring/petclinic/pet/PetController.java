package dev.spring.petclinic.pet;

import dev.spring.petclinic.owner.Owner;
import dev.spring.petclinic.owner.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("owners/{ownerId}/pets")
@RequiredArgsConstructor
public class PetController {

    private final OwnerService ownerService;
    private final PetService petService;
    private final PetRepository petRepository;

    @GetMapping("/new")
    public ModelAndView addPetForm(@PathVariable int ownerId) {
        ModelAndView mav = new ModelAndView("pets/createOrUpdatePetForm");
        Owner owner = ownerService.findById(ownerId);
        Pet pet = new Pet();
        owner.addPet(pet);
        mav.addObject("owner", owner);
        mav.addObject("pet", pet);
        mav.addObject("types", petService.findAllPetTypes());
        return mav;
    }

    @PostMapping("/new")
    public ModelAndView addPet(@Valid Pet pet, BindingResult bindingResult,
                               @RequestParam("type") String typeName, @PathVariable int ownerId) {
        Owner owner = ownerService.findById(ownerId);

        if (bindingResult.hasErrors()) {
            ModelAndView mav = new ModelAndView("pets/createOrUpdatePetForm");
            mav.addObject("owner", owner);
            mav.addObject("pet", pet);
            mav.addObject("types", petService.findAllPetTypes());
            return mav;
        }

        PetType petType = petService.findAllPetTypes()
                .stream()
                .filter(type -> type.getName().equalsIgnoreCase(typeName))
                .findFirst()
                .orElse(null);

        if (petType == null) {
            bindingResult.rejectValue("type", "notFound", "Invalid pet type");
            ModelAndView mav = new ModelAndView("pets/createOrUpdatePetForm");
            mav.addObject("owner", owner);
            mav.addObject("pet", pet);
            mav.addObject("types", petService.findAllPetTypes());
            return mav;
        }

        pet.setOwner(owner);
        pet.setType(petType);
        petService.save(pet);

        return new ModelAndView("redirect:/owners/{ownerId}");
    }




    @GetMapping("/{petId}/edit")
    public ModelAndView editPetForm(@PathVariable int ownerId, @PathVariable int petId) {
        ModelAndView mav = new ModelAndView("pets/createOrUpdatePetForm");
        Owner owner = ownerService.findById(ownerId);
        Pet pet = petService.findById(petId);

        mav.addObject("owner", owner);
        mav.addObject("pet", pet);
        mav.addObject("types", petService.findAllPetTypes());

        return mav;
    }

    @PostMapping("/{petId}/edit")
    public ModelAndView updatePet(@Valid Pet pet, BindingResult bindingResult,
                                  @RequestParam("type") String typeName, @PathVariable int ownerId,
                                  @PathVariable int petId) {
        Owner owner = ownerService.findById(ownerId);
        Pet existingPet = petService.findById(petId);

        if (bindingResult.hasErrors()) {
            ModelAndView mav = new ModelAndView("pets/createOrUpdatePetForm");
            mav.addObject("owner", owner);
            mav.addObject("pet", pet);
            mav.addObject("types", petService.findAllPetTypes());
            return mav;
        }

        PetType petType = petService.findAllPetTypes()
                .stream()
                .filter(type -> type.getName().equalsIgnoreCase(typeName))
                .findFirst()
                .orElse(null);

        if (petType == null) {
            bindingResult.rejectValue("type", "notFound", "Invalid pet type");
            ModelAndView mav = new ModelAndView("pets/createOrUpdatePetForm");
            mav.addObject("owner", owner);
            mav.addObject("pet", pet);
            mav.addObject("types", petService.findAllPetTypes());
            return mav;
        }

        // 기존 Pet 정보 업데이트
        existingPet.setName(pet.getName());
        existingPet.setBirthDate(pet.getBirthDate());
        existingPet.setType(petType);
        petService.save(existingPet);

        return new ModelAndView("redirect:/owners/{ownerId}");
    }




}
