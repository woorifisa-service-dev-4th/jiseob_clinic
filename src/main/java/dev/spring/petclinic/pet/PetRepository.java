package dev.spring.petclinic.pet;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface PetRepository extends JpaRepository<Pet, Integer> {
    List<Pet> findAllByOwnerId(int ownerId);
    Pet findById(int petId);


}