package dev.spring.petclinic.pet;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Integer> {
}