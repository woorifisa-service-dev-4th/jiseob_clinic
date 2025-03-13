package dev.spring.petclinic.pet;

import dev.spring.petclinic.owner.Owner;
import dev.spring.petclinic.owner.OwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Pet API", description = "PetClinic의 Pet 관련 API")
public class PetRestController {
    private final PetService petService;
    private final OwnerService ownerService;

    @Operation(summary = "Pets 조회", description = "특정 Owner의 모든 Pet을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pet 조회 성공"),
            @ApiResponse(responseCode = "404", description = "해당 ID의 Owner를 찾을 수 없음")
    })
    @GetMapping("/{ownerId}")
    public ResponseEntity<List<PetDTO>> getPetsByOwner(@PathVariable int ownerId) {

        Owner owner = ownerService.findById(ownerId);
        if (owner == null) {
            return ResponseEntity.notFound().build();
        }

        List<PetDTO> petDTOs = petService.findAllPetsByOwner(ownerId);
        return ResponseEntity.ok(petDTOs);
    }

    @Operation(summary = "Pets 추가", description = "특정 Owner의 Pet을 추가합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pet 추가 성공"),
            @ApiResponse(responseCode = "404", description = "해당 ID의 Owner를 찾을 수 없음")
    })
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

    @Operation(summary = "Pet 타입 조회", description = "Pet의 모든 타입을 조회하여 보여줍니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pet Type 조회 성공"),
    })
    @GetMapping("/pettypes")
    public ResponseEntity<List<PetType>> getAllPetTypes() {
        List<PetType> petTypes = petService.findAllPetTypes();
        return ResponseEntity.ok(petTypes);
    }

    @Operation(summary = "Pet 수정", description = "특정 유저의 특정 Pet의 정보를 수정합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pet 수정 성공"),
    })
    @PutMapping("/{ownerId}/{petId}")
    public ResponseEntity<PetDTO> updatePet(@PathVariable("ownerId") int ownerId, @PathVariable("petId") int petId,
                                            @Valid @RequestBody Pet updatedPet) {
        Owner owner = ownerService.findById(ownerId);
        Pet existingPet = petService.findById(petId);

        existingPet.setName(updatedPet.getName());
        existingPet.setBirthDate(updatedPet.getBirthDate());
        existingPet.setType(updatedPet.getType());

        PetDTO updatedPetDTO = petService.saveDTO(existingPet);
        return ResponseEntity.ok(updatedPetDTO);
    }





}
