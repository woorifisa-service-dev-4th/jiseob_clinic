package dev.spring.petclinic.visit;

import dev.spring.petclinic.common.BaseEntity;
import dev.spring.petclinic.pet.Pet;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "visits")
public class Visit extends BaseEntity {

    @Column(name = "visit_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate visitDate;

    @Column(name = "description")
    @NotBlank
    private String description;

    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    public boolean isNew() {
        return this.getId() == null;
    }

    public LocalDate getDate() {
        return this.visitDate;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
        if (pet != null) {
            pet.getVisits().add(this);
        }
    }
}