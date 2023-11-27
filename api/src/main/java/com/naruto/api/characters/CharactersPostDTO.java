package com.naruto.api.characters;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record CharactersPostDTO(
        @NotBlank
        String name,
        String[] jutsu,
        NatureType[] natureType,
        PersonalPostDTO personal,
        String[] tools
) {}
