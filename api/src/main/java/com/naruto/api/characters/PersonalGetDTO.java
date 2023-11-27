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
        this.birthdate = personal.getBirthdate();
        this.sex = personal.getSex();
        this.age = personal.getAge();
        this.height = personal.getHeight();
        this.weight = personal.getWeight();
        this.clan = personal.getClan();
    }
}
