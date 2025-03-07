package dev.spring.petclinic.owner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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
}
