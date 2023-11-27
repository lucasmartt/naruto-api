package com.naruto.api.characters;

public record Personal(
        String birthdate,
        Sex sex,
        int age,
        String height,
        String weight,
        String clan
) {}
