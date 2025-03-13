package dev.spring.petclinic.pet;

import dev.spring.petclinic.owner.Owner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;

    public List<PetType> findAllPetTypes() {
        return petTypeRepository.findAll();
    }

    public Pet findById(int petId) {
        return petRepository.findById(petId);
    }

    public List<PetDTO> findAllPetsByOwner(int ownerId) {
        return petRepository.findAllByOwnerId(ownerId)
                .stream()
                .map(PetDTO::new) // Pet -> PetDTO 변환
                .collect(Collectors.toList());
    }

    public void save(Pet pet) {
        petRepository.save(pet);
    }

    public PetDTO saveDTO(Pet pet) {
        return new PetDTO(petRepository.save(pet)); // 저장 후 DTO 변환하여 반환
    }


}
