package dev.spring.petclinic.pet;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PetTypeRepository extends JpaRepository<PetType, Integer> {
}
