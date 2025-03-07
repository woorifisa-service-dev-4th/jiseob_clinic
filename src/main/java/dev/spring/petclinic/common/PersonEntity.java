package dev.spring.petclinic.common;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@MappedSuperclass
public class PersonEntity extends BaseEntity {

    @Column(name = "first_name")
    @NotBlank(message = "비어 있을 수 없습니다")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "비어 있을 수 없습니다")
    private String lastName;

}
