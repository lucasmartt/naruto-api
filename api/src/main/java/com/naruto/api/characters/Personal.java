package com.naruto.api.characters;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Personal {
    private String birthdate;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    private int age;
    private String height;
    private String weight;
    private String clan;

    public Personal(PersonalPostDTO data) {
        this.birthdate = data.birthdate();
        this.sex = data.sex();
        this.age = data.age();
        this.height = data.height();
        this.weight = data.weight();
        this.clan = data.clan();
    }
}
