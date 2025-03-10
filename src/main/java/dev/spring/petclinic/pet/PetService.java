package dev.spring.petclinic.pet;

import dev.spring.petclinic.owner.Owner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;

    public List<PetType> findAllPetTypes() {
        return petTypeRepository.findAll();
    }

    public Pet findById(int petId) {return petRepository.findById(petId).orElse(null);}

    public void save(Pet pet) {
        petRepository.save(pet);
    }
}
