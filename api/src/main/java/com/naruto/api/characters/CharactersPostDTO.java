package com.naruto.api.characters;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CharactersPostDTO(
        @NotNull
        Long id,
        @NotBlank
        String name,
        String[] jutsu,
        String[] natureType,
        PersonalPostDTO personal,
        String[] tools
) {}
