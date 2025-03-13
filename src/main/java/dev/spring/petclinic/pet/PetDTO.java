package dev.spring.petclinic.pet;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PetDTO {
    private Integer id;
    private String name;
    private LocalDate birthDate;
    private String type;

    public PetDTO(Pet pet) {
        this.id = pet.getId();
        this.name = pet.getName();
        this.birthDate = pet.getBirthDate();
        this.type = pet.getType().getName(); // PetType에서 name만 가져오기
    }

}