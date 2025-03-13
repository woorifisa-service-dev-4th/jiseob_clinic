package dev.spring.petclinic.owner;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/owners")
@Tag(name = "Owner API", description = "PetClinic의 Owner 관련 API")
public class OwnerRestController {

    private final OwnerService ownerService;

    @Operation(summary = "Owner 조회", description = "특정 ID의 Owner를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Owner 조회 성공"),
            @ApiResponse(responseCode = "404", description = "해당 ID의 Owner를 찾을 수 없음")
    })
    @GetMapping("/{ownerId}")
    public ResponseEntity<OwnerResponse> getOwnerById(
            @Parameter(description = "조회할 Owner의 ID", example = "1") @PathVariable int ownerId) {

        Owner owner = ownerService.findById(ownerId);
        return Optional.ofNullable(owner)
                .map(o -> ResponseEntity.ok(new OwnerResponse(o)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Owner 목록 조회", description = "모든 Owner 목록을 조회하거나 성씨(Last Name)로 검색합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Owner 목록 조회 성공")
    })
    @GetMapping("")
    public ResponseEntity<List<OwnerResponse>> getOwners(
            @Parameter(description = "검색할 성씨 (Last Name)", example = "Smith")
            @RequestParam(value = "lastName", required = false) String lastName,

            @Parameter(description = "페이지 번호 (1부터 시작)", example = "1")
            @RequestParam(defaultValue = "1") int page) {

        int pageSize = 5;
        Page<Owner> ownerPage;

        if (lastName != null && !lastName.isBlank()) {
            ownerPage = ownerService.findByLastName(lastName, PageRequest.of(page - 1, pageSize));
        } else {
            ownerPage = ownerService.findAll(PageRequest.of(page - 1, pageSize));
        }

        List<OwnerResponse> ownerDTOs = ownerPage.getContent()
                .stream()
                .map(OwnerResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(ownerDTOs);
    }

    @Operation(summary = "Owner 추가", description = "새로운 Owner를 추가합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Owner 추가 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터")
    })
    @PostMapping("")
    public ResponseEntity<OwnerResponse> addOwner(
            @Valid @RequestBody OwnerRequest ownerRequest) {

        Owner owner = new Owner();
        owner.setFirstName(ownerRequest.getFirstName());
        owner.setLastName(ownerRequest.getLastName());
        owner.setAddress(ownerRequest.getAddress());
        owner.setCity(ownerRequest.getCity());
        owner.setTelephone(ownerRequest.getTelephone());

        Owner savedOwner = ownerService.save(owner);
        return ResponseEntity.ok(new OwnerResponse(savedOwner));
    }


    @Operation(summary = "Owner 수정", description = "Owner 정보를 수정합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Owner 수정 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터"),
            @ApiResponse(responseCode = "404", description = "해당 ID의 Owner를 찾을 수 없음")
    })
    @PutMapping("/{ownerId}")
    public ResponseEntity<OwnerResponse> updateOwner(
            @PathVariable int ownerId,
            @Valid @RequestBody OwnerRequest ownerRequest) {

        Owner existingOwner = ownerService.findById(ownerId);
        if (existingOwner == null) {
            return ResponseEntity.notFound().build();
        }

        existingOwner.setFirstName(ownerRequest.getFirstName());
        existingOwner.setLastName(ownerRequest.getLastName());
        existingOwner.setAddress(ownerRequest.getAddress());
        existingOwner.setCity(ownerRequest.getCity());
        existingOwner.setTelephone(ownerRequest.getTelephone());


        Owner updatedOwner = ownerService.save(existingOwner);

        return ResponseEntity.ok(new OwnerResponse(updatedOwner));
    }
}
