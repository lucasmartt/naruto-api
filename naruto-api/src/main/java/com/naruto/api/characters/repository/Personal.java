package com.naruto.api.characters.repository;

import com.naruto.api.characters.dto.PersonalPostDTO;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Personal {
    private String birthdate;
    private String sex;
    private String clan;

    public Personal(PersonalPostDTO data) {
        this.birthdate = data.birthdate();
        this.sex = data.sex();
        this.clan = data.clan();
    }
}
