package com.naruto.api.characters.dto;

import com.naruto.api.characters.repository.Personal;

public record PersonalGetDTO(
        String birthdate,
        String sex,
        String clan
) {
    public PersonalGetDTO(Personal personal) {
        this(
                personal.getBirthdate(),
                personal.getSex(),
                personal.getClan()
        );
    }
}
