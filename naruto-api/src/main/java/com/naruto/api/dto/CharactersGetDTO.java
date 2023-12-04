package com.naruto.api.dto;

import com.google.gson.Gson;
import com.naruto.api.models.Character;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CharactersGetDTO {
        Long id;
        String name;
        String[] jutsu;
        String[] natureType;
        PersonalGetDTO personal;
        String[] tools;

        public CharactersGetDTO(Character character) {
                Gson gson = new Gson();

                this.id = character.getId();
                this.name = character.getName();
                this.jutsu = gson.fromJson(character.getJutsu(), String[].class);
                this.natureType = gson.fromJson(character.getNatureType(), String[].class);
                if (character.getPersonal() != null) {
                        this.personal = new PersonalGetDTO(character.getPersonal());
                }
                this.tools = gson.fromJson(character.getTools(), String[].class);
        }
}
