package com.naruto.api.characters;

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
