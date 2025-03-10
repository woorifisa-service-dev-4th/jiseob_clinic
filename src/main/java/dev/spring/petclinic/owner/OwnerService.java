package dev.spring.petclinic.owner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public void save(Owner owner) {
        ownerRepository.save(owner);
    }

    public Owner findById(int ownerId) {
        return ownerRepository.findById(ownerId).orElse(null);
    }

    public List<Owner> findByLastName(String lastName) {
        return ownerRepository.findByLastNameContainingIgnoreCase(lastName);
    }

    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }
}
