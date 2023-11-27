package com.naruto.api.characters;

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
    private String[] jutsu;
    @Enumerated(EnumType.STRING)
    private NatureType[] natureType;
    @Embedded
    private Personal personal;
    private String[] tools;

    public Character(CharactersPostDTO data) {
        this.name = data.name();
        this.jutsu = data.jutsu();
        this.natureType = data.natureType();
        this.personal = new Personal(data.personal());
        this.tools = data.tools();
    }
}
