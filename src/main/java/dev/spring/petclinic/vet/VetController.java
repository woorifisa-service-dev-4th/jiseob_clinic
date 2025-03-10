package dev.spring.petclinic.vet;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class VetController {

    private final VetRepository vetRepository;

    @GetMapping("/vets")
    public String getVets(Model model, @RequestParam(defaultValue = "1") int page) {
        int pageSize = 5;
        Page<Vet> vetPage = vetRepository.findAll(PageRequest.of(page - 1, pageSize));


        if (page > vetPage.getTotalPages()) {
            return "redirect:/vets?page=" + vetPage.getTotalPages();
        }

        model.addAttribute("listVets", vetPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", vetPage.getTotalPages());

        return "vets/vetList";
    }

}
