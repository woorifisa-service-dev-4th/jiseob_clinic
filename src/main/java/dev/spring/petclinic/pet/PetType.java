package dev.spring.petclinic.pet;

import dev.spring.petclinic.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "types")
public class PetType extends BaseEntity {

    @Column(name = "name")
    @NotBlank
    private String name;
}
