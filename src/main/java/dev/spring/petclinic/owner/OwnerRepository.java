package dev.spring.petclinic.owner;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {
    List<Owner> findByLastNameContainingIgnoreCase(String lastName);
}