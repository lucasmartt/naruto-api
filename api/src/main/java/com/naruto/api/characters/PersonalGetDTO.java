package com.naruto.api.characters;

import com.naruto.api.characters.Sex;

public record PersonalGetDTO(
        String birthdate,
        Sex sex,
        int age,
        String height,
        String weight,
        String clan
) {
    public PersonalGetDTO(Personal personal) {
        this(
                personal.getBirthdate(),
                personal.getSex(),
                personal.getAge(),
                personal.getHeight(),
                personal.getWeight(),
                personal.getClan()
        );
    }
}
