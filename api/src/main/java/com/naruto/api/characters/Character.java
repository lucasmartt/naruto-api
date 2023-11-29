package com.naruto.api.characters;

import com.google.gson.Gson;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "characters")
@Entity(name = "Character")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Character {
    @Id
    private Long id;
    private String name;
    private String jutsu;
    private String natureType;
    @Embedded
    private Personal personal;
    private String tools;

    public Long ensureIdAssigned(Long lastId) {
            if (lastId == null || lastId < 1431) {
                return 1431L;
            } else {
                return lastId + 1;
        }
    }

    public Character(CharactersPostDTO data, Long lastId) {
        Gson gson = new Gson();

        this.id = ensureIdAssigned(lastId);
        this.name = data.name();
        this.jutsu = gson.toJson(data.jutsu());
        this.natureType = gson.toJson(data.natureType());
        this.personal = new Personal(data.personal());
        this.tools = gson.toJson(data.tools());
    }
    public Character(CharactersPostDTO data) {
        Gson gson = new Gson();

        this.id = data.id();
        this.name = data.name();
        this.jutsu = gson.toJson(data.jutsu());
        this.natureType = gson.toJson(data.natureType());
        this.personal = new Personal(data.personal());
        this.tools = gson.toJson(data.tools());
    }

}
