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
    @NotBlank(message = "비어 있을 수 없습니다")
    private String address;

    @Column(name = "city")
    @NotBlank(message = "비어 있을 수 없습니다")
    private String city;

    @Column(name = "telephone")
    @Pattern(regexp = "\\d{1,11}", message = "형식에 맞게 입력해주세요")
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private final List<Pet> pets = new ArrayList<>();

    public boolean isNew() {
        return this.getId() == null;
    }
}
