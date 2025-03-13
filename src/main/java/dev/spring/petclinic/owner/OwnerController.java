package dev.spring.petclinic.owner;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public ModelAndView findOwners(
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(defaultValue = "1") int page) {

        ModelAndView mav = new ModelAndView("owners/findOwners");
        int pageSize = 5;
        Owner owner = new Owner();
        mav.addObject("owner", owner);

        Page<Owner> ownerPage;

        if (lastName != null && !lastName.isBlank()) {
            ownerPage = ownerService.findByLastName(lastName, PageRequest.of(page - 1, pageSize));

            if (ownerPage.isEmpty()) {
                BindingResult bindingResult = new BeanPropertyBindingResult(owner, "owner");
                bindingResult.rejectValue("lastName", "notFound", "has not been found");
                mav.addObject("org.springframework.validation.BindingResult.owner", bindingResult);
            } else if (ownerPage.getTotalElements() == 1) {
                return new ModelAndView("redirect:/owners/" + ownerPage.getContent().get(0).getId());
            } else {
                mav.setViewName("owners/ownersList");
            }
        } else {
            ownerPage = ownerService.findAll(PageRequest.of(page - 1, pageSize));
            mav.setViewName("owners/ownersList");
        }

        // 잘못된 페이지 번호 요청 시 마지막 페이지로 리디렉트
        if (page > ownerPage.getTotalPages()) {
            return new ModelAndView("redirect:/owners?lastName=" + (lastName != null ? lastName : "") + "&page=" + ownerPage.getTotalPages());
        }

        mav.addObject("listOwners", ownerPage.getContent());
        mav.addObject("currentPage", page);
        mav.addObject("totalPages", ownerPage.getTotalPages());

        return mav;
    }


    @GetMapping("/{ownerId}/edit")
    public ModelAndView editOwnerForm(@PathVariable int ownerId) {
        Owner owner = ownerService.findById(ownerId);
        ModelAndView mav = new ModelAndView("owners/createOrUpdateOwnerForm");
        mav.addObject("owner", owner);
        return mav;
    }


    @PostMapping("/{ownerId}/edit")
    public ModelAndView updateOwner(@PathVariable int ownerId, @Valid Owner owner, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView mav = new ModelAndView("owners/createOrUpdateOwnerForm");
            mav.addObject("owner", owner);
            return mav;
        }

        owner.setId(ownerId);
        ownerService.save(owner);

        return new ModelAndView("redirect:/owners/" + owner.getId());
    }




}
