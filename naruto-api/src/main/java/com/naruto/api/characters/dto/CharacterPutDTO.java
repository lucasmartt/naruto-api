package com.naruto.api.characters.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CharacterPutDTO(
        @NotNull
        Long id,
        String name,
        String[] jutsu,
        String[] natureType,
        PersonalPostDTO personal,
        String[] tools
) {}
