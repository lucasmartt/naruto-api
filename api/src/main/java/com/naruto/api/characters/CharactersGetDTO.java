package com.naruto.api.characters;

import com.google.gson.Gson;
import jakarta.validation.constraints.NotBlank;

public record CharactersGetDTO(
        @NotBlank
        String name,
        String[] jutsu,
        NatureType[] natureType,
        PersonalGetDTO personal,
        String[] tools
) {
        public CharactersGetDTO(Character character) {
                Gson gson = new Gson();

                this.name = character.getName();
                this.jutsu = gson.fromJson(character.getJutsu(), String[].class);
                this.natureType = gson.fromJson(character.getNatureType(), NatureType[].class);
                this.personal = new PersonalGetDTO(character.getPersonal());
                this.tools = gson.fromJson(character.getTools(), String[].class);
        }
}
