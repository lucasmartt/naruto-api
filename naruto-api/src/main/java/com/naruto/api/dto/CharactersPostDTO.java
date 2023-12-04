package com.naruto.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
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
