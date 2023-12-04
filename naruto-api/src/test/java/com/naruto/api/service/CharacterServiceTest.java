package com.naruto.api.service;

import com.naruto.api.dto.CharactersGetDTO;
import com.naruto.api.dto.CharactersPostDTO;
import com.naruto.api.dto.CharactersPutDTO;
import com.naruto.api.models.Character;
import com.naruto.api.repository.CharacterRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CharacterServiceTest {

    @Mock
    private CharacterRepository characterRepository;

    @InjectMocks
    private CharacterService characterService;

    @Test
    public void CharacterService_PostNewCharacter_ReturnsOkResponse() {
        Character character = Character.builder().id(2000L).name("Super Test Ninja").build();
        CharactersPostDTO charactersPostDTO = CharactersPostDTO.builder().id(2000L).name("Super Test Ninja").build();

        when(characterRepository.save(Mockito.any(Character.class))).thenReturn(character);

        ResponseEntity<String> response = characterService.postNewCharacter(charactersPostDTO);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    };

    @Test
    public void CharacterService_GetCharacterById_ReturnsCharactersGetDTO() {
        Character character = Character.builder().id(2000L).name("Super Test Ninja").build();
        CharactersPostDTO charactersPostDTO = CharactersPostDTO.builder().id(2000L).name("Super Test Ninja").build();

        when(characterRepository.findById(2000L)).thenReturn(Optional.ofNullable(character));

        ResponseEntity<CharactersGetDTO> response = characterService.getCharacterById(2000L);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();
    };

    @Test
    public void CharacterService_UpdateCharacterInfo_ReturnsCharactersPutDTO() {
        Character character = Character.builder().id(2000L).name("Super Test Ninja").build();
        CharactersPutDTO charactersPutDTO = CharactersPutDTO.builder().id(2000L).name("Super Test Ninja").build();

        when(characterRepository.getReferenceById(2000L)).thenReturn(character);
        when(characterRepository.save(Mockito.any(Character.class))).thenReturn(character);

        ResponseEntity<CharactersGetDTO> response = characterService.updateCharacterInfo(charactersPutDTO);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();
    };

    @Test
    public void CharacterService_DeleteCharacterById_ReturnsCharactersPutDTO() {
        ResponseEntity<String> response = characterService.deleteCharacterById(2000L);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    };
}
