package com.naruto.api.characters;

public record CharactersPostDTO(
        String name,
        String[] jutsu,
        NatureType[] natureType,
        PersonalPostDTO personal,
        String[] tools
) {}
