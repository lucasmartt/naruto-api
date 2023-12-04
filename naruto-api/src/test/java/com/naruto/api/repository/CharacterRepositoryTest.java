package com.naruto.api.repository;

import com.naruto.api.models.Character;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CharacterRepositoryTest {
    @Autowired
    private CharacterRepository characterRepository;

    @Test
    public void CharacterRepository_SaveAll_ReturnSavedCharacter() {
        //Arrange
        Character character = Character.builder().id(2000L).name("Super Test Ninja").build();

        //Act
        Character savedCharacter = characterRepository.save(character);

        //Assert
        Assertions.assertThat(savedCharacter).isNotNull();
    }

    @Test
    public void CharacterRepository_GetAll_ReturnMoreThanOneCharacter() {
        //Arrange
        Character character1 = Character.builder().id(2000L).name("Super Test Ninja 1").build();
        Character character2 = Character.builder().id(2001L).name("Super Test Ninja 2").build();

        //Act
        Character savedCharacter1 = characterRepository.save(character1);
        Character savedCharacter2 = characterRepository.save(character2);

        List<Character> returnedCharacters = characterRepository.findAll();

        //Assert
        Assertions.assertThat(returnedCharacters).isNotNull();
        Assertions.assertThat(returnedCharacters.size()).isEqualTo(2);
    }
}

