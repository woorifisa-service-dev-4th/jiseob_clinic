package dev.spring.petclinic.visit;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;

    public List<Visit> findAll() {
        return visitRepository.findAll();
    }

    public Optional<Visit> findById(Long id) {
        return visitRepository.findById(id);
    }

    public Visit save(Visit visit) {
        return visitRepository.save(visit);
    }
}