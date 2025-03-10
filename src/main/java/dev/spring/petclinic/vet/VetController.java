package dev.spring.petclinic.vet;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class VetController {

    private final VetRepository vetRepository;

    @GetMapping("/vets")
    public ModelAndView getVets(@RequestParam(defaultValue = "1") int page) {
        int pageSize = 5;
        Page<Vet> vetPage = vetRepository.findAll(PageRequest.of(page - 1, pageSize));
        ModelAndView mav = new ModelAndView("vets/vetList");

        if (page > vetPage.getTotalPages()) {
            return new ModelAndView("redirect:/vets?page=" + vetPage.getTotalPages());
        }

        mav.addObject("listVets", vetPage.getContent());
        mav.addObject("currentPage", page);
        mav.addObject("totalPages", vetPage.getTotalPages());

        return mav;
    }

}
