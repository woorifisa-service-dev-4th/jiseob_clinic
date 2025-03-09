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
        int pageSize = 5; // 페이지당 데이터 개수
        Page<Vet> vetPage = vetRepository.findAll(PageRequest.of(page - 1, pageSize));

        // ✅ 페이지가 유효한지 체크
        if (page > vetPage.getTotalPages()) {
            return "redirect:/vets?page=" + vetPage.getTotalPages(); // 마지막 페이지로 리디렉션
        }

        model.addAttribute("listVets", vetPage.getContent()); // 수의사 목록
        model.addAttribute("currentPage", page); // 현재 페이지
        model.addAttribute("totalPages", vetPage.getTotalPages()); // 전체 페이지 수

        return "vets/vetList"; // Thymeleaf 템플릿 반환
    }

}
