package dev.spring.petclinic.owner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/owners")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    @GetMapping("/find")
    public ModelAndView initForm() {
        ModelAndView mav = new ModelAndView("owners/findOwners");
        mav.addObject("owner", new Owner());
        return mav;
    }

    @GetMapping("/new")
    public ModelAndView addOwnerForm() {
        ModelAndView mav = new ModelAndView("owners/createOrUpdateOwnerForm");
        mav.addObject("owner", new Owner());
        return mav;
    }

    @PostMapping("/new")
    public ModelAndView addOwner(@Valid Owner owner, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView mav = new ModelAndView("owners/createOrUpdateOwnerForm");
            mav.addObject("owner", owner);
            return mav;
        }

        ownerService.save(owner);
        return new ModelAndView("redirect:/owners/" + owner.getId());
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable int ownerId) {
        Owner owner = ownerService.findById(ownerId);
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject("owner", owner);
        return mav;
    }

    @GetMapping
    public ModelAndView findOwners(@RequestParam(value = "lastName", required = false) String lastName) {
        ModelAndView mav = new ModelAndView("owners/findOwners");
        Owner owner = new Owner();
        mav.addObject("owner", owner);

        if (lastName != null && !lastName.isBlank()) {
            List<Owner> owners = ownerService.findByLastName(lastName);
            if (owners.isEmpty()) {
                BindingResult bindingResult = new BeanPropertyBindingResult(owner, "owner");
                bindingResult.rejectValue("lastName", "notFound", "has not been found");
                mav.addObject("org.springframework.validation.BindingResult.owner", bindingResult);
            } else if (owners.size() == 1) {
                return new ModelAndView("redirect:/owners/" + owners.get(0).getId());
            } else {
                mav.setViewName("owners/ownersList");
                mav.addObject("listOwners", owners);
            }
        } else {
            mav.setViewName("owners/ownersList");
            mav.addObject("listOwners", ownerService.findAll());
        }

        return mav;
    }
}
