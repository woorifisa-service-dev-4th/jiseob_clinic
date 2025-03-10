package dev.spring.petclinic.pet;

import dev.spring.petclinic.common.BaseEntity;
import dev.spring.petclinic.owner.Owner;
import dev.spring.petclinic.visit.Visit;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "pets")
public class Pet extends BaseEntity {

    @Column(name = "name")
    @NotBlank(message = "is required")
    private String name;

    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "is required")
    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private PetType type;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "pet_id")
    private final Set<Visit> visits = new LinkedHashSet<>();

    public boolean isNew() {
        return this.getId() == null;
    }

    @Override
    public String toString() {
        return name;
    }
}
