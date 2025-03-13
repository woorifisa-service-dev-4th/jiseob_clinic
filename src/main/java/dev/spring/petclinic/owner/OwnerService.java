package dev.spring.petclinic.owner;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    public Owner findById(int ownerId) {
        return ownerRepository.findById(ownerId).orElse(null);
    }

    public Page<Owner> findByLastName(String lastName, Pageable pageable) {
        return ownerRepository.findByLastNameContainingIgnoreCase(lastName, pageable);
    }

    public Page<Owner> findAll(Pageable pageable) {
        return ownerRepository.findAll(pageable);
    }
}
