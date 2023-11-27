package com.naruto.api.characters;

import com.google.gson.Gson;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "characters")
@Entity(name = "Character")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Character {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String jutsu;
    private String natureType;
    @Embedded
    private Personal personal;
    private String tools;

    public Character(CharactersPostDTO data) {
        Gson gson = new Gson();

        this.name = data.name();
        this.jutsu = gson.toJson(data.jutsu());
        this.natureType = gson.toJson(data.natureType());
        this.personal = new Personal(data.personal());
        this.tools = gson.toJson(data.tools());
    }
}
