package com.naruto.api.dto;

import com.naruto.api.models.Personal;

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
