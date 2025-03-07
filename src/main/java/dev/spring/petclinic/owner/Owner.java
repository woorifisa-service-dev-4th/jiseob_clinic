package dev.spring.petclinic.owner;

import dev.spring.petclinic.common.PersonEntity;
import dev.spring.petclinic.pet.Pet;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "owners")
public class Owner extends PersonEntity {

    @Column(name = "address")
    @NotBlank
    private String address;

    @Column(name = "city")
    @NotBlank
    private String city;

    @Column(name = "telephone")
    @NotBlank
    @Pattern(regexp = "\\d{10}", message = "숫자 값이 한계를 초과합니다(<10 자리>.<0 자리> 예상)")
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private final List<Pet> pets = new ArrayList<>();

}
