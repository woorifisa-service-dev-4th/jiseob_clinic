package dev.spring.petclinic.pet;

import dev.spring.petclinic.owner.Owner;
import dev.spring.petclinic.owner.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/pets")
@RequiredArgsConstructor
@Validated
public class PetRestController {
    private final PetService petService;
    private final OwnerService ownerService;

    // 특정 주인의 모든 펫 조회
    @GetMapping("/{ownerId}")
    public ResponseEntity<List<PetDTO>> getPetsByOwner(@PathVariable int ownerId) {

        Owner owner = ownerService.findById(ownerId);
        if (owner == null) {
            return ResponseEntity.notFound().build();
        }

        List<PetDTO> petDTOs = petService.findAllPetsByOwner(ownerId);
        return ResponseEntity.ok(petDTOs);
    }

    /**
     * 펫 등록
     * @param ownerId
     * @param pet
     * @return
     */
    @PostMapping("/{ownerId}")
    public ResponseEntity<PetDTO> createPet(@PathVariable int ownerId, @Valid @RequestBody Pet pet) {
        Owner owner = ownerService.findById(ownerId);
        if (owner == null) {
            return ResponseEntity.notFound().build();
        }

        pet.setOwner(owner);
        PetDTO savedPetDTO = petService.saveDTO(pet);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedPetDTO);
    }

    /**
     * 펫 타입 조회
     * @return
     */
    @GetMapping("/pettypes")
    public ResponseEntity<List<PetType>> getAllPetTypes() {
        List<PetType> petTypes = petService.findAllPetTypes();
        return ResponseEntity.ok(petTypes);
    }

    @PutMapping("/{ownerId}/{petId}")
    public ResponseEntity<PetDTO> updatePet(@PathVariable("ownerId") int ownerId, @PathVariable("petId") int petId,
                                            @Valid @RequestBody Pet updatedPet) {
        Owner owner = ownerService.findById(ownerId);
        Pet existingPet = petService.findById(petId);

        System.out.println("owner = " + owner.getId());
        System.out.println("existingPet = " + existingPet);

        existingPet.setName(updatedPet.getName());
        existingPet.setBirthDate(updatedPet.getBirthDate());
        existingPet.setType(updatedPet.getType());

        PetDTO updatedPetDTO = petService.saveDTO(existingPet);
        return ResponseEntity.ok(updatedPetDTO);
    }





}
