package com.naruto.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CharactersPutDTO(
        @NotNull
        Long id,
        String name,
        String[] jutsu,
        String[] natureType,
        PersonalPostDTO personal,
        String[] tools
) {}
