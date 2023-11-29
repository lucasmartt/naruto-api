package com.naruto.api.characters;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
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
