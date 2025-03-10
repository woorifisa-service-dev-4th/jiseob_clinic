package dev.spring.petclinic.owner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {
    Page<Owner> findByLastNameContainingIgnoreCase(String lastName, Pageable pageable);
}